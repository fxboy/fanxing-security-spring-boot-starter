package live.fanxing.authentication.enums;

import java.util.Locale;

public enum Method {
    AUTO("AUTO"),
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE")
    ;

    String method;

    Method(String method){
        this.method = method;
    }

    public String getMethod() {
        return method.toUpperCase(Locale.ROOT);
    }
}
