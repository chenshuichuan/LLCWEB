package llcweb.com.service;

import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DocumentService {

    /**
     * 动态查找
     */
    public Page<Document> findAll(UsefulDocument document, int pageNum, int pageSize);

    public List<Document> selectAll(int userId);
    public Map<String,Object> add(Document document);
    public Map<String,Object> update(Document document);
    public Map<String,Object> delete(Document document);
}
