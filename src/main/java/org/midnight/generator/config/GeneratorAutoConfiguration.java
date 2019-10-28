package org.midnight.generator.config;

import org.midnight.generator.CodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linchuangang
 * @create 2019-10-25 11:24
 **/
@Configuration
//使用EnableConfigurationProperties,方便统一管理需要自动注入的类，不需要每个类都加上component注解
@EnableConfigurationProperties({GeneratorProperties.class, EntitiesConfig.class})
@ConditionalOnProperty(prefix = "midnight.generator",value = "enabled",havingValue = "true")
public class GeneratorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CodeGenerator.class)
    public CodeGenerator codeGenerator(){
        return CodeGenerator.getInstance();
    }
}
