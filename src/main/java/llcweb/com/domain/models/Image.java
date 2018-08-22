package llcweb.com.domain.models;
/***********************************************************************
 * Module:  Image.java
 * Author:  Ricardo
 * Purpose: Defines the Class Image
 ***********************************************************************/

import javax.persistence.*;
import java.util.Date;

/** 图片表
 * @pdOid 09bbc5c7-3bed-42f7-87a0-87cafad28b0f */
@Entity
@Table(name="image")
public class Image {
   /** 图片主键
    * @pdOid 7eda8b9d-bb39-4f55-8fdd-944f7bb7b076 */
   @Id
   @GeneratedValue
   public int id;
   /** 照片描述(相当于标题）
    * 
    * @pdOid 8393be4e-3238-42d5-8eec-5d65bb26e282 */

   public String description;
   /** 照片日期
    * 
    * @pdOid ccf35a27-a918-4f8c-b220-ceb345850357 */
   @Column(columnDefinition="DATE")
   public Date date;
   /** 照片拥有者、或是上传者id
    * 
    * @pdOid f1c4220d-41c3-4f25-a5b2-d941bd4a8b8c */
   public int ownerId;
    /** 照片拥有者、或是上传者
     *
     * @pdOid f1c4220d-41c3-4f25-a5b2-d941bd4a8b8c */
    public String owner;
   /** 照片地址
    * 
    * @pdOid e64fc9fb-4cfb-43c2-9371-52bd901dd1a9 */
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