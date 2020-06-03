package org.midnight.generator.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class TableInfo implements Serializable {

    private String tableName;

    private String tableComment;

    private List<ColumnInfo> columns;

}
