package llcweb.com.service.impl;

import llcweb.com.dao.repository.UsersRepository;
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

    @Test
    public void selectAllTest() throws Exception{
        Users user=usersRepository.findByUsername("user1");
        Page<Document> documents=documentService.selectByRole(user,0,10);
        Assert.assertThat(documents.getTotalElements(),is(2L));
    }

}