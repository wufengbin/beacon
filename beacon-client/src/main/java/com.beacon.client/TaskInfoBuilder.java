package com.beacon.client;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 11:20
 * version 1.0
 */
public class TaskInfoBuilder {

    private TaskInfo taskInfo;

    public TaskInfoBuilder() {
        this.taskInfo = new TaskInfo();
    }

    public static TaskInfoBuilder builder() {
        return new TaskInfoBuilder();
    }

    public TaskInfoBuilder clazz(String clazz) {
        this.taskInfo.setClazz(clazz);
        return this;
    }

    public TaskInfoBuilder method(String method) {
        this.taskInfo.setMethod(method);
        return this;
    }

    public TaskInfoBuilder corn(String corn) {
        this.taskInfo.setCorn(corn);
        return this;
    }

    public TaskInfo build() {
        return this.taskInfo;
    }

}