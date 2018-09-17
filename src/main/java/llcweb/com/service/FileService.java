package llcweb.com.service;

import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import llcweb.com.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface FileService {

    /**
     * 动态查找
     */
    public Page<File> activeSearch(UsefulFile file, int pageNum, int pageSize);

    /**
     * 上传文件
     */
    public String saveFile(MultipartFile multipartFile, File file) throws BusinessException;

    /**
     * 获取文件输出流
     **/
    public void getOutputStream(File file,HttpServletResponse response) throws IOException;

    /**
     * 删除项目文件
     **/
    public void deleteFile(String path) throws FileNotFoundException, BusinessException;
}
