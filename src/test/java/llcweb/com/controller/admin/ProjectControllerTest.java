package llcweb.com.controller.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import llcweb.com.Application;
import llcweb.com.domain.User;

/**
 * @author tong
 * controller测试类
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "/LLCWEB/src/main/java/llcweb/com/Application")
public class ProjectControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mvc;
	private MockHttpSession session;
	
	private MockHttpServletRequest request;    
    private MockHttpServletResponse response;  
	
    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        User user =new User("admin","admin");
        session.setAttribute("user",user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
    }
    
/*        
     * 新增测试用例
     
    
    @Test
    public void saveProject() throws Exception{
    	//String json = "{\"status\":\"ing\",\"money\":\"1111\",\"end_date\":\"2018-09-10\"}";
    	String json = "{\"status\":\"ing\",\"money\":\"111\",\"end_date\":\"2018-09-10\", \"start_date\":\"2018-09-10\",\"title\":\"teacher\",\"responsible_person\":\"me\", \"require_num\":\"123456\",\"project_type\":\"纵向\",\"project_name\":\"ohuo\", \"members\":\"HAHAHAA\",\"team\":\"Spring\",\"project_des\":\"http://tengj.top/\", \"project_aim\":\"HAHAHAA\",\"host_unit\":\"Spring\",\"co_unit\":\"http://tengj.top/\", \"undertake_unit\":\"hst\"}";
    	mvc.perform(MockMvcRequestBuilders.post("/project/save")
    			 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .content(json.getBytes()) //传json参数
                 .session(session)
         )
    	.andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
    				
    }*/
    
    /*
     * 新增删除测试
     */
    @Test
    public void deleteProject() throws Exception{
    	String json = "336";
    	
        mvc.perform(MockMvcRequestBuilders.post("/project/delete")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())//传json参数
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
