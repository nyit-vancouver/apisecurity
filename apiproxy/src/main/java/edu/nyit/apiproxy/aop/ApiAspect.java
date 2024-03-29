package edu.nyit.apiproxy.aop;

import edu.nyit.apiproxy.constant.Cons;
import edu.nyit.apiproxy.dao.ApiMapper;
import edu.nyit.apiproxy.entity.BlockList;
import edu.nyit.apiproxy.model.ResponseObj;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author wangtao
 * @date 2022/11/3 19:32
 */
@Aspect
@Component
public class ApiAspect {

    @Autowired
    private ApiMapper apiMapper;

    /**
     * 定义切点
     */
    @Pointcut("execution(* forwardClientRequest(..))")
    public void checkBlockList() {
    }

    /**
     * 环绕通知，检查参数，仅转发执行符合条件的请求
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("edu.nyit.apiproxy.aop.ApiAspect.checkBlockList()")
    public Object doCheckBlockList(ProceedingJoinPoint pjp) throws Throwable {

        //获取请求参数
        Object[] args = pjp.getArgs();
        //提取请求参数到map
        Map<String, String> params = (Map<String, String>) args[3];
        //将请求参数拼装成string
        Set<String> keySet = params.keySet();
        StringBuilder sb = new StringBuilder();
        for (String key : keySet) {
            sb.append(params.get(key));
        }

        String paramString = sb.toString();

        boolean flag = false;

        List<BlockList> blockLists = apiMapper.queryAllBlockList();

        //default as normal response
        ResponseObj responseObj = new ResponseObj(Cons.NORMAL,null);

        //逐个检查是否包含关键字
        for (BlockList blockList : blockLists) {
            //一旦包含则停止循环
            if (flag) break;

            String keyWord = blockList.getKeywords();
            if (paramString.contains(keyWord)) {
                System.out.println("Contains malicious keywords : " + keyWord);
                responseObj.setCode(Cons.BLOCK);
                flag = true;
            }
        }

        //正常流程
        if (!flag) {
            //执行target方法
            Object returnVal = pjp.proceed();
            responseObj.setData(returnVal);
            return responseObj;
        } else {
            return responseObj;
        }

    }
}
