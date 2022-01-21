package live.fanxing.enums;

import live.fanxing.tomres.TomResultStandard;

public enum HttpStatusCode implements TomResultStandard {

    // 200
    SUCCESS(200, "请求成功"),
    // 500
    ERROR(500, "系统内部错误");

    private int code;

    private String message;

    private HttpStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
