package yuanxin.solr.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import yuanxin.solr.generator.api.DataSourceService;
import yuanxin.solr.generator.dao.GetInfoMapper;
import yuanxin.solr.generator.entity.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:29
 */
@Service("DataSourceService")
@PropertySource(value = {"classpath:application.properties"})
public class DataSourceServiceImpl implements DataSourceService {
    final GetInfoMapper getInfoMapper;

    @Value("${spring.datasource.driver-class-name}")
    String dataBaseDriverClassName;

    @Value("${spring.datasource.username}")
    String dataBaseUserName;

    @Value("${spring.datasource.password}")
    String dataBasePassword;

    @Value("${spring.datasource.url}")
    String dataBaseUrl;

    @Autowired
    public DataSourceServiceImpl(GetInfoMapper getInfoMapper) {
        this.getInfoMapper = getInfoMapper;
    }

    /**
     * 返回需要生成的DataSource {@link List<DataSource>}
     *
     * @return 需要生成的DataSource {@link List<DataSource>}
     */
    @Override
    public List<DataSource> generatorDataSourceList() {
        List<DataSource> dataSourceList = new ArrayList<>();
        // 去除系统表和自定义表
        List<String> ignoreDataBaseNameList = new ArrayList<>();
        ignoreDataBaseNameList.add("chenrui");
        List<String> dataBaseNameList = getDataBaseName(ignoreDataBaseNameList);
        DataSource dataSource = new DataSource();
        dataSource.setDataConfigDriver(dataBaseDriverClassName);
        dataSource.setDataConfigUser(dataBaseUserName);
        dataSource.setDataConfigPassword(dataBasePassword);
        for (String dataBaseName : dataBaseNameList
        ) {
            dataSource.setDataConfigName(dataBaseName);
            dataSource.setDataConfigUrl(dataBaseUrl + dataBaseName);
            dataSourceList.add(dataSource);
        }
        return dataSourceList;
    }

    /**
     * 排除自定义无需生成的数据库
     *
     * @param ignoreDataBaseNameList 自定义无需的生成的数据库名 {@link List<String>}
     * @return 排除自定义无需生成的数据库后的数据库名 {@link List<String>}
     */
    @Override
    public List<String> getDataBaseName(List<String> ignoreDataBaseNameList) {
        List<String> dataBaseNameList = getInfoMapper.getDataBaseName();
        // 去除系统表
        dataBaseNameList.removeIf("information_schema"::equals);
        dataBaseNameList.removeIf("mysql"::equals);
        dataBaseNameList.removeIf("performance_schema"::equals);
        dataBaseNameList.removeIf("sys"::equals);
        dataBaseNameList.removeIf("world"::equals);
        dataBaseNameList.removeIf("sakila"::equals);
        // 去除指定表
        for (String ignoreDataBaseName : ignoreDataBaseNameList
        ) {
            dataBaseNameList.removeIf(ignoreDataBaseName::equals);
        }
        return dataBaseNameList;
    }
}
