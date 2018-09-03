package llcweb.com.service;

import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface FileService {

    /**
     * 动态查找
     */
    public Page<File> findAll(UsefulFile file, int pageNum, int pageSize);

    public Map<String,Object> add(File file);
    public Map<String,Object> delete(int id);
}
