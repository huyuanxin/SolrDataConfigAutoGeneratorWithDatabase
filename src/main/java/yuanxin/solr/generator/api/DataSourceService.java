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
     * @return 排除系统表后的数据库名 {@link List<String>}
     */
    List<String> getDataBaseNameExceptSystemDataBase();

    /**
     * 排除自定义无需生成的数据库
     *
     * @param ignoreDataBaseNameList 自定义无需的生成的数据库名 {@link List<String>}
     * @return 排除自定义无需生成的数据库后的数据库名 {@link List<String>}
     */
    List<String> getDataBaseNameAfterFitter(List<String> ignoreDataBaseNameList);
}
