package yuanxin.solr.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuanxin.solr.generator.api.DataSourceService;
import yuanxin.solr.generator.api.EntityService;
import yuanxin.solr.generator.api.FieldService;
import yuanxin.solr.generator.dao.GetInfoMapper;
import yuanxin.solr.generator.entity.Entity;
import yuanxin.solr.generator.entity.Field;
import yuanxin.solr.generator.entity.IgnoreColumn;
import yuanxin.solr.generator.entity.IgnoreTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:24
 */
@Service("EntityService")
public class EntityServiceImpl implements EntityService {
    final GetInfoMapper getInfoMapper;
    final FieldService fieldService;
    final DataSourceService dataSourceService;

    @Autowired
    public EntityServiceImpl(GetInfoMapper getInfoMapper, FieldService fieldService, DataSourceService dataSourceService) {
        this.getInfoMapper = getInfoMapper;
        this.fieldService = fieldService;
        this.dataSourceService = dataSourceService;
    }

    /**
     * 返回要生成的Entity {@link List<Entity>}
     *
     * @param ignoreDataBaseNameList 不需要的表名
     * @param ignoreTableList        不需要的表名
     * @param ignoreColumnList       不需要的列名
     * @return 需要生成的Entity {@link List<Entity>}
     */
    @Override
    public List<Entity> generatorEntityList(List<String> ignoreDataBaseNameList, List<IgnoreTable> ignoreTableList, List<IgnoreColumn> ignoreColumnList) {
        List<Entity> entityList = new ArrayList<>();
        List<String> dataBaseNameList = dataSourceService.getDataBaseName(ignoreDataBaseNameList);
        // 读取库内所有表
        for (String dataBaseName : dataBaseNameList
        ) {
            List<String> tableNameList = getDataTableName(dataBaseName, ignoreTableList);
            // 生entity
            for (String tableName : tableNameList
            ) {
                List<Field> fieldList = fieldService.generatorFieldList(dataBaseName, tableName, ignoreColumnList);
                // 每个表生成一个Entity
                Entity entity = new Entity(tableName, dataBaseName, generatorQuerySqlCommand(dataBaseName, tableName, ignoreColumnList),
                        generatorDeltaImportQuerySqlCommand(dataBaseName, tableName, ignoreColumnList),
                        generatorDeltaQuerySqlCommand(tableName),
                        fieldList);
                // 加入到List
                entityList.add(entity);
            }
        }
        return entityList;
    }

    /**
     * 构造data-config需要的Query语句
     *
     * @param tableName        表名 {@link String}
     * @param dataBaseName     数据库名 @{@link String}
     * @param ignoreColumnList 不需要的列名 {@link List<IgnoreColumn>}
     * @return 构造的Query语句 {@link List<IgnoreColumn>}
     */
    @Override
    public String generatorQuerySqlCommand(String dataBaseName, String tableName, List<IgnoreColumn> ignoreColumnList) {
        List<String> columnNameList = fieldService.getColumnName(dataBaseName, tableName, ignoreColumnList);
        StringBuilder sql = new StringBuilder();
        // 构造Query
        sql.append("Select ");
        int size = columnNameList.size() - 1;
        for (int i = 0; i < size; i++) {
            sql.append("`").append(columnNameList.get(i)).append("`,");
        }
        sql.append("`").append(columnNameList.get(size)).append("` ").append("From ").append(tableName);
        return sql.toString();
    }

    /**
     * 构造data-config需要的DeltaImportQuery语句
     *
     * @param tableName        表名 {@link String}
     * @param dataBaseName     数据库名 @{@link String}
     * @param ignoreColumnList 不需要的列名 {@link List<IgnoreColumn>}
     * @return 构造的DeltaImportQuery语句 {@link String}
     */
    @Override
    public String generatorDeltaImportQuerySqlCommand(String dataBaseName, String tableName, List<IgnoreColumn> ignoreColumnList) {
        // 此处需要每个表都要一个id字段
        // Select uid,`Name`,`Desc`,create_time,age From demo where uid='${dataimporter.delta.id}'
        String sql = generatorQuerySqlCommand(dataBaseName, tableName, ignoreColumnList);
        return sql + " where uid='${dataimporter.delta.id}'";
    }

    /**
     * 构造data-config需要的DeltaQuery语句
     *
     * @param tableName 表名 {@link String}
     * @return 构造的DeltaQuery语句 {@link String}
     */
    @Override
    public String generatorDeltaQuerySqlCommand(String tableName) {
        return "Select uid Form " + tableName + " where create_time > '${dataimporter.last_index_time}'";
    }

    /**
     * 获得需要的表名
     *
     * @param dataBaseName    数据库名称 {@link String}
     * @param ignoreTableList 不需要的表名 {@link List<IgnoreTable>}
     * @return 表名 {@link String}
     */


    @Override
    public List<String> getDataTableName(String dataBaseName, List<IgnoreTable> ignoreTableList) {
        List<String> ignoreColumnList = new ArrayList<>();
        for (IgnoreTable ignoredTable : ignoreTableList
        ) {
            if (dataBaseName.equals(ignoredTable.getDataBaseName())) {
                ignoreColumnList = ignoredTable.getTableNameList();
            }
        }
        List<String> allTableName = getInfoMapper.getDataBaseTableName(dataBaseName);
        for (String ignoreColumn : ignoreColumnList
        ) {
            allTableName.removeIf(ignoreColumn::equals);
        }
        return allTableName;
    }

}
