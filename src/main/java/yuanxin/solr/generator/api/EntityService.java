package yuanxin.solr.generator.api;


import org.apache.ibatis.annotations.Param;
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
     * @param tableName    表名 {@link String}
     * @param dataBaseName 数据库名 @{@link String}
     * @return 构造的Query语句
     */
    String generatorQuerySqlCommand(String tableName, String dataBaseName);

    /**
     * 构造data-config需要的DeltaImportQuery语句
     *
     * @param tableName    表名 {@link String}
     * @param dataBaseName 数据库名 @{@link String}
     * @return 构造的DeltaImportQuery语句 {@link String}
     */
    String generatorDeltaImportQuerySqlCommand(String tableName, String dataBaseName);

    /**
     * 构造data-config需要的DeltaQuery语句
     *
     * @param tableName 表名 {@link String}
     * @return 构造的DeltaQuery语句 {@link String}
     */
    String generatorDeltaQuerySqlCommand(String tableName);
}
