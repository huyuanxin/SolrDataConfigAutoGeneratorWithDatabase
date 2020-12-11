package yuanxin.solr.generator.entity;

import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:07
 */
public class Entity {
    public String entityName;
    public String entityDataSource;
    public String querySqlCommand;
    public String deltaImportQuery;
    public String deltaQuery;
    public List<Field> fieldList;

    public Entity(String entityName, String entityDataSource, String querySqlCommand, String deltaImportQuery, String deltaQuery, List<Field> fieldList) {
        this.entityName = entityName;
        this.entityDataSource = entityDataSource;
        this.querySqlCommand = querySqlCommand;
        this.deltaImportQuery = deltaImportQuery;
        this.deltaQuery = deltaQuery;
        this.fieldList = fieldList;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityDataSource() {
        return entityDataSource;
    }

    public void setEntityDataSource(String entityDataSource) {
        this.entityDataSource = entityDataSource;
    }

    public String getQuerySqlCommand() {
        return querySqlCommand;
    }

    public void setQuerySqlCommand(String querySqlCommand) {
        this.querySqlCommand = querySqlCommand;
    }

    public String getDeltaImportQuery() {
        return deltaImportQuery;
    }

    public void setDeltaImportQuery(String deltaImportQuery) {
        this.deltaImportQuery = deltaImportQuery;
    }

    public String getDeltaQuery() {
        return deltaQuery;
    }

    public void setDeltaQuery(String deltaQuery) {
        this.deltaQuery = deltaQuery;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }
}
