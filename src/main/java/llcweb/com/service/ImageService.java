package llcweb.com.service;

import llcweb.com.domain.UsefulImage;
import llcweb.com.domain.models.Image;
import org.springframework.data.domain.Page;

public interface ImageService {

    public Page<Image> findAll(UsefulImage image, int pageNum, int pageSize);

}
