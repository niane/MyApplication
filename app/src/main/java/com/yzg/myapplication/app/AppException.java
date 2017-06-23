package com.yzg.myapplication.app;

/**
 * Created by yzg on 2017/6/23.
 */

public class AppException extends Exception {

    /**网络连接异常**/
    public static int CONNECTION_EXCEPTION = 1;
    /**服务器异常**/
    public static int SERVER_EXCEPTION = 2;

    private int exceptionCode;

    private AppException(int code, String msg){
        super(msg);
        this.exceptionCode = code;
    }

    public int getCode() {
        return exceptionCode;
    }

    /**网络连接异常**/
    public static AppException ConnectionException(){
        return new AppException(CONNECTION_EXCEPTION, "网络连接异常");
    }

    /**服务器异常**/
    public static AppException ServerException(){
        return new AppException(SERVER_EXCEPTION, "服务器异常");
    }
}
