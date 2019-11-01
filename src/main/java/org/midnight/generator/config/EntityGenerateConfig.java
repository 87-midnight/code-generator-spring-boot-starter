package org.midnight.generator.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * entity 单个或多个 实体类配置
 * @author linchuangang
 * @create 2019-10-28 11:42
 **/
@ConfigurationProperties(prefix = "midnight.generate.entity")
@Data
@Builder
public class EntityGenerateConfig {

    private List<String> entityNameList;
}
