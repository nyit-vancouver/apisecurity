package edu.nyit.orderservice.mapper;

import edu.nyit.orderservice.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wangtao
 * @date 2022/11/30 15:38
 */
@Mapper
@Repository
public interface OrderMapper {

    /**
     * get order by id
     * @param id
     * @return
     */
     OrderInfo queryOrderById(String id);

    /**
     * add new order
     * @param orderInfo
     * @return
     */
     void addOrder(OrderInfo orderInfo);

}
