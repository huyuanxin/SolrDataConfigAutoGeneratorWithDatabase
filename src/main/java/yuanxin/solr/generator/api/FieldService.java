package yuanxin.solr.generator.api;


import org.springframework.stereotype.Service;
import yuanxin.solr.generator.entity.Field;

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
     * @param tableName    表名 {@link String}
     * @param dataBaseName 数据库名 {@link String}
     * @return 需要生成的Field {@link List<Field>}
     */
    List<Field> generatorFieldList(String tableName, String dataBaseName);
}
