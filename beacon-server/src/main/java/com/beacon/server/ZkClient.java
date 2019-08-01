package com.beacon.server;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 14:49
 * version 1.0
 */
@Component
public class ZkClient {


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


    private static CuratorListener curatorListener = new CuratorListener() {

        @Override
        public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
            if (event == null) {
                return;
            }
            if (CuratorEventType.CREATE == event.getType()) {
                System.out.println(event.getData());
            }

        }
    };

    public static void listener(String path) throws Exception {

        cf.getCuratorListenable().addListener(curatorListener);

    }

    public static void main(String[] args) throws Exception {
        listener("/beacon");
    }


}