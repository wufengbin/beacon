package com.beacon.core.register;

import com.beacon.core.exception.CuratorException;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 14:49
 * version 1.0
 */
@Component
public class ZkClient {

    private static final Logger logger = LoggerFactory.getLogger(ZkClient.class);

    private static final String ZOOKEEPER_ADDRESS = "127.0.0.1:2181";

    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

    private static CuratorFramework cf;

    static {
        cf = CuratorFrameworkFactory.builder()
                .connectString(ZOOKEEPER_ADDRESS)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(15000)
                .sessionTimeoutMs(5000)
                .build();
        cf.start();
    }


    public void create(String path, String data) {
        try {
            cf.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path, data.getBytes());
        } catch (Exception e) {
            logger.error("create node error,path:{},data:{}", path, data, e);
            throw new CuratorException(e);
        }
    }


}