package yuanxin.solr.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuanxin.solr.generator.api.DataSourceService;
import yuanxin.solr.generator.api.EntityService;
import yuanxin.solr.generator.api.FieldService;
import yuanxin.solr.generator.dao.GetInfoMapper;
import yuanxin.solr.generator.entity.Entity;
import yuanxin.solr.generator.entity.Field;

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
     * @return 需要生成的Entity {@link List<Entity>}
     */
    @Override
    public List<Entity> generatorEntityList() {

        List<Entity> entityList = new ArrayList<>();
        //获得数据库内除系统表外所有数据库名
        List<String> dataBaseNameList = dataSourceService.getDataBaseNameExceptSystemDataBase();
        // 读取库内所有表
        for (String dataBaseName : dataBaseNameList
        ) {
            List<String> tableNameList = getInfoMapper.getDataBaseTableName(dataBaseName);
            for (String tableName : tableNameList
            ) {
                // 读取每个表的列名
                List<Field> fieldList = fieldService.generatorFieldList(tableName);
                // 每个表生成一个Entity
                Entity entity = new Entity(tableName, dataBaseName, generatorQuerySqlCommand(tableName),
                        generatorDeltaImportQuerySqlCommand(tableName),
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
     * @param tableName 表名
     * @return 构造的Query语句
     */
    @Override
    public String generatorQuerySqlCommand(String tableName) {
        // Select uid,`Name`,`Desc`,create_time,age From demo
        //获得列
        List<String> columnNameList = getInfoMapper.getTableColumnName(tableName);
        StringBuilder sql = new StringBuilder();
        // 构造Query
        sql.append("Select ");
        int size = columnNameList.size() - 1;
        for (int i = 0; i < size - 1; i++) {
            sql.append("`").append(columnNameList.get(i)).append("`,");
        }
        sql.append("`").append(columnNameList.get(size)).append("` ").append("From ").append(tableName);
        return sql.toString();
    }

    /**
     * 构造data-config需要的DeltaImportQuery语句
     *
     * @param tableName 表名
     * @return 构造的DeltaImportQuery语句
     */
    @Override
    public String generatorDeltaImportQuerySqlCommand(String tableName) {
        // 此处需要每个表都要一个id字段
        // Select uid,`Name`,`Desc`,create_time,age From demo where uid='${dataimporter.delta.id}'
        String sql = generatorQuerySqlCommand(tableName);
        return sql + " where uid='${dataimporter.delta.id}'";
    }

    /**
     * 构造data-config需要的DeltaQuery语句
     *
     * @param tableName 表名
     * @return 构造的DeltaQuery语句
     */
    @Override
    public String generatorDeltaQuerySqlCommand(String tableName) {
        // 此处需要每个表都要一个create_time字段
        // select uid from demo where create_time &gt; '${dataimporter.last_index_time}'
        return "Select uid Form " + tableName + " where create_time > '${dataimporter.last_index_time}'";
    }
}
