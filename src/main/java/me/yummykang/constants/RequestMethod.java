package me.yummykang.constants;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public enum RequestMethod {
    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete");

    private String method;

    RequestMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
