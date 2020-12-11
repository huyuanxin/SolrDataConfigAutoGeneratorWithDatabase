package yuanxin.solr.generator.entity;

/**
 * @author huyuanxin
 * @create 2020/12/10 14:05
 */

public class DataSource {
    public String dataConfigName;
    public String dataConfigDriver;
    public String dataConfigUrl;
    public String dataConfigUser;
    public String dataConfigPassword;

    public String getDataConfigName() {
        return dataConfigName;
    }

    public void setDataConfigName(String dataConfigName) {
        this.dataConfigName = dataConfigName;
    }

    public String getDataConfigDriver() {
        return dataConfigDriver;
    }

    public void setDataConfigDriver(String dataConfigDriver) {
        this.dataConfigDriver = dataConfigDriver;
    }

    public String getDataConfigUrl() {
        return dataConfigUrl;
    }

    public void setDataConfigUrl(String dataConfigUrl) {
        this.dataConfigUrl = dataConfigUrl;
    }

    public String getDataConfigUser() {
        return dataConfigUser;
    }

    public void setDataConfigUser(String dataConfigUser) {
        this.dataConfigUser = dataConfigUser;
    }

    public String getDataConfigPassword() {
        return dataConfigPassword;
    }

    public void setDataConfigPassword(String dataConfigPassword) {
        this.dataConfigPassword = dataConfigPassword;
    }
}
