package yuanxin.solr.generator.entity;

import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/14 14:31
 */
public class IgnoreTable {
    String dataBaseName;
    List<String> tableNameList;

    public IgnoreTable(String dataBaseName, List<String> tableNameList) {
        this.dataBaseName = dataBaseName;
        this.tableNameList = tableNameList;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public List<String> getTableNameList() {
        return tableNameList;
    }

    public void setTableNameList(List<String> tableNameList) {
        this.tableNameList = tableNameList;
    }

    @Override
    public String toString() {
        return "IgnoreTable{" +
                "dataBaseName='" + dataBaseName + '\'' +
                ", tableNameList=" + tableNameList +
                '}';
    }
}
