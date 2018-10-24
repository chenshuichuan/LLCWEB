package llcweb.com.service;

import llcweb.com.dao.repository.myInterface.ResourceRepository;
import llcweb.com.domain.entity.Resource;
import llcweb.com.domain.entity.UsefulResource;
import llcweb.com.domain.models.Users;
import llcweb.com.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ResourceService<T> {
    /**
     * 动态查找（repository不能在类或方法中动态指定，只能传参）
     */
    public Page<T> activeSearch(UsefulResource resource, int pageNum, int pageSize,
                                ResourceRepository resourceRepository);

    /**
     * 根据用户权限查找
     **/
    public Page<T> selectByRole(Users user, int pageNum, int pageSize,
                                ResourceRepository resourceRepository);

    /**
     * 上传图片|文件
     */
    public String saveResource(MultipartFile multipartFile, Resource resource) throws BusinessException;

    /**
     * 获取文件输出流
     **/
    public void getOutputStream(String path,HttpServletResponse response) throws IOException;

    /**
     * 删除项目文件
     **/
    public void deleteResource(String path) throws FileNotFoundException, BusinessException;

}
