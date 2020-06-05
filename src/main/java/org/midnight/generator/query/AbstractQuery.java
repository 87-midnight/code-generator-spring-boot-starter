package org.midnight.generator.query;

import java.util.Collections;
import java.util.List;

public interface AbstractQuery<T> {

    T queryFieldsInfo(final String tableName);
    T queryTableInfo(final String tableName);


    /**
     * 表名称
     */
    String tableName();


    /**
     * 表注释
     */
    String tableComment();


    /**
     * 字段名称
     */
    String fieldName();


    /**
     * 字段类型
     */
    String fieldType();


    /**
     * 字段注释
     */
    String fieldComment();

    /**
     * 主键字段
     */
    String fieldKey();
}
