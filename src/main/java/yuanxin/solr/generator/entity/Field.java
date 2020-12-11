package yuanxin.solr.generator.entity;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:06
 */
public class Field {
    public String columnName;
    public String fieldName;

    public Field(String columnName, String fieldName) {
        this.columnName = columnName;
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
