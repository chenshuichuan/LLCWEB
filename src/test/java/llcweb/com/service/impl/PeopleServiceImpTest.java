package llcweb.com.service.impl;

import llcweb.com.domain.models.People;
import llcweb.com.service.PeopleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleServiceImpTest {
    @Autowired
    private PeopleService peopleService;

    @Test
    public void ActiveSearchTest(){
        People people=new People();
        //people.setName("李四");
        //people.setPosition("position");
        Page<People> peoplePage=peopleService.activeSearch(people,0,10);
        Assert.assertThat(peoplePage.getTotalElements(),is(10L));
    }
}
