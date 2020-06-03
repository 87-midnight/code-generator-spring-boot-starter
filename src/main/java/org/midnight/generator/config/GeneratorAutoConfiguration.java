package org.midnight.generator.config;

import org.midnight.generator.engine.CodeGenerator;
import org.midnight.generator.properties.GeneratorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author linchuangang
 * @create 2019-10-25 11:24
 **/
@Configuration
//使用EnableConfigurationProperties,方便统一管理需要自动注入的类，不需要每个类都加上component注解
@EnableConfigurationProperties({GeneratorProperties.class})
@ConditionalOnProperty(prefix = "midnight.generator",value = "enabled",havingValue = "true")
public class GeneratorAutoConfiguration implements Ordered {

    @Bean
    @ConditionalOnMissingBean(CodeGenerator.class)
    public CodeGenerator codeGenerator(){
        return new CodeGenerator();
    }

    @Override
    public int getOrder() {
        return -999;
    }
}
