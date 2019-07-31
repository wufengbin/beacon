package com.beacon.core.aspect;

import com.alibaba.fastjson.JSON;
import com.beacon.client.Task;
import com.beacon.client.TaskInfo;
import com.beacon.client.TaskInfoBuilder;
import com.beacon.core.register.ZkClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 11:15
 * version 1.0
 */
@Component
public class TaskProcessor implements BeanPostProcessor {

    @Autowired
    private ZkClient zkClient;

    @Value("${beacon.nameSpace}")
    private String nameSpace;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            Task task = AnnotationUtils.findAnnotation(method, Task.class);
            if (task == null) {
                return bean;
            }
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();
            String corn = task.value();
            TaskInfo taskInfo = TaskInfoBuilder.builder().clazz(className).method(methodName).corn(corn).build();
            zkClient.create(nameSpace, JSON.toJSONString(taskInfo));
        }
        return bean;
    }

}