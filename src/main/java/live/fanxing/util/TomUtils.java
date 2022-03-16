package live.fanxing.util;

public class TomUtils {

    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     *                * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * 格式化
     *
     * @param pre    前缀
     * @param params 参数值
     * @return 格式化后的文本
     */
    public static String format(String pre, String params) {
        if (isEmpty(params) || isEmpty(pre)) {
            return pre;
        }
        return pre.concat(params);
    }

    public static void TomTest() {
        System.out.println("TomTest >>> OK");
    }

}
