package llcweb.com.dao.repository;

import llcweb.com.domain.entity.UsefulConference;
import llcweb.com.domain.models.Conference;
import llcweb.com.service.ConferenceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;

/**
 * @Author haien
 * @Description 会议的测试类
 * @Date 19:03 2018/8/22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private ConferenceService conferenceService;

    private Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-28");
    private Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25");
<<<<<<< HEAD

=======
>>>>>>> 48976cb113d6a1b6e3afccc1fa4a511297c0948e
    public ConferenceRepositoryTest() throws ParseException {
    }

    @Test
    public void add() throws ParseException {
<<<<<<< HEAD
        Conference conference = new Conference();
=======
        Conference conference=new Conference();
>>>>>>> 48976cb113d6a1b6e3afccc1fa4a511297c0948e
        conference.setAuthor("haien");
        conference.setDate(date1);
        conference.setModel("项目组");
        conference.setTitle("项目组第1次会议");
        conference.setType("小组会议");
<<<<<<< HEAD
        Assert.assertThat(conferenceRepository.save(conference).getId(), is(0));
=======
        Assert.assertThat(conferenceRepository.save(conference).getId(),is(0));
>>>>>>> 48976cb113d6a1b6e3afccc1fa4a511297c0948e
    }

    @Test
    public void findAll() throws ParseException {
<<<<<<< HEAD
        UsefulConference conference = new UsefulConference();
        conference.setFirstDate(date1);
        conference.setLastDate(date1);
        Page<Conference> conferences = conferenceService.findAll(conference, 1, 3);
        Assert.assertThat(conferences.getTotalElements(), is(12L));
    }

    @Test
    public void findByOneKey() {
        Page<Conference> conferences = conferenceRepository.findByOneKey("haien", new PageRequest(0, 10, Sort.Direction.DESC, "date"));
        Assert.assertThat(conferences.getTotalElements(), is(12L));
    }
=======
        UsefulConference conference=new UsefulConference();
        conference.setFirstDate(date1);
        conference.setLastDate(date1);
        Page<Conference> conferences=conferenceService.findAll(conference,1,3);
        Assert.assertThat(conferences.getTotalElements(),is(12L));
    }

    @Test
    public void findByOneKey(){
        Page<Conference> conferences=conferenceRepository.findByOneKey("haien",new PageRequest(0,10, Sort.Direction.DESC,"date"));
        Assert.assertThat(conferences.getTotalElements(),is(12L));
    }

>>>>>>> 48976cb113d6a1b6e3afccc1fa4a511297c0948e
}