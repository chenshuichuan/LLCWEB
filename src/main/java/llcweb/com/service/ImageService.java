package llcweb.com.service;

import llcweb.com.domain.entity.UsefulImage;
import llcweb.com.domain.models.Image;
import llcweb.com.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageService {

    /**
     * 动态查找
     */
    public Page<Image> activeSearch(UsefulImage image, int pageNum, int pageSize);

    /**
     * @Author haien
     * @Description 保存图片到项目
     * @Date 2018/9/6
     * @Param [file]
     * @return java.lang.String
     **/
    public String saveImg(MultipartFile file, Image image)throws BusinessException;

    public void getOutputStream(Image image,HttpServletResponse response) throws IOException;

    public void deleteImg(String path) throws FileNotFoundException, BusinessException;
}
