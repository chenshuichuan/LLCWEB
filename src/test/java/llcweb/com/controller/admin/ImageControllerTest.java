package llcweb.com.controller.admin;

import llcweb.com.exception.BusinessException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ImageControllerTest {

    @Autowired
    private WebApplicationContext wac; //注入WebAppContext

    private MockMvc mvc; //模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac)build()初始化

    @Before
    public void setUp(){
        mvc=MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    /**
     * 模拟用户，给定用户名，通过自定义UserDetails来认证
     **/
    @WithUserDetails(value="admin",userDetailsServiceBeanName = "loginServiceImpl")
    public void uploadImageTest() throws BusinessException{
        File file=new File("./data/puchijun.jpg");
        try {
            mvc.perform(
                    MockMvcRequestBuilders
                        .fileUpload("/image/upload?group=数据组&description=hello")
                        .file(
                                new MockMultipartFile("file","puchijun.jpg",
                                            "multipart/form-data",new FileInputStream(file))
                        )       //name:转换后的文件名（不知何故只能写file）originalFilename：原文件名称
                                //contentType：转换后的文件类型 FileInputStream：文件输入流
            ).andExpect(MockMvcResultMatchers.status().isOk())
             .andDo(MockMvcResultHandlers.print()) //打印报头等信息
             .andReturn().getResponse().getContentAsString();
        } catch (IOException e) {
            System.out.println("文件格式错误！");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}