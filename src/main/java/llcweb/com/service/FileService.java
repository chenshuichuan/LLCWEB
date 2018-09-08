package llcweb.com.service;

import llcweb.com.domain.entity.BusinessException;
import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

    /**
     * 动态查找
     */
    public Page<File> findAll(UsefulFile file, int pageNum, int pageSize);

    public int add(File file);
    public void delete(int id);
    public void update(File file);

    public String saveFile(MultipartFile multipartFile, File file)throws BusinessException;
}
