package com.lin.utils;

import com.google.code.kaptcha.Constants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lkmc2
 * @date 2018/5/20.
 * 验证码工具类
 */

public class CodeUtil {

    /**
     * 判断验证码输入是否正确
     * @param request 请求
     * @return 验证码输入是否正确
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        // 正确的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 用户输入的验证码
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");

        // 用户输入的验证码非空，并且等于正确的验证码才为真
        return StringUtils.isNotEmpty(verifyCodeExpected)
                && StringUtils.isNotEmpty(verifyCodeActual)
                && verifyCodeActual.toLowerCase().equals(verifyCodeExpected.toLowerCase());
    }

}
