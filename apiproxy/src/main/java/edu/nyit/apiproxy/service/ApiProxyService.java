package edu.nyit.apiproxy.service;

import edu.nyit.apiproxy.entity.BlockList;
import edu.nyit.apiproxy.dao.ApiMapper;
import edu.nyit.apiproxy.entity.SourceMatch;
import edu.nyit.apiproxy.util.HttpUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangtao
 * @date 2022/10/18 11:02
 */
@Component
public class ApiProxyService {

    @Autowired
    private ApiMapper apiMapper;

    public Object forwardClientRequest(String url, String method, Map<String, String> params) {

        SourceMatch sourceMatch = apiMapper.selectByCode(Integer.parseInt(params.get("code")));

        if (sourceMatch == null) {
            throw new RuntimeException("server not exists error");
        }

        String sourceURL = transformURL(url, sourceMatch.getUrl());

        System.out.println("method = " + method);
        Object result = null;
        if (RequestMethod.GET.equals(method)) {
            result = HttpUtils.get(sourceURL, new HashMap<>());
        } else if (RequestMethod.POST.equals(method)) {
            result = HttpUtils.post(url, new HashMap<>(), params);
        }

        return result;
    }


    /**
     * url 地址转换
     * 比如 127.0.0.1:8080/apiproxy/ 转换成 12.34.45.78:9090/testserver1
     *
     * @param originalURL
     * @param sourceURL
     * @return
     */
    private String transformURL(String originalURL, String sourceURL) {
        // TODO: 2022/11/14
        System.out.println("originalURL = " + originalURL);
        System.out.println("sourceURL = " + sourceURL);
        return "";
    }
}
