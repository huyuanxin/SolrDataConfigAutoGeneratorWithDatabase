package yuanxin.solr.generator.api;


import org.springframework.stereotype.Service;
import yuanxin.solr.generator.entity.Entity;

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
     * @return 需要生成的Entity {@link List<Entity>}
     */
    List<Entity> generatorEntityList();

    /**
     * 构造data-config需要的Query语句
     *
     * @param tableName 表名 {@link String}
     * @return 构造的Query语句
     */
    String generatorQuerySqlCommand(String tableName);

    /**
     * 构造data-config需要的DeltaImportQuery语句
     *
     * @param tableName 表名
     * @return 构造的DeltaImportQuery语句
     */
    String generatorDeltaImportQuerySqlCommand(String tableName);

    /**
     * 构造data-config需要的DeltaQuery语句
     *
     * @param tableName 表名
     * @return 构造的DeltaQuery语句
     */
    String generatorDeltaQuerySqlCommand(String tableName);
}
