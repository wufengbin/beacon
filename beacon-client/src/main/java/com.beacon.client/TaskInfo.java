package com.beacon.client;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 11:17
 * version 1.0
 */
public class TaskInfo {

    private String address;
    private String clazz;
    private String method;
    private String corn;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCorn() {
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }

    @Override
    public String toString() {
        return "{\"address\":" + "\"" + address + "\"," + "\"clazz\":" + "\"" + clazz + "\","
                + "\"method\":" + "\"" + method + "\","
                + "\"corn\":" + "\"" + corn + "\"" + "}";
    }

    public static void main(String[] args) {
        System.out.println(TaskInfoBuilder.builder().address("address").clazz("clazz").method("method").corn("corn")
                .build().toString());
    }

}