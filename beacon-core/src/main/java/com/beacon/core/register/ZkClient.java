package com.beacon.core.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 14:49
 * version 1.0
 */
public class ZkClient {

    private static final String NAME_SPACE = "beacon";

    CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
            .connectString("")
            .retryPolicy(new ExponentialBackoffRetry(1000, 5))
            .namespace(NAME_SPACE)
            .build();


    public void createContainers(String path) {
        try {
            curatorFramework.createContainers(path);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void add() {
        curatorFramework.create();
    }


}