package edu.nyit.apiproxy.service;

import edu.nyit.apiproxy.constant.Cons;
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

    public Object forwardClientRequest(String uri, String queryStr, String method, Map<String, String> params) {

        String serviceName = parseServiceName(uri);

        SourceMatch sourceMatch = apiMapper.selectByServiceName(serviceName);

        if (sourceMatch == null) {
            throw new RuntimeException("server not exists error");
        }

        String sourceURL = transformURL(uri, queryStr, method, sourceMatch);

        System.out.println("destination url: " + sourceURL);

        Object result = null;

        if (Cons.GET.equals(method)) {
            result = HttpUtils.get(sourceURL, new HashMap<>());
        } else if (Cons.POST.equals(method)) {
            result = HttpUtils.post(sourceURL, new HashMap<>(), params);
        }

        return result;
    }

    /**
     * transform url
     * for example:  127.0.0.1:8080/apiproxy/ -> 12.34.45.78:9090/testserver1
     *
     * @return
     */
    private String transformURL(String uri, String queryStr, String method, SourceMatch sourceMatch) {

        if (Cons.GET.equals(method)) {
            return "http://" + sourceMatch.getServiceIp() + ":" + sourceMatch.getServicePort() + "/" + uri + "?" + queryStr;
        } else if (Cons.POST.equals(method)) {
            return "http://" + sourceMatch.getServiceIp() + ":" + sourceMatch.getServicePort() + "/" + uri;
        }

        return "";
    }

    private String parseServiceName(String uri) {
        String[] strings = uri.split("/");
        return strings[1];
    }


    /**
     *
     * @param id
     * @return
     */
    public BlockList queryById(String id) {
        return apiMapper.queryById(id);
    }

    /**
     *
     * @return
     */
    public List<BlockList> queryAllBlockList() {
        return apiMapper.queryAllBlockList();
    }
}
