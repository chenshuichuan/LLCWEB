package llcweb.com.service;

import llcweb.com.domain.entity.BusinessException;
import llcweb.com.domain.entity.UsefulImage;
import llcweb.com.domain.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    /**
     * 动态查找
     */
    public Page<Image> findAll(UsefulImage image, int pageNum, int pageSize);

    public int add(Image image)throws BusinessException;
    public void update(Image image)throws BusinessException;
    public void delete(Image image)throws BusinessException;

    /**
     * @Author haien
     * @Description 保存图片到项目
     * @Date 2018/9/6
     * @Param [file]
     * @return java.lang.String
     **/
    public String saveImg(MultipartFile file,Image image)throws BusinessException;
}
