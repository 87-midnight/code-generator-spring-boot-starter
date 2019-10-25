package org.midnight.generator.query;

import java.util.Collections;
import java.util.List;

public interface AbstractQuery<T> {

    default List<String> getTableNameList(){return Collections.EMPTY_LIST;}

    T queryFieldsInfo(final String tableName);
    T queryTableInfo(final String tableName);
}
