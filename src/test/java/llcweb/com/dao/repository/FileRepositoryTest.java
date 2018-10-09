package llcweb.com.dao.repository;

import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import llcweb.com.service.FileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileService fileService;

    @Test
    public void add() throws ParseException {
        File file=new File();
        file.setAuthor("haien");
        file.setIntroduction("项目组第1次会议");
        file.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25"));
        Assert.assertThat(fileRepository.save(file).getId(),is(1));
    }

    @Test
    public void findAll() throws ParseException {
        UsefulFile file=new UsefulFile();
        file.setFirstDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-22"));
        file.setLastDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25"));
        file.setAuthor("haien");
        Page<File> files=fileService.activeSearch(file,0,3,fileRepository);
        Assert.assertThat(files.getTotalElements(),is(1L));
    }

    @Test
    public void fuzzySearch(){
        Page<File> files=fileRepository.fuzzySearch("项目",new PageRequest(0,10, Sort.Direction.DESC,"date"));
        Assert.assertThat(files.getTotalElements(),is(1L));
    }

}