package org.midnight.generator.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ColumnInfo implements Serializable {


    private Long columnId;

    private String columnSource;

    private Boolean isUpdateColumn;

    private String columnType;

    private String columnTypeFullPackage;

    private Integer columnIndex;

    private Integer columnPrecision;

    private Integer columnScale;

    private String defaultValue;

    private Boolean isPrimaryKey;

    private Boolean nullable;

    private String columnComment;



}
