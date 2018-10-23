package llcweb.com.domain.models;

import llcweb.com.domain.entity.Resource;

import javax.persistence.*;
import java.util.Date;

/**
 * 图片表
 */
@Entity
@Table(name="image")
public class Image extends Resource {

    //照片描述
    private String description;
    //照片地址
    private String path;
    //原文件名
    private String originalName;

    public Image() {
    }

    public Image(String description, Date createDate, String author, int authorId, String model,String originalName) {
        super(createDate,model,author,authorId);
        this.description = description;
        this.originalName = originalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}