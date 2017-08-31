package com.yzg.common.app;

import com.yzg.common.util.YLog;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

/**
 * Created by yzg on 2017/6/23.
 */

public class ExceptionHandler{
    /**网络连接异常**/
    public static int NETWORK_ACCESS  = 1000;
    /**请求超时**/
    public static int REQUEST_TIMEOUT = 1001;
    /**服务器异常**/
    public static int SERVICE_EXCEPTION = 1002;
    /**网络错误**/
    public static int NETWORK_ERROR = 1003;

    /**服务器返回异常数据**/
    public static int SERVER_ERROR_DATA = 1100;
    /**未知错误**/
    public static int UNKNOWN = 1101;

    private ExceptionHandler(){}

    public static YException handleException(Throwable exception){
        YLog.e("ExceptionHandler", exception.getMessage());

        if(exception instanceof UnknownHostException || exception instanceof ConnectException){
            return new YException(NETWORK_ACCESS, "网络连接异常", exception);
        }

        if(exception instanceof SocketTimeoutException){
            return new YException(REQUEST_TIMEOUT, "请求超时", exception);
        }

        if(exception instanceof UnknownServiceException){
            return new YException(SERVICE_EXCEPTION, "服务器不可用", exception);
        }

        if(exception instanceof SocketException){
            return new YException(NETWORK_ERROR, "网络错误", exception);
        }

        if(exception instanceof ServerException){
            return new YException(SERVER_ERROR_DATA, "服务器异常", exception);
        }

        return new YException(UNKNOWN, "未知错误", exception);
    }
}
