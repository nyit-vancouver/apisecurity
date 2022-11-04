package edu.nyit.apiproxy.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wangtao
 * @date 2022/11/3 19:31
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("edu.nyit.apiproxy.aop")
public class Config {
}
