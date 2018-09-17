package llcweb.com.service.impl;

import llcweb.com.dao.repository.ImageRepository;
import llcweb.com.domain.models.Image;
import llcweb.com.exception.BusinessException;
import llcweb.com.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ImageServiceImplTest {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void deleteImg() throws FileNotFoundException, BusinessException {
        Image image=imageRepository.findOne(10);
        imageService.deleteImg(image.getPath());
    }

}