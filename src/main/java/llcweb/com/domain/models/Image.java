package llcweb.com.domain.models;

import javax.persistence.*;
import java.util.Date;

/**
 * 图片表
 */
@Entity
@Table(name="image")
public class Image {
    //图片主键
    @Id
    @GeneratedValue
    private int id;
    //照片描述(相当于标题）
    private String description;
    //照片日期
    @Column(columnDefinition="DATE")
    private Date date;
    //照片拥有者、或是上传者id
    private int authorId;
    //照片拥有者、或是上传者
    private String author;
    //照片地址
    private String path;
    //组别
    private String model;
    //原文件名
    private String originalName;

    public Image() {
    }

    public Image(String description, Date date, String author, int authorId, String model,String originalName) {
        this.description = description;
        this.date = date;
        this.author = author;
        this.authorId = authorId;
        this.model = model;
        this.originalName = originalName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}