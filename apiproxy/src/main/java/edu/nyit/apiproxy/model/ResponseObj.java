package edu.nyit.apiproxy.model;

/**
 * response object
 * @author wangtao
 * @date 2022/11/29 0:25
 */
public class ResponseObj {

    /***
     * 1 true normal request
     * 0 false blocked request
     */
    private String code;

    private Object data;

    public ResponseObj(String code, Object data) {
        this.code = code;
        this.data = data;
    }

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
}
