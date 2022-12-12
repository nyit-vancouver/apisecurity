package edu.nyit.apiproxy.controller;

import edu.nyit.apiproxy.model.ResponseObj;
import edu.nyit.apiproxy.service.ApiProxyService;
import edu.nyit.apiproxy.util.UrlCoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtao
 * @date 2022/10/18 10:52
 */
@RestController("/apiProxy")
public class MainController {

    @Autowired
    private ApiProxyService apiProxyService;


    /**
     * get request
     *
     * @return
     */
    @GetMapping(value = "/**")
    public Object getMapping(HttpServletRequest request) {

        System.out.println(request.getRequestURL());
        String uri = parseURI(request.getRequestURI());
        String requestType = request.getMethod();

        Enumeration<String> enumeration = request.getHeaderNames();
        String queryStr = request.getQueryString();
        boolean hasEncode = UrlCoderUtil.hasEnCode(queryStr);
        if(hasEncode){
            queryStr = UrlCoderUtil.decode(queryStr);
        }

        Map<String, String> paramMap = parseParam(queryStr);
        //forward or block request and get result
        ResponseObj result = (ResponseObj) apiProxyService.forwardClientRequest(uri, request.getQueryString(), requestType, paramMap);

        return result.getData();

    }

    /**
     * post request
     *
     * @param request
     * @param param   the param of the request
     * @return
     */
    @PostMapping(value = "/**")
    public Object postMapping(HttpServletRequest request, @RequestParam Map<String, String> param) {

        String uri = parseURI(request.getRequestURI());

        String requestType = request.getMethod();

        //forward or block request and get result
        ResponseObj result = (ResponseObj) apiProxyService.forwardClientRequest(uri, request.getQueryString(), requestType, param);

        return result.getData();
    }


    /**
     * get destination uri
     * /apiProxy/orderservice/queryOrderById -> /orderservice/queryOrderById
     *
     * @param requestURI
     * @return
     */
    private String parseURI(String requestURI) {
        int len = requestURI.length();
        return requestURI.substring(9, len);
    }


    /**
     * parse request params from queryString
     * a=b&c=d&e=f
     *
     * @param queryString
     * @return
     */
    private Map<String, String> parseParam(String queryString) {

        Map<String, String> paramMap = new HashMap<>();

        if(queryString == null) return paramMap;

        String[] array = queryString.split("&");
        for (String str : array) {
            String[] kv = str.split("=");
            paramMap.put(kv[0], kv[1]);
        }
        return paramMap;
    }

}
