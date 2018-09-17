package llcweb.com.service.impl;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import llcweb.com.dao.repository.PaperRepository;
import llcweb.com.service.PaperService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaperServiceImplTest {

	@Autowired
	private PaperService paperService;
	@Autowired
	private PaperRepository paperRepository;
	
	@Test
	public void delete() {
		paperService.delete(45);
		Assert.assertThat(paperRepository.getOne(45), is(equals(null)));
	}
	
}
