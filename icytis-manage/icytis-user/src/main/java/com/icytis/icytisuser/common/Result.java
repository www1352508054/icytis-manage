package com.icytis.icytisuser.common;

public class Result<T> {
    private String code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    public static <T> Result<T> error(String code, String message){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
