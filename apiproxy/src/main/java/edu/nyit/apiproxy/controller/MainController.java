package edu.nyit.apiproxy.controller;

import edu.nyit.apiproxy.constant.Cons;
import edu.nyit.apiproxy.model.RequestParam;
import edu.nyit.apiproxy.model.ResponseObj;
import edu.nyit.apiproxy.service.ApiProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtao
 * @date 2022/10/18 10:52
 */
@RestController
public class MainController {

    @Autowired
    private ApiProxyService apiProxyService;

    /**
     * @param request
     * @param param   the param of the request
     * @return
     */
    @RequestMapping(value = "/apiProxy", method = {RequestMethod.GET, RequestMethod.POST})
    public Object apiProxy(HttpServletRequest request, @RequestBody RequestParam param) {


        String requestType = request.getMethod();
        String queryString = request.getQueryString();

        //collect all parameters of the request.
        Map<String,String> paramMap = null;

        //get parameters by request type
        if(requestType.equals(Cons.GET)){
            paramMap = parseParam(queryString);
        }else{
            paramMap = param.getParam();
        }

        //forward or block request and get result
        ResponseObj result = (ResponseObj)apiProxyService.forwardClientRequest(paramMap.get(Cons.SERVICE_NAME), requestType, paramMap);

        return result.getData();
    }

    /**
     * parse request params from queryString
     * a=b&c=d&e=f
     * @param queryString
     * @return
     */
    private Map<String, String> parseParam(String queryString) {

        Map<String, String> paramMap = new HashMap<>();
        String[] array = queryString.split("&");
        for(String str : array){
            String[] kv = str.split("=");
            paramMap.put(kv[0],kv[1]);
        }
        return paramMap;
    }
}
