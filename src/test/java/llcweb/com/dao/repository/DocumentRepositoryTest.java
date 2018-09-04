package llcweb.com.dao.repository;

import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import llcweb.com.service.DocumentService;
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
import java.util.Date;

import static org.hamcrest.Matchers.is;

/**
 * @Author haien
 * @Description TODO
 * @Date 19:03 2018/8/22
 * @Param
 * @return
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;

    @Test
    public void add(){
        Document document=new Document();
        document.setId(1);
        document.setAuthor("haien");
        document.setContent("balabala");
        document.setTitle("项目组第13次会议");
        document.setCreateDate(new Date());
        Assert.assertThat(documentRepository.save(document).getAuthor(),is("haien"));
    }

    @Test
    public void findAll() throws ParseException {
        UsefulDocument document=new UsefulDocument();
        document.setFirstDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-22"));
        document.setLastDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25"));
        //document.setAuthor("haien2");
        Page<Document> documents=documentService.findAll(document,1,3);
        Assert.assertThat(documents.getTotalElements(),is(4L));
    }

    @Test
    public void findByOneKey(){
        Page<Document> documents=documentRepository.findByOneKey("haien",new PageRequest(0,10, Sort.Direction.DESC,"createDate"));
        Assert.assertThat(documents.getTotalElements(),is(12L));
    }

    @Test
    public void findByAuthorId(){
        Page<Document> documents=documentRepository.findByAuthorId(1,new PageRequest(0,10, Sort.Direction.DESC,"createDate"));
        Assert.assertThat(documents.getTotalElements(),is(3L));
    }

}