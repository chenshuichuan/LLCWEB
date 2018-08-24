package llcweb.com.service;

import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import org.springframework.data.domain.Page;

public interface DocumentService {

    public Page<Document> findAll(UsefulDocument document, int pageNum, int pageSize);

}
