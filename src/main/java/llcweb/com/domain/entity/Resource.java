package llcweb.com.domain.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 封装搜索信息的会议类
 */
@MappedSuperclass
public class Resource {

    @Id
    @GeneratedValue
    private int id;
    //创建日期
    @Column(columnDefinition = "DATE")
    private Date createDate;
    //组别
    private String model;
    //上传者
    @Column(length=20)
    private String author;
    //上传者id
    private int authorId;


    public Resource() {
    }
    public Resource(int id, Date createDate, String model, String author, int authorId) {
        this.id = id;
        this.createDate = createDate;
        this.model = model;
        this.author = author;
        this.authorId = authorId;
    }
    public Resource(Date createDate, String model, String author, int authorId) {
        this.createDate = createDate;
        this.model = model;
        this.author = author;
        this.authorId = authorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}