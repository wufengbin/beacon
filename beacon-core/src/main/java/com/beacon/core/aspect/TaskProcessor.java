package com.beacon.core.aspect;

import com.beacon.client.Task;
import com.beacon.client.TaskInfo;
import com.beacon.client.TaskInfoBuilder;
import com.beacon.core.register.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 11:15
 * version 1.0
 */
@Component
public class TaskProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TaskProcessor.class);

    @Autowired
    private ZkClient zkClient;

    // @Value("${beacon.nameSpace}")
    private String nameSpace = "/beacon/beaconCore/";

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
                continue;
            }
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();
            String corn = task.value();
            TaskInfo taskInfo = TaskInfoBuilder.builder().address(getHost()).clazz(className)
                    .method(methodName)
                    .corn(corn)
                    .build();
            zkClient.create(nameSpace + methodName, taskInfo.toString());
        }
        return bean;
    }


    private String getHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("beacon getHost error", e);
            return "";
        }
    }

}