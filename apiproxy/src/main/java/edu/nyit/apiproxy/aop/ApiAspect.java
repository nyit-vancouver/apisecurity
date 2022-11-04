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
    public void checkBlockList(){}

    /**
     * 环绕通知，检查参数，仅转发执行符合条件的请求
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("edu.nyit.apiproxy.aop.ApiAspect.checkBlockList()")
    public Object doCheckBlockList(ProceedingJoinPoint pjp) throws Throwable{

        //方法执行前
        System.out.println("before");
        // TODO: 2022/11/3 Ying 从数据库获取blocklist，
        //  检查请求的参数里是否包含黑名单的关键字，如果不包含，则 继续后续流程，如果包含，则不转发该请求

        //执行target方法
        Object returnVal = pjp.proceed();

        // TODO: 2022/11/3  方法执行后，检查服务端返回内容（待定）
        System.out.println("after");

        return returnVal;
    }
}
