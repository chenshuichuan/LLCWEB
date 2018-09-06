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
import llcweb.com.domain.models.Project;
import llcweb.com.domain.models.Users;
import llcweb.com.service.ProjectService;

/**
 * @author tong
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {

	@Autowired
	private ProjectService projectService;
	@Autowired
	 private UsersRepository usersRepository;
	
	@Test
	public void selectAllTest() throws Exception{
		Users user = usersRepository.findByUsername("admin");
		Page<Project> projects = projectService.selectAll(user, 1, 3);
		Assert.assertThat(projects.getTotalElements(),is(2L));
	}
}
