package yuanxin.solr.generator.api;


import org.springframework.stereotype.Service;
import yuanxin.solr.generator.entity.Field;
import yuanxin.solr.generator.entity.IgnoreColumn;

import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:16
 */
@Service("FieldService")
public interface FieldService {
    /**
     * 返回要生成的Field {@link List<Field>}
     *
     * @param tableName        表名 {@link String}
     * @param dataBaseName     数据库名 {@link String}
     * @param ignoreColumnList 忽略的列名 {@link List<IgnoreColumn> }
     * @return 需要生成的Field {@link List<Field>}
     */
    List<Field> generatorFieldList(String dataBaseName, String tableName, List<IgnoreColumn> ignoreColumnList);

    /**
     * 返回需要的列名
     *
     * @param tableName        表名 {@link String}
     * @param ignoreColumnList 需要排除的列名 {@link List<IgnoreColumn>}
     * @param dataBaseName     数据库名称 {@link String}
     * @return 需要的列名 {@link List<String>}
     */
    List<String> getColumnName(String dataBaseName, String tableName, List<IgnoreColumn> ignoreColumnList);
}
