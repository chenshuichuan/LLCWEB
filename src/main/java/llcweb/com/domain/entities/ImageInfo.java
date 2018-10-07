package llcweb.com.domain.entities;

import llcweb.com.domain.entity.Resource;
import llcweb.com.domain.models.Image;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
/**
 * 打算用于封装信息给前端，目前未使用*/
public class ImageInfo {

    //照片描述
    private String description;
    //照片地址 ./images/xxx.jpg
    private String path;

    /**
     * 下载地址，*/
    private String downloadPath;
    /**
     * originalName:原文件名
     * */
    private String originalName;

    private int id;
    //创建日期

    private Date createDate;
    //组别
    private String model;

    //上传者
    private String author;
    //上传者id
    private int authorId;

    public ImageInfo() {
    }
    public ImageInfo(Image image) {
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}