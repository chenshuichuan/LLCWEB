package llcweb.com.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Created by tengj on 2017/3/13.
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


    /**
     * 配置静态访问资源
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //自定义项目内目录
//        //registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
//        //指向外部目录
//        registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/");
//        super.addResourceHandlers(registry);
//    }

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/admin/login");
        super.addViewControllers(registry);
    }

    /**
     * 拦截器
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin","/login");
//        super.addInterceptors(registry);
//    }


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
    
    /*
     * 时间格式转换
     */
    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();

        //日期格式转换
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH"));
        
        //Long类型转String类型
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        mapper.registerModule(simpleModule);
        
        converter.setObjectMapper(mapper);
        return converter;
    } 
    
}

