package llcweb.com.dao.repository;

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
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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
    public void fuzzySearch(){
        Page<Document> documents=documentRepository.fuzzySearch("haien",new PageRequest(0,10, Sort.Direction.DESC,"createDate"));
        Assert.assertThat(documents.getTotalElements(),is(12L));
    }

    @Test
    public void findByAuthorId(){
        Page<Document> documents=documentRepository.findByAuthorId(1,new PageRequest(0,10, Sort.Direction.DESC,"createDate"));
        Assert.assertThat(documents.getTotalElements(),is(3L));
    }

}