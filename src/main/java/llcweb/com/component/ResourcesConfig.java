package llcweb.com.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 *@Author: Ricardo
 *@Description: 读取配置文件的配置信息
 *@Date: 10:21 2018/10/3
 *@param:
 **/
@Component
public class ResourcesConfig {

    @Value("${llcweb.com.resources.url}")
    private  String url;
    @Value("${llcweb.com.resources.image}")
    private  String image;
    @Value("${llcweb.com.resources.file}")
    private  String file;

    public String getUrl() {
        return url;
    }
    public String getImage() {
        return image;
    }
    public String getFile() {
        return file;
    }

}
