package org.midnight.generator.query;

/**
 * @author linchuangang
 * @create 2019-10-25 16:14
 **/
public class SqlServerQuery implements AbstractQuery<String> {
    @Override
    public String queryFieldsInfo(String tableName) {
        return "SELECT  cast(a.name AS VARCHAR(500)) AS TABLE_NAME,cast(b.name AS VARCHAR(500)) AS COLUMN_NAME, "
            + "cast(c.VALUE AS NVARCHAR(500)) AS COMMENTS,cast(sys.types.name AS VARCHAR (500)) AS DATA_TYPE,"
            + "(" + " SELECT CASE count(1) WHEN 1 then 'PRI' ELSE '' END"
            + " FROM syscolumns,sysobjects,sysindexes,sysindexkeys,systypes "
            + " WHERE syscolumns.xusertype = systypes.xusertype AND syscolumns.id = object_id (a.name) AND sysobjects.xtype = 'PK'"
            + " AND sysobjects.parent_obj = syscolumns.id " + " AND sysindexes.id = syscolumns.id "
            + " AND sysobjects.name = sysindexes.name AND sysindexkeys.id = syscolumns.id "
            + " AND sysindexkeys.indid = sysindexes.indid "
            + " AND syscolumns.colid = sysindexkeys.colid AND syscolumns.name = b.name) as 'KEY',"
            + "  b.is_identity isIdentity "
            + " FROM ( select name,object_id from sys.tables UNION all select name,object_id from sys.views ) a "
            + " INNER JOIN sys.columns b ON b.object_id = a.object_id "
            + " LEFT JOIN sys.types ON b.user_type_id = sys.types.user_type_id   "
            + " LEFT JOIN sys.extended_properties c ON c.major_id = b.object_id AND c.minor_id = b.column_id "
            + " WHERE a.name = '%s' and sys.types.name !='sysname' ";
    }

    @Override
    public String queryTableInfo(String tableName) {
        return "select cast(so.name as varchar(500)) as TABLE_NAME, " +
            "cast(sep.value as varchar(500)) as COMMENTS from sysobjects so " +
            "left JOIN sys.extended_properties sep on sep.major_id=so.id and sep.minor_id=0 " +
            "where (xtype='U' or xtype='v')";
    }
}
