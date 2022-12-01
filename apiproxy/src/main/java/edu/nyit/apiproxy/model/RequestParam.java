package edu.nyit.apiproxy.model;

import java.util.Map;

/**
 * Request parameter
 * @author wangtao
 * @date 2022/11/21 19:55
 */
public class RequestParam {

    Map<String,String> param;

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
