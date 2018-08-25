package llcweb.com.service;

import llcweb.com.domain.entity.UsefulFile;
import llcweb.com.domain.models.File;
import org.springframework.data.domain.Page;

public interface FileService {

    public Page<File> findAll(UsefulFile file, int pageNum, int pageSize);

}
