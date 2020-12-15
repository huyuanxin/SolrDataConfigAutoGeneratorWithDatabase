package yuanxin.solr.generator.api;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import yuanxin.solr.generator.entity.Entity;
import yuanxin.solr.generator.entity.IgnoreColumn;
import yuanxin.solr.generator.entity.IgnoreTable;

import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:16
 */
@Service("EntityService")
public interface EntityService {
    /**
     * 返回要生成的Entity {@link List<Entity>}
     *
     * @param ignoreDataBaseNameList 不需要的表名 {@link List<String>}
     * @param ignoreTableList        不需要的表名 {@link List<IgnoreTable>}
     * @param ignoreColumnList       不需要的列名 {@link List<IgnoreColumn>}
     * @return 需要生成的Entity {@link List<Entity>}
     */
    List<Entity> generatorEntityList(List<String> ignoreDataBaseNameList, List<IgnoreTable> ignoreTableList, List<IgnoreColumn> ignoreColumnList);

    /**
     * 构造data-config需要的Query语句
     *
     * @param tableName        表名 {@link String}
     * @param dataBaseName     数据库名 @{@link String}
     * @param ignoreColumnList 不需要的列名 {@link List<IgnoreColumn>}
     * @return 构造的Query语句 {@link List<IgnoreColumn>}
     */
    String generatorQuerySqlCommand(String tableName, String dataBaseName, List<IgnoreColumn> ignoreColumnList);

    /**
     * 构造data-config需要的DeltaImportQuery语句
     *
     * @param tableName        表名 {@link String}
     * @param dataBaseName     数据库名 @{@link String}
     * @param ignoreColumnList 不需要的列名 {@link List<IgnoreColumn>}
     * @return 构造的DeltaImportQuery语句 {@link String}
     */
    String generatorDeltaImportQuerySqlCommand(String tableName, String dataBaseName, List<IgnoreColumn> ignoreColumnList);

    /**
     * 构造data-config需要的DeltaQuery语句
     *
     * @param tableName 表名 {@link String}
     * @return 构造的DeltaQuery语句 {@link String}
     */
    String generatorDeltaQuerySqlCommand(String tableName);

    /**
     * 获得需要的表名
     *
     * @param dataBaseName    数据库名称 {@link String}
     * @param ignoreTableList 不需要的表名 {@link List<IgnoreTable>}
     * @return 表名 {@link String}
     */
    List<String> getDataTableName(String dataBaseName, List<IgnoreTable> ignoreTableList);
}
