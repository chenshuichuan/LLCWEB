package llcweb.com.dao.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.transaction.annotation.Transactional;

import llcweb.com.domain.entity.UsefulProject;
import llcweb.com.domain.models.Conference;
import llcweb.com.domain.models.Project;
import llcweb.com.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTest1 {

	@Autowired
    private ProjectRepository projectRepository;
	@Autowired
	private ProjectService projectService;
	
	//Repository测试
	@Test
	public void add() throws ParseException{
		
		Project project = new Project();
		
		for(int i=1;i<=8;i++){
		
			project.setId(i);	
			project.setStatus("ing");
			project.setMoney("99");
			project.setEndDate(new Date());
			project.setStartDate(new Date());
			project.setTitle("title");
			project.setResponsiblePerson("re");
			project.setRequireNum("CN");
			project.setProjectType("xy");
			project.setProjectName("第二次测试图像组第" + i + "个项目");
			project.setMembers("who");
			project.setTeam("999");
			project.setProjectDes("test");
			project.setProjectAim("testPassed");
			project.setHostUnit("GDUT");
			project.setCoUnit("GDUT");
			project.setUndertakeUnit("GDUT");
			Assert.assertThat(projectRepository.save(project), notNullValue());
		
		}

	}
	
	
	   @Test
	    public void findAll() throws ParseException {
	        UsefulProject project=new UsefulProject();
	        project.setFirstDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25"));
	        project.setLastDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-02"));
	        Page<Project> projectList=projectService.findAll(project,1,3);
	        Assert.assertThat(projectList.getTotalElements(),is(91L));
	    }
	   
/*	    @Test
	    public void findByOneKey(){
	        Page<Project> projectList = projectRepository.findByOneKey("heiheihei",new PageRequest(0,10, Sort.Direction.DESC,"startDate"));
	        Assert.assertThat(projectList.getTotalElements(),is(12L));
	    }
*/
}
