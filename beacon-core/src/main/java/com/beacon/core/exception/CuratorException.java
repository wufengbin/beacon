package com.beacon.core.exception;

/**
 * @author fengbin2.wu
 * @date 2019-07-31 15:53
 * version 1.0
 */
public class CuratorException extends RuntimeException {

    public CuratorException() {
    }

    public CuratorException(String message) {
        super(message);
    }

    public CuratorException(Throwable cause) {
        super(cause);
    }
}