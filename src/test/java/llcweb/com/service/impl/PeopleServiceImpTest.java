package llcweb.com.service.impl;

import llcweb.com.dao.repository.PeopleRepository;
import llcweb.com.domain.models.People;
import llcweb.com.service.PeopleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleServiceImpTest {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private PeopleRepository peopleRepository;
    @Test
    public void add() throws Exception {

        for (int i=0;i<15;i++){
            People people = new People();
            people.setName("user"+i);
            people.setPasswd("password"+i);
            people.setGrade("grade"+i);
            people.setPosition("position"+i);
            peopleRepository.save(people);
        }
        Assert.fail("存在异常");
    }
}
