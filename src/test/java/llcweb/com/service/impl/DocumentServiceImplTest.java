package llcweb.com.service.impl;

import com.alibaba.fastjson.JSONObject;
import llcweb.com.service.DocumentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by:Haien
 * Description:
 * Date: 2018/8/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DocumentServiceImplTest {

    @Autowired
    private WebApplicationContext wac; //注入WebAppContext
    @Autowired
    private DocumentService documentService;

    private MockMvc mvc; //模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).bulid()初始化
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception{
        mvc= MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    /**
     * 模拟用户，给定用户名，通过自定义UserDetails来认证
     * userDetailsServiceBeanName值为UserDetailsService的实现类的bean名
     */
    @WithUserDetails(value="admin",userDetailsServiceBeanName = "loginServiceImpl")
    public void selectAllTest() throws Exception{

        Map<String,Object> map=new HashMap<>();
        map.put("param1","valueaa");
        mvc.perform(MockMvcRequestBuilders.post("/admin/document.html")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(map)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) //预期返回值的媒体类型
                .andDo(MockMvcResultHandlers.print()); //打印报头等信息
                //.andReturn(); //返回执行请求的结果,没什么用
    }

    /**
     * 测试登录成功
     */
    /*
    @Test
    public void testLogin() throws Exception{
        mvc.perform(formLogin("/login").user("admin").password("admin"))
                .andExpect(authenticated());
    }
*/
    /**
     * 测试登录失败
     */
    /*
    @Test
    public void testLoginFail() throws Exception{
        mvc.perform(formLogin("login").user("admin").password("invalid"))
                .andExpect(unauthenticated());
    }
    */
}