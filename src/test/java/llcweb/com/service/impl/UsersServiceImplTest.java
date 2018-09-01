package llcweb.com.service.impl;

import llcweb.com.dao.repository.ConferenceRepository;
import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.models.Users;
import llcweb.com.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2018/8/24
 * Time: 14:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    UsersService usersService;
    @Test
    public void add() throws Exception {

        for (int i=0;i<15;i++){
            Users users = new Users();
            users.setUsername("user"+i);
            users.setPassword("password"+i);
            users.setUpdateTime(new Date());
            users.setPeopleId(i);
            usersRepository.save(users);
        }
        //Assert.assertThat(conferenceRepository.save(conference).getId(),is(0));
    }
}