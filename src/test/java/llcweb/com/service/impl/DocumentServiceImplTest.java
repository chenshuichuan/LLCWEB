package llcweb.com.service.impl;

import llcweb.com.dao.repository.UsersRepository;
import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import llcweb.com.domain.models.Users;
import llcweb.com.service.DocumentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;

/**
 * Created by:Haien
 * Description:
 * Date: 2018/8/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DocumentServiceImplTest {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private UsersRepository usersRepository;

    private Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-21");
    private Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-23");

    public DocumentServiceImplTest() throws ParseException { //在默认构造器抛出异常
    }

    @Test
    public void selectAllTest() throws Exception{
        Users user=usersRepository.findByUsername("user1");
        Page<Document> documents=documentService.selectByRole(user,0,10);
        Assert.assertThat(documents.getTotalElements(),is(2L));
    }

    @Test
    public void activeSearch() throws Exception{
        //UsefulDocument document=new UsefulDocument("haien",null,null,null,date1,date2);
        UsefulDocument document=new UsefulDocument("haien", null, null, null, null, null, date1, date1);
        Page<Document> documents=documentService.activeSearch(document,0,10);
        Assert.assertThat(documents.getTotalElements(),is(3L));
    }
}