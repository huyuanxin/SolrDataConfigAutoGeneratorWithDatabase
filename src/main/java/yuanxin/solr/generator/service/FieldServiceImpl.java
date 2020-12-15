package yuanxin.solr.generator.service;

import org.springframework.stereotype.Service;
import yuanxin.solr.generator.api.FieldService;
import yuanxin.solr.generator.dao.GetInfoMapper;
import yuanxin.solr.generator.entity.Field;
import yuanxin.solr.generator.entity.IgnoreColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:29
 */
@Service("FieldService")
public class FieldServiceImpl implements FieldService {
    final GetInfoMapper getInfoMapper;


    public FieldServiceImpl(GetInfoMapper getInfoMapper) {
        this.getInfoMapper = getInfoMapper;

    }

    /**
     * 返回要生成的Field {@link List<Field>}
     *
     * @param tableName        表名 {@link String}
     * @param dataBaseName     数据库名 {@link String}
     * @param ignoreColumnList 忽略的列名 {@link List<IgnoreColumn> }
     * @return 需要生成的Field {@link List<Field>}
     */
    @Override
    public List<Field> generatorFieldList(String dataBaseName, String tableName, List<IgnoreColumn> ignoreColumnList) {
        // 去除不需要的字段
        List<String> columnNameList = getColumnName(dataBaseName, tableName, ignoreColumnList);
        List<Field> fieldList = new ArrayList<>();
        for (String columnName : columnNameList
        ) {
            fieldList.add(new Field(columnName, columnName));
        }
        return fieldList;
    }

    /**
     * 返回需要的列名
     *
     * @param tableName        表名
     * @param ignoreColumnList 需要排除的列名
     * @return 需要的列名
     */
    @Override
    public List<String> getColumnName(String dataBaseName, String tableName, List<IgnoreColumn> ignoreColumnList) {
        List<String> ignoreList = new ArrayList<>();
        for (IgnoreColumn ignoreColumn : ignoreColumnList
        ) {
            if (tableName.equals(ignoreColumn.getTableName())&&dataBaseName.equals(ignoreColumn.getDataBaseName())) {
                ignoreList = ignoreColumn.getColumnNameList();
            }
        }
        List<String> allColumnList = getInfoMapper.getTableColumnName(dataBaseName, tableName);
        for (String ignoreColumn : ignoreList
        ) {
            allColumnList.removeIf(ignoreColumn::equals);
        }
        return allColumnList;
    }
}
