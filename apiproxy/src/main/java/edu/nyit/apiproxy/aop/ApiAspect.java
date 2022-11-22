package edu.nyit.apiproxy.aop;

import edu.nyit.apiproxy.dao.ApiMapper;
import edu.nyit.apiproxy.entity.BlockList;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

        //方法执行前
        System.out.println("before");

        //获取请求参数
        Object[] args = pjp.getArgs();
        //提取请求参数到map
        Map<String, String> params = (Map<String, String>) args[2];
        //将请求参数拼装成string
        Set<String> keySet = params.keySet();
        StringBuilder sb = new StringBuilder();
        for (String key : keySet) {
            sb.append(params.get(key));
        }

        String paramString = sb.toString();

        boolean flag = false;

        // TODO: 2022/11/3 Ying 从数据库获取blocklist，
        //  检查请求的参数里是否包含黑名单的关键字，如果不包含，则 继续后续流程，如果包含，则不转发该请求
        List<BlockList> blockLists = apiMapper.queryAllBlockList();
        //逐个检查是否包含关键字
        for (BlockList blockList : blockLists) {
            //一旦包含则停止循环
            if (flag) break;

            String keyWord = blockList.getKeywords();
            if (paramString.contains(keyWord)) {
                System.out.println("contains keywords : " + keyWord);
                flag = true;
            }
        }

        // TODO: 2022/11/3  方法执行后，检查服务端返回内容（待定）
        System.out.println("after");

        //正常流程
        if (!flag) {
            //执行target方法
            Object returnVal = pjp.proceed();
            return returnVal;
        } else {
            return "found error, forbidden";
        }

    }
}
