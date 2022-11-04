package edu.nyit.apiproxy.service;

import edu.nyit.apiproxy.entity.BlockList;
import edu.nyit.apiproxy.dao.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangtao
 * @date 2022/10/18 11:02
 */
@Component
public class ApiProxyService {

    @Autowired
    private ApiMapper apiMapper;

    public String forwardClientRequest(String serverCode,String param){

        return "success";
    }
}
