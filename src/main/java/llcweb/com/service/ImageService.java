package llcweb.com.service;

import llcweb.com.domain.entity.UsefulImage;
import llcweb.com.domain.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {

    /**
     * 动态查找
     */
    public Page<Image> findAll(UsefulImage image, int pageNum, int pageSize);

    public Map<String,Object> add(Image image);
    public Map<String,Object> update(Image image);
    public Map<String,Object> delete(Image image);

    /**
     * @Author haien
     * @Description 保存图片到项目
     * @Date 2018/9/6
     * @Param [file]
     * @return java.lang.String
     **/
    public String saveImg(MultipartFile file);
}
