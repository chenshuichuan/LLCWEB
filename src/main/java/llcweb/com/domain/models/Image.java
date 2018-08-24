package llcweb.com.domain.models;
/***********************************************************************
 * Module:  Image.java
 * Author:  Ricardo
 * Purpose: Defines the Class Image
 ***********************************************************************/

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
    public int id;
    //照片描述(相当于标题）
    public String description;
    //照片日期
    @Column(columnDefinition="DATE")
    public Date date;
    //照片拥有者、或是上传者id
    public int ownerId;
    //照片拥有者、或是上传者
    public String owner;
    //照片地址
    public String path;

    public Image() {
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", description='" + description  +
                ", date=" + date +
                ", ownerId=" + ownerId +
                ", owner='" + owner +
                ", path='" + path +
                '}';
    }
}