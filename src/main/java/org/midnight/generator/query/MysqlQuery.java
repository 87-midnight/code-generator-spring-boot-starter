package org.midnight.generator.query;



/**
 * @author linchuangang
 * @create 2019-10-25 16:13
 **/
public class MysqlQuery implements AbstractQuery<String>{

    @Override
    public String queryFieldsInfo(String tableName) {
        return String.format("show full fields from `%s`", tableName);
    }

    @Override
    public String queryTableInfo(String tableName) {
        return String.format("show table status where Name = '%s'", tableName);
    }
}
