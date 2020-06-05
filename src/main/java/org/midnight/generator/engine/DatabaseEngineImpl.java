package org.midnight.generator.engine;

import com.baomidou.mybatisplus.generator.config.po.TableField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.midnight.generator.converter.*;
import org.midnight.generator.pojo.*;
import org.midnight.generator.properties.DatabaseProperties;
import org.midnight.generator.query.*;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

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
        }else if (properties.getDatabaseType() == DatabaseEnum.sqlserver.getType()){
            return new SqlServerQuery();
        }
        return null;
    }

    @Override
    public ITypeConvert getConverter() {
        if (properties.getDatabaseType() == DatabaseEnum.mysql.getType()){
            return new MySqlTypeConvert();
        }else if (properties.getDatabaseType() == DatabaseEnum.oracle.getType()){
            return new OracleTypeConvert();
        }else if (properties.getDatabaseType() == DatabaseEnum.postgres.getType()){
            return new PostgresTypeConvert();
        }else if (properties.getDatabaseType() == DatabaseEnum.sqlserver.getType()){
            return new SqlServerTypeConvert();
        }
        return null;
    }

    @Override
    public List<TableInfo> queryTableStruct(List<String> tableNameList) throws Exception {
        List<TableInfo> tableInfoList = new ArrayList<>();
        try(Connection connection = getConnection(properties)) {
            for (String tableName : tableNameList){
                TableInfo tableInfo = new TableInfo();
                List<ColumnInfo> columnInfos = new ArrayList<>();
                String sql = (String) getQuery().queryFieldsInfo(tableName);
                try (
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet results = preparedStatement.executeQuery()) {
                    while (results.next()) {
                        ColumnInfo column = new ColumnInfo();
                        String columnName = results.getString(getQuery().fieldName());
                        // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
                        String key = results.getString(getQuery().fieldKey());
                        boolean isId = StringUtils.isNotBlank(key) && "PRI".equals(key.toUpperCase());
                        column.setColumnSource(columnName);
                        column.setIsPrimaryKey(isId);

                        IColumnType type = getConverter().processTypeConvert(results.getString(getQuery().fieldType()));
                        column.setColumnType(type.getType());
                        column.setColumnTypeFullPackage(type.getPkg());

                        String comment = results.getString(getQuery().fieldComment());

                        column.setColumnComment(comment);
                        columnInfos.add(column);
                    }
                }
                tableInfo.setColumns(columnInfos);
                tableInfo.setTableName(tableName);
                tableInfoList.add(tableInfo);

            }
        }catch (Exception var1){

        }
        return null;
    }
}
