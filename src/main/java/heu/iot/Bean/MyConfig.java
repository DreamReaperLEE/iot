package heu.iot.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:接受YML里的配置信息
 * @Since: 2018/1/31 14:06
 */
@Component
@ConfigurationProperties(prefix = "myconfig")
public class MyConfig {
    private String filepath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
