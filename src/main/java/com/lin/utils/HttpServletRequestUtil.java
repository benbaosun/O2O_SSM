package com.lin.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 请求工具类
 */

public class HttpServletRequestUtil {

    /**
     * 将参数转换成int型
     * @param request 请求
     * @param key 键
     * @return 转换成int型后的值
     */
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (NumberFormatException ignored) {
        }
        return -1;
    }

    /**
     * 将参数转换成long型
     * @param request 请求
     * @param key 键
     * @return 转换成long型后的值
     */
    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.decode(request.getParameter(key));
        } catch (NumberFormatException ignored) {
        }
        return -1;
    }

    /**
     * 将参数转换成double型
     * @param request 请求
     * @param key 键
     * @return 转换成double型后的值
     */
    public static double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (NumberFormatException ignored) {
        }
        return -1;
    }

    /**
     * 将参数转换成boolean型
     * @param request 请求
     * @param key 键
     * @return 转换成boolean型后的值
     */
    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    /**
     * 将参数转换成String型
     * @param request 请求
     * @param key 键
     * @return 转换成String型后的值
     */
    public static String getString(HttpServletRequest request, String key) {
        try {
            // 获取参数中的值
            String result = request.getParameter(key);
            // 去空格
            result = result.trim();
            // 内容为空
            if ("".equals(result)) {
                result = null;
            }
            return result;
        } catch (Exception ignored) {
        }
        return null;
    }
}
