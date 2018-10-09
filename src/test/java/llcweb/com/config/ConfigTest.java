package llcweb.com.config;

import llcweb.com.component.ResourcesConfig;
import llcweb.com.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ConfigTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public ResourcesConfig resourcesConfig;

    @Test
    public void TestResources(){
        logger.info("url="+resourcesConfig.getUrl());
        logger.info("url="+resourcesConfig.getImage());
        logger.info("url="+resourcesConfig.getFile());
    }
}