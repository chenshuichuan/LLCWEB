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
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.notNullValue;

/**
 * @Author haien
 * @Description TODO
 * @Date 19:03 2018/8/22
 * @Param
 * @return
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;

    @Test
    public void add(){
        Document document=new Document();
        for(int i=1;i<=10;i++){
            document.setId(i);
            document.setAuthor("haien"+i);
            document.setContent("balabala"+i);
            document.setTitle("项目组第"+i+"次会议");
            document.setCreateDate(new Date());
            Assert.assertThat(documentRepository.save(document),notNullValue());
        }
    }

    @Test
    public void findAll() throws ParseException {
        UsefulDocument document=new UsefulDocument();
        document.setFirstDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-22"));
        document.setLastDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-25"));
        document.setAuthor("haien2");
        Page<Document> documents=documentService.findAll(document,0,3);
        for (Document document1 : documents) {
            System.out.println(document1);
        }
    }


}