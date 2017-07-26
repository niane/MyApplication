package com.yzg.common.app;

/**
 * Created by yzg on 2017/7/26.
 */

public class YException {
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

    private int code;
    private String message;

    public YException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
