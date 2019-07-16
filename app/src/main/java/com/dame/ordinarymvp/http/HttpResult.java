package com.dame.ordinarymvp.http;

/**
 * @author 姚明亮
 * @date 2019/7/9
 */
public class HttpResult<T> {

    /**
     * info :
     * code : 100
     * object : {}
     * refrsh : true
     */
    private String SUCCESS = "1";
    private String FAIL = "0";
    private String OVERTIME = "102";
    public String info;
    public String code;
    public T data;
    public boolean refrsh;



    public boolean isSuccess() {
        return SUCCESS.equals(code);
    }

    public boolean isFail(){
        return FAIL.equals(code);
    }

    public boolean isOvertime(){
        return OVERTIME.equals(code);
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "info='" + info + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", refrsh=" + refrsh +
                '}';
    }
}
