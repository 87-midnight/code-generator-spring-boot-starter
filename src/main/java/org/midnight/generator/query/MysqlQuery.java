package org.midnight.generator.query;



/**
 * @author linchuangang
 * @create 2019-10-25 16:13
 **/
public class MysqlQuery implements AbstractQuery<String>{

    @Override
    public String queryFieldsInfo(String tableName) {
        return "show full fields from `%s`";
    }

    @Override
    public String queryTableInfo(String tableName) {
        return "show table status";
    }
}
