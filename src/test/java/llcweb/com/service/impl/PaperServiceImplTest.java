package llcweb.com.service.impl;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.models.Paper;
import llcweb.com.domain.models.Users;
import llcweb.com.service.PaperService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaperServiceImplTest {

	@Autowired
	private PaperService paperService;
	@Autowired
	 private UsersRepository usersRepository;
	
	@Test
	public void selectAllTest() throws Exception{
		Users user = usersRepository.findByUsername("ADMIN");
		Page<Paper> papers = paperService.selectAll(user, 1, 3);
		Assert.assertThat(papers.getTotalElements(),is(2L));
	}
}
