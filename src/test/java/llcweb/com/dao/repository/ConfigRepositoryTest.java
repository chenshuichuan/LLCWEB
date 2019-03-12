package llcweb.com.dao.repository;

import llcweb.com.domain.models.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/3/12
 * Time: 23:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigRepositoryTest {

    @Autowired
    private ConfigRepository configRepository;
    @Test
    public void findByConfigKey() {
        Config params = configRepository.findByConfigKey("index-float-window");
        Assert.assertThat(params,notNullValue());
        System.out.println("find value = "+params.getConfigValue());
    }
}