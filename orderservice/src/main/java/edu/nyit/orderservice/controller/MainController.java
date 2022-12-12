package edu.nyit.orderservice.controller;

import edu.nyit.orderservice.cons.Const;
import edu.nyit.orderservice.entity.OrderInfo;
import edu.nyit.orderservice.entity.ResponseObj;
import edu.nyit.orderservice.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangtao
 * @date 2022/11/30 15:45
 */
@Controller
public class MainController {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * get the order info by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryOrderById", method = {RequestMethod.GET})
    @ResponseBody
    public Object queryOrderById(String id) {

        return orderMapper.queryOrderById(id);
    }

    /**
     * add a new order
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "/addOrder", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseObj addOrder(OrderInfo orderInfo) {

        if (orderInfo == null) return new ResponseObj(Const.FAIL_CODE, Const.FAIL, null);

        orderMapper.addOrder(orderInfo);

        return new ResponseObj(Const.SUCCESS_CODE, Const.SUCCESS, orderInfo.getId());
    }
}
