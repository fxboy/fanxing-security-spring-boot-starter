package live.fanxing.tomres;

import live.fanxing.util.TomUtils;

public class ResultController {

    /**
     * 响应行数向响应结果转换
     */
    protected TomResult toRes(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应标识向响应结果转换
     */
    protected TomResult toRes(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功消息
     */
    public TomResult success() {
        return TomResult.success();
    }

    /**
     * 返回失败消息
     */
    public TomResult error() {
        return TomResult.error();
    }

    /**
     * 返回自定义成功提示
     */
    public TomResult success(String message) {
        return TomResult.success(message);
    }

    /**
     * 返回自定义成功提示并携带数据
     */
    public TomResult success(String message, Object data) {
        return TomResult.success(message, data);
    }

    /**
     * 返回自定义失败提示
     */
    public TomResult error(String message) {
        return TomResult.error(message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return TomUtils.format("redirect:", url);
    }

}
