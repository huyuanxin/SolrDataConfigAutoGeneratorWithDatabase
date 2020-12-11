package yuanxin.solr.generator.api;


import org.springframework.stereotype.Service;
import yuanxin.solr.generator.entity.DataSource;

import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:17
 */
@Service("DataSourceService")
public interface DataSourceService {
    /**
     * 返回需要生成的DataSource {@link List<DataSource>}
     *
     * @return 需要生成的DataSource {@link List<DataSource>}
     */
    List<DataSource> generatorDataSourceList();

    /**
     * 获得无系统表的数据库表名
     *
     * @return 排除系统表后的数据库名 {@link String}
     */
    List<String> getDataBaseNameExceptSystemDataBase();

}
