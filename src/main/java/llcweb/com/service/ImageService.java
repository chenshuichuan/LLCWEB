package llcweb.com.service;

import llcweb.com.domain.entity.UsefulImage;
import llcweb.com.domain.models.Image;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ImageService {

    /**
     * 动态查找
     */
    public Page<Image> findAll(UsefulImage image, int pageNum, int pageSize);

    public Map<String,Object> add(Image image);
    public Map<String,Object> update(Image image);
    public Map<String,Object> delete(Image image);
}
