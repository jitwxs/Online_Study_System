package com.wxs.exception;

/**
 * Author: jitwxs
 * Date: 2017-10-11
 * 系统自定义异常类，针对预期异常，需要在程序中抛出此类的异常
 */
public class CustomException extends Exception {

    /**
     * 异常信息
     */
    public String message;

    /**
     * 包含异常信息的构造函数
     * @param message 异常信息
     */
    public CustomException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
