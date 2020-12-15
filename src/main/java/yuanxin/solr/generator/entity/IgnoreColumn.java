package yuanxin.solr.generator.entity;

import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/14 18:28
 */
public class IgnoreColumn {
    String dataBaseName;
    String tableName;
    List<String> columnNameList;

    public IgnoreColumn(String dataBaseName, String tableName, List<String> columnNameList) {
        this.dataBaseName = dataBaseName;
        this.tableName = tableName;
        this.columnNameList = columnNameList;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }
}
