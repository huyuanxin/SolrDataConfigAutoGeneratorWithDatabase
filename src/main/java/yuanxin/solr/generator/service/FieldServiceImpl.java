package yuanxin.solr.generator.service;

import org.springframework.stereotype.Service;
import yuanxin.solr.generator.api.FieldService;
import yuanxin.solr.generator.dao.GetInfoMapper;
import yuanxin.solr.generator.entity.Field;

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
     * @param tableName 表名 {@link String}
     * @return 需要生成的Field {@link List<Field>}
     */
    @Override
    public List<Field> generatorFieldList(String tableName, String dataBaseName) {
        List<String> list = getInfoMapper.getTableColumnName(tableName, dataBaseName);
        List<Field> fieldList = new ArrayList<>();
        for (String s : list
        ) {
            fieldList.add(new Field(s, s));
        }
        return fieldList;
    }
}
