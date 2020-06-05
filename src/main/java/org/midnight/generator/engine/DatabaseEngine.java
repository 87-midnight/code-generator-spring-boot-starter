package org.midnight.generator.engine;

import org.midnight.generator.converter.ITypeConvert;
import org.midnight.generator.pojo.TableInfo;
import org.midnight.generator.properties.DatabaseProperties;
import org.midnight.generator.query.AbstractQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * created by Annie since 2020/6/3 11:48
 * intro: Annie
 */
public interface DatabaseEngine {

    AbstractQuery getQuery();

    ITypeConvert getConverter();

    default Connection getConnection(DatabaseProperties properties)throws Exception{
        Class.forName(properties.getDriverClassName());
        return DriverManager.getConnection(properties.getUrl(),properties.getUsername(),properties.getPassword());
    }

    List<TableInfo> queryTableStruct(List<String> tableNameList)throws Exception;

}
