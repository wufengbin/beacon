package com.beacon.core.order;

import com.beacon.client.Task;
import org.springframework.stereotype.Component;

/**
 * @author fengbin2.wu
 * @date 2019-07-24 11:36
 * version 1.0
 */
@Component
public class OrderTask {


    @Task("* * * * * ?")
    public void syncOrder() {

        System.out.println("syncOrder execute ...");
    }

}