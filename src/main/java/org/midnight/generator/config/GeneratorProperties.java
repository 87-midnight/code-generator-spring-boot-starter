package org.midnight.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author linchuangang
 * @create 2019-10-25 11:22
 **/
@ConfigurationProperties(prefix = "midnight.generator")
@Data
public class GeneratorProperties {
    private Boolean enabled;
}
