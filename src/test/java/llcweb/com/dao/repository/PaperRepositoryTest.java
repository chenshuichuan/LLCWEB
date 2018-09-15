package llcweb.com.dao.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import llcweb.com.domain.entity.UsefulPaper;
import llcweb.com.domain.models.Paper;
import llcweb.com.service.PaperService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaperRepositoryTest {
	
	@Autowired
	private PaperRepository paperRepository;
	
	@Autowired
	private PaperService paperService;
	
	@Test
	public void add() throws ParseException {
		
		Paper paper = new Paper();
		for(int i = 2; i <= 10; i++) {
			paper.setId(i);
			paper.setAuthorList("test");
			paper.setBelongProject("hahaha");
			paper.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-28"));
			paper.setIntroduction("nothing");
			paper.setOriginalLink("www.github.com");
			paper.setPeriodical("SCI第" + i + "区论文");
			paper.setSourceLink("www.google.com");
			paper.setTitle("GAN" + i + "种方法综述");
			Assert.assertThat(paperRepository.saveAndFlush(paper), notNullValue());
	}
	}

	   @Test
	    public void findAll() throws ParseException {
	        UsefulPaper paper = new UsefulPaper();
	        paper.setFirstDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25"));
	        paper.setLastDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-02"));
	        Page<Paper> paperList=paperService.findAll(paper,1,3);
	        Assert.assertThat(paperList.getTotalElements(),is(91L));
	        System.out.println(((Slice<Paper>) paper).getSort());
	    }
}
