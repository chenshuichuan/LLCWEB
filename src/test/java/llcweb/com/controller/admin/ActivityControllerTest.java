package llcweb.com.controller.admin;

import llcweb.com.dao.repository.ActivityRepository;
import llcweb.com.domain.models.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class ActivityControllerTest {
    @Resource
    private ActivityRepository activityRepository;

    @Test
    public void saveTest() throws ParseException {
        String date="2018-10-1";
        //Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        //Date startDate= Date.valueOf(date);
        String[] dates=date.split("-");
        int year=Integer.parseInt(dates[0]);
        int month=Integer.parseInt(dates[1]);
        int day=Integer.parseInt(dates[2]);
        GregorianCalendar calendar=new GregorianCalendar(year,month,day);
        java.util.Date startDate=calendar.getTime();
        Activity activity=new Activity();
        activity.setStartDate(startDate);
        activity=activityRepository.save(activity);
        Assert.assertThat(activity.getId(),is(2));
    }
}