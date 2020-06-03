package org.midnight.generator.properties;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * created by Annie since 2020/6/3 10:56
 * intro: Annie
 */
@Data
@Accessors(chain = true)
public class DatabaseProperties {

    private String driverClassName;

    private Integer databaseType;

    private String url;

    private String username;

    private String password;
}
