package edu.nyit.apiproxy.dao;

import edu.nyit.apiproxy.entity.BlockList;
import edu.nyit.apiproxy.entity.SourceMatch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangtao
 * @date 2022/11/3 18:27
 */
@Mapper
@Repository
public interface ApiMapper {

    /**
     * get all block list
     * @return
     */
    List<BlockList> queryAllBlockList();

    /**
     * get by id
     * @param id
     * @return
     */
    BlockList queryById(String id);

    /**
     * get the match entity by service name
     * @param serviceName
     * @return
     */
    SourceMatch selectByServiceName(String serviceName);
}
