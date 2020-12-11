package yuanxin.solr.generator.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:32
 */
@Repository
@Mapper
public interface GetInfoMapper {
    /**
     * 获得表中的列名
     *
     * @param tableName 表名 {@link String}
     * @return 从表中拿到的列名 {@link List<String>}
     */
    List<String> getTableColumnName(String tableName);

    /**
     * 获得数据库中的表名
     *
     * @param dataBaseName 数据库名 {@link String}
     * @return 从数据库中拿到的表名{@link List<String>}
     */
    List<String> getDataBaseTableName(String dataBaseName);

    /**
     * 获得数据库中的数据库名
     *
     * @return 从数据库系统中的数据库名 {@link List<String>}
     */
    List<String> getDataBaseName();
}
