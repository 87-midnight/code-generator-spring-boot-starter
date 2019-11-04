package org.midnight.generator.converter.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author linchuangang
 * @create 2019-10-25 16:12
 **/
@Data
@Builder
public class FieldInfo {

    private String fieldName;
    private String propertyType;
    private String propertyName;
    private boolean isPrimaryKey;
    private String comment;
}
