package com.beacon.client;

import java.lang.annotation.*;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 09:11
 * version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Task {

    String value() default "";

}