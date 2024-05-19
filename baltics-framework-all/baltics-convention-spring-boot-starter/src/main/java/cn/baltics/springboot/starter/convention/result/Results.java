package cn.baltics.springboot.starter.convention.result;

import cn.baltics.springboot.starter.convention.errorcode.BaseErrorCode;

import java.io.Serializable;
import java.util.Objects;

/**
 *@name Results
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public class Results<T> implements Serializable {
    /**
     * 正确返回码
     */
    public static final int SUCCESS_CODE = 200;

    public static final int ERROR_CODE = 500;

    /**
     * 正确返回信息
     */
    public static final String SUCCESS_MESSAGE = "success";

    /**
     * 返回码
     */
    private int code;

    public int getCode() {
        return code;
    }

    public Results<T> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 返回消息
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public Results<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 响应数据
     */
    private T result;

    public Results<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public T getResult() {
        return result;
    }

    /**
     * 当前返回时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return Objects.equals(SUCCESS_CODE, code);
    }

    public static <T> Results<T> success() {
        return new Results<T>().setCode(SUCCESS_CODE).setMessage(SUCCESS_MESSAGE);
    }

    public static <T> Results<T> success(T data) {
        return new Results<T>().setCode(SUCCESS_CODE).setMessage(SUCCESS_MESSAGE).setResult(data);
    }

    public static <T> Results<T> fail(){
        return new Results<T>().setCode(ERROR_CODE).setMessage(BaseErrorCode.SERVICE_ERROR.message());
    }

    public static <T> Results<T> fail(String errorMsg){
        return new Results<T>().setCode(ERROR_CODE).setMessage(errorMsg);
    }
}
