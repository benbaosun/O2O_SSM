package com.lin.dto;

/**
 * @author lkmc2
 * @date 2018/5/27.
 * 结果传输对象
 */

public class Result<T> {

    /*** 是否成功标志 ***/
    private boolean success;

    /*** 成功时返回的数据 ***/
    private T data;

    /*** 错误信息 ***/
    private String errorMsg;

    /*** 错误码 ***/
    private int errorCode;

    public Result() {
    }

    /**
     * 返回成功消息时的构造器
     * @param success 是否成功
     * @param data 数据
     */
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /**
     * 返回失败消息时的构造器
     * @param success 是否成功
     * @param errorCode 错误码
     * @param errorMsg 错误消息
     */
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
