package llcweb.com.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @Author haien
 * @Description 对上传的文件做的一些限制
 * @Date 2018/9/5
 **/
@Configuration
public class ImageUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        //限制单个文件大小
        factory.setMaxFileSize("10MB");
        //限制总文件大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
}
