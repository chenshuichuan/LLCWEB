package llcweb.com.dao.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2018/8/24
 * Time: 14:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    public void findAll(){
        Assert.assertNotNull(peopleRepository.findAll());
    }

}
