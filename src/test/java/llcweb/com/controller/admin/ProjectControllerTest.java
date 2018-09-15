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
import org.springframework.web.servlet.ModelAndView;

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
     * 新增添加测试
     */
    
    @Test
    public void saveProject() throws Exception{
    	//String json = "{\"status\":\"ing\",\"money\":\"1111\",\"end_date\":\"2018-09-10\"}";
    	String json = "{\"id\":\"421\", \"status\":\"ing\",\"money\":\"111\",\"end_date\":\"2018-09-10\", \"start_date\":\"2018-09-10\",\"title\":\"teacher\",\"responsible_person\":\"me\", \"require_num\":\"123456\",\"project_type\":\"纵向\",\"project_name\":\"ohuo\", \"members\":\"HAHAHAA\",\"team\":\"Spring\",\"project_des\":\"http://tengj.top/\", \"project_aim\":\"HAHAHAA\",\"host_unit\":\"Spring\",\"co_unit\":\"http://tengj.top/\", \"undertake_unit\":\"hst\"}";
        mvc.perform(MockMvcRequestBuilders.post("/project/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
                .session(session)
        )
       .andExpect(MockMvcResultMatchers.status().isOk())
       .andDo(MockMvcResultHandlers.print());
}
    
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
    
    /*
     * 新增查询测试
     */
    @Test
    public void getProject() throws Exception{
    	mvc.perform(MockMvcRequestBuilders.get("/project/getProjectById/351")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)//代表发送端发送的数据格式是application/json;charset=UTF-8
    			.accept(MediaType.APPLICATION_JSON_UTF8)//代表客户端希望接受的数据类型为application/json;charset=UTF-8
    			.session(session)//注入一个session，这样拦截器才可以通过
    			)
		        .andExpect(MockMvcResultMatchers.status().isOk())//看请求的状态响应码是否为200如果不是则抛异常，测试不通过
		        //.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"))//jsonPath用来获取字段比对是否正确，否则测试不通过
		        .andDo(MockMvcResultHandlers.print());//输出整个响应结果信息
    	
    }

}