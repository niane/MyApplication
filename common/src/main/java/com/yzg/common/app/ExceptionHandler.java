package com.yzg.common.app;

import com.yzg.common.util.YLog;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import static com.yzg.common.app.YException.NETWORK_ACCESS;
import static com.yzg.common.app.YException.NETWORK_ERROR;
import static com.yzg.common.app.YException.REQUEST_TIMEOUT;
import static com.yzg.common.app.YException.SERVER_ERROR_DATA;
import static com.yzg.common.app.YException.SERVICE_EXCEPTION;
import static com.yzg.common.app.YException.UNKNOWN;

/**
 * Created by yzg on 2017/6/23.
 */

public class ExceptionHandler{

    private ExceptionHandler(){}

    public static YException handleException(Throwable exception){
        YLog.e("ExceptionHandler", exception.getMessage());

        if(exception instanceof UnknownHostException || exception instanceof ConnectException){
            return new YException(NETWORK_ACCESS, "网络连接异常");
        }

        if(exception instanceof SocketTimeoutException){
            return new YException(REQUEST_TIMEOUT, "请求超时");
        }

        if(exception instanceof UnknownServiceException){
            return new YException(SERVICE_EXCEPTION, "服务器不可用");
        }

        if(exception instanceof SocketException){
            return new YException(NETWORK_ERROR, "网络错误");
        }

        return new YException(UNKNOWN, "未知错误");
    }

    public static YException handleException(ServerException exception){
        YLog.e("ExceptionHandler", exception.getMessage());
        return new YException(SERVER_ERROR_DATA, exception.getMessage());
    }
}
