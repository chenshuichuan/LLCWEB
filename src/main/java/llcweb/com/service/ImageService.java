package llcweb.com.service;

import llcweb.com.domain.entity.UsefulImage;
import llcweb.com.domain.models.Image;
import llcweb.com.domain.models.Users;
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
     * 保存图片到项目
     **/
    public String saveImg(MultipartFile file, Image image)throws BusinessException;

    /**
     * 根据用户权限查找
     **/
    public Page<Image> selectByRole(Users user, int pageNum, int pageSize);

    public void getOutputStream(Image image,HttpServletResponse response) throws IOException;

    /**
     * 删除项目中指定图片
     **/
    public void deleteImg(String path) throws FileNotFoundException, BusinessException;
}
