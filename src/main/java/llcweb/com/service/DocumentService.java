package llcweb.com.service;

import llcweb.com.domain.entities.DocumentInfo;
import llcweb.com.domain.entity.UsefulDocument;
import llcweb.com.domain.models.Document;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DocumentService {

    public Page<Document> findAll(UsefulDocument document, int pageNum, int pageSize);
    List<DocumentInfo> documentsToDocumentInfos(List<Document> documentList);
    Page<Document> getPage(int pageNum, int pageSize,Document document);
}
