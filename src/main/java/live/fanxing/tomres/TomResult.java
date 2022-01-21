package live.fanxing.tomres;

import live.fanxing.enums.HttpStatusCode;

import java.util.HashMap;

/**
 * 响应结果封装
 */
public class TomResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private static final String CODE_TAG = "code";

    /**
     * 返回提示
     */
    private static final String MSG_TAG = "msg";

    /**
     * 返回数据
     */
    private static final String DATA_TAG = "data";

    /**
     * 初始化 TomResult 对象，无参构造
     */
    public TomResult() {
    }

    /**
     * 初始化 TomResult 对象
     *
     * @param standard 状态码枚举
     */
    public TomResult(TomResultStandard standard) {
        super.put(CODE_TAG, standard.getCode());
        super.put(MSG_TAG, standard.getMessage());
    }

    /**
     * 初始化 TomResult 对象
     *
     * @param code 状态码
     * @param msg  返回提示
     */
    public TomResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化 TomResult 对象
     *
     * @param code 状态码
     * @param msg  返回提示
     * @param data 返回数据
     */
    public TomResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (null != data) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 默认 SUCCESS(200, "请求成功"),
     */
    public static TomResult success() {
        return TomResult.success(HttpStatusCode.SUCCESS);
    }

    /**
     * 返回成功消息
     *
     * @param standard 状态码枚举
     * @return 自定义枚举状态码与消息
     */
    public static TomResult success(TomResultStandard standard) {
        return new TomResult(standard);
    }

    /**
     * 返回成功消息
     *
     * @param msg 自定义提示内容
     * @return 返回提示
     */
    public static TomResult success(String msg) {
        return TomResult.success(msg, null);
    }

    /**
     * 返回成功数据
     *
     * @param data 返回数据
     * @return 查询成功并携带数据
     */
    public static TomResult success(Object data) {
        return TomResult.success(HttpStatusCode.SUCCESS.getMessage(), data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  自定义提示内容
     * @param data 返回数据
     * @return 成功消息
     */
    public static TomResult success(String msg, Object data) {
        return new TomResult(HttpStatusCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回失败消息
     *
     * @return 失败消息
     */
    public static TomResult error() {
        return TomResult.error("操作失败");
    }

    /**
     * 返回失败消息
     *
     * @param standard 状态码枚举
     * @return 失败消息
     */
    public static TomResult error(TomResultStandard standard) {
        return new TomResult(standard);
    }

    /**
     * 返回失败消息
     *
     * @param msg 自定义消息
     * @return 失败消息
     */
    public static TomResult error(String msg) {
        return TomResult.error(msg, null);
    }

    /**
     * 返回失败消息
     *
     * @param msg  自定义消息
     * @param data 响应数据
     * @return 失败消息
     */
    public static TomResult error(String msg, Object data) {
        return new TomResult(HttpStatusCode.ERROR.getCode(), msg, data);
    }

    /**
     * 返回失败消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static TomResult error(int code, String msg) {
        return new TomResult(code, msg, null);
    }

}
