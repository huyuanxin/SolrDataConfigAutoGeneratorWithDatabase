package yuanxin.solr.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DataSourceServiceImpl implements DataSourceService {
    final GetInfoMapper getInfoMapper;

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
        // 去除系统表
        List<String> dataBaseNameList=getDataBaseNameExceptSystemDataBase();
        for (String dataBaseName : dataBaseNameList
        ) {
            DataSource dataSource = new DataSource();
            dataSource.setDataConfigDriver("com.mysql.cj.jdbc.Driver");
            dataSource.setDataConfigUser("root");
            dataSource.setDataConfigPassword("root");
            dataSource.setDataConfigName(dataBaseName);
            dataSource.setDataConfigUrl("jdbc:mysql://localhost:3306/" + dataBaseName);
            dataSourceList.add(dataSource);
        }
        return dataSourceList;
    }

    /**
     * 获得无系统表的数据库表名
     *
     * @return 排除系统表后的数据库名 {@link String}
     */
    @Override
    public List<String> getDataBaseNameExceptSystemDataBase() {
        List<String> dataBaseNameList = getInfoMapper.getDataBaseName();
        // 去除系统表
        dataBaseNameList.removeIf("information_schema"::equals);
        dataBaseNameList.removeIf("mysql"::equals);
        dataBaseNameList.removeIf("performance_schema"::equals);
        dataBaseNameList.removeIf("sys"::equals);
        dataBaseNameList.removeIf("world"::equals);
        dataBaseNameList.removeIf("sakila"::equals);
        return dataBaseNameList;
    }
}
