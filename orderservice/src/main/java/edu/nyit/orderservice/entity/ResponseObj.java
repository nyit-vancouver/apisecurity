package edu.nyit.orderservice.entity;

/**
 * @author wangtao
 * @date 2022/11/30 15:47
 */
public class ResponseObj {

    /**
     * code for success or fail
     */
    private String code;

    /**
     * success or fail
     */
    private String msg;

    /**
     * data
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseObj() {
    }

    public ResponseObj(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
