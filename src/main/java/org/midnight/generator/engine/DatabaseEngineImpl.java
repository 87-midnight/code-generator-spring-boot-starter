package org.midnight.generator.engine;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.midnight.generator.pojo.*;
import org.midnight.generator.properties.DatabaseProperties;
import org.midnight.generator.query.AbstractQuery;
import org.midnight.generator.query.MysqlQuery;
import org.midnight.generator.query.OracleQuery;
import org.midnight.generator.query.PostgresQuery;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * created by Annie since 2020/6/3 16:16
 * intro: Annie
 */
@Slf4j
public class DatabaseEngineImpl implements DatabaseEngine{

    private DatabaseProperties properties;

    public DatabaseEngineImpl(DatabaseProperties properties) {
        Assert.notNull(properties,"数据库信息不能为空");
        Assert.notNull(properties.getDriverClassName(),"数据库信息不能为空");
        Assert.notNull(properties.getDatabaseType(),"数据库信息不能为空");
        Assert.notNull(properties.getUrl(),"数据库信息不能为空");
        Assert.notNull(properties.getUsername(),"数据库信息不能为空");
        Assert.notNull(properties.getPassword(),"数据库信息不能为空");
        this.properties = properties;
    }

    @Override
    public AbstractQuery getQuery() {
        if (properties.getDatabaseType() == DatabaseEnum.mysql.getType()){
            return new MysqlQuery();
        }else if (properties.getDatabaseType() == DatabaseEnum.oracle.getType()){
            return new OracleQuery();
        }else if (properties.getDatabaseType() == DatabaseEnum.postgres.getType()){
            return new PostgresQuery();
        }
        return null;
    }

    @Override
    public List<TableInfo> queryTableStruct(List<String> tableNameList) throws Exception {
        List<TableInfo> tableInfoList = new ArrayList<>();
        try(Connection connection = getConnection(properties)) {
            DatabaseMetaData metaData = connection.getMetaData();
            //精准匹配表，获取主键信息
            for (String tableName : tableNameList){
                TableInfo tableInfo = new TableInfo();
                List<ColumnInfo> columnInfos = new ArrayList<>();

                tableInfo.setColumns(columnInfos);
                tableInfo.setTableName(tableName);
                tableInfoList.add(tableInfo);

            }
        }catch (Exception var1){

        }
        return null;
    }
}
