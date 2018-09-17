package llcweb.com.service.impl;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import llcweb.com.dao.repository.PatentRepository;
import llcweb.com.service.PatentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatentServiceImplTest {

	@Autowired
	private PatentService patentService;
	@Autowired
	private PatentRepository patentRepository;
	
	@Test
	public void delete() {
		patentService.delete(45);
		Assert.assertThat(patentRepository.getOne(45), is(equals(null)));
	}
	
}
