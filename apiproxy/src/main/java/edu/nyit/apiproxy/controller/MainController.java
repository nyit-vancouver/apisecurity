package edu.nyit.apiproxy.controller;

import edu.nyit.apiproxy.service.ApiProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
     * @param param      the param of the request
     * @param serverCode the unique identity number of the server
     * @return
     */
    @RequestMapping("/apiProxy")
    public Object apiProxy(HttpServletRequest request, String param, String serverCode) {


        //analyze the param
        //1,check the black list of sql injection

        //2,check the black list of nosql injection

        //3,check the black list of command injection

        //4,check if there is a white list of the server

        //5,if the request passes all filters, ready to forward it to the server

        //6, search the ip address of the server

        //7,search the url of the server

        //8, prepare the param of the request and forward it to the server

        //9, get the response from the server

        //10, respond the data to the client

        String remoteIp = request.getRemoteAddr();
        String localIp = request.getLocalAddr();
        String url = request.getRequestURI();

        System.out.println("url = " + url);

        System.out.println(remoteIp);
        System.out.println(localIp);

        System.out.println("Received param from the client:" + param);

        apiProxyService.forwardClientRequest("","");

        return "hello world" + param;
    }
}
