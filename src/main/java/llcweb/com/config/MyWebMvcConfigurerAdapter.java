package llcweb.com.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import llcweb.com.controller.admin.ImageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by tengj on 2017/3/13.
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    private final static Logger logger=LoggerFactory.getLogger(ImageController.class);

    //访问图片方法
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //linux 下
        //registry.addResourceHandler("/images/**").addResourceLocations("file:"+"/root/LLCWEB/images/");
        //registry.addResourceHandler("/files/**").addResourceLocations("file:"+"/root/LLCWEB/files/");
        //windows下 C:\Users\Ricardo\IdeaProjects\LLCWEB\files\   E:\LLCWEB\files\
        //registry.addResourceHandler("/images/**").addResourceLocations("file:"+"E:\\LLCWEB\\images\\");
        //registry.addResourceHandler("/files/**").addResourceLocations("file:"+"E:\\LLCWEB\\files\\");

        registry.addResourceHandler("/homes/images/**").addResourceLocations("file:"+"E:\\temp\\");
        registry.addResourceHandler("/homes/files/**").addResourceLocations("file:"+"E:\\temp\\");
        //registry.addResourceHandler("/images/**").addResourceLocations("file:"+"C:\\Users\\Ricardo\\IdeaProjects\\LLCWEB\\images\\");
        //registry.addResourceHandler("/files/**").addResourceLocations("file:"+"C:\\Users\\Ricardo\\IdeaProjects\\LLCWEB\\files\\");

        super.addResourceHandlers(registry);
    }
    /**
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("admin/login");
        super.addViewControllers(registry);
    }

    /**
     * 配置fastJson
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
        super.configureMessageConverters(converters);
    }
}

