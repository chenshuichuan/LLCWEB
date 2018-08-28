package llcweb.com.domain.models; /***********************************************************************
 * Module:  Document.java
 * Author:  Ricardo
 * Purpose: Defines the Class Document
 ***********************************************************************/

import javax.persistence.*;
import java.util.Date;

/**
 * 文档类
 */
@Entity
@Table(name="document")
public  class Document {

   //文章id
   @Id
   @GeneratedValue
   private long id;
   //作者id
   private int authorId = 0;
   //作者
   private String author;
   //文章标题
   private String title;
   //文章内容
   private String content;
   //创建时间
   @Column(columnDefinition="DATE") //指定字段类型和命名
   private Date createDate;
   //修改时间
   @Column(columnDefinition="DATE")
   private Date modifyDate;
   //注释
   private String infor;
   //组别
   private String model;

   public Document() {
   }

   public Document(String author, String title, String content, Date createDate, Date modifyDate, String infor, String model) {
      this.author = author;
      this.title = title;
      this.content = content;
      this.createDate = createDate;
      this.modifyDate = modifyDate;
      this.infor = infor;
      this.model = model;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
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

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getCreateDate() {
      return createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public Date getModifyDate() {
      return modifyDate;
   }

   public void setModifyDate(Date modifyDate) {
      this.modifyDate = modifyDate;
   }

   public String getInfor() {
      return infor;
   }

   public void setInfor(String infor) {
      this.infor = infor;
   }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
   public String toString() {
      return "Document{" +
              "id=" + id +
              ", authorId=" + authorId +
              ", author='" + author +
              ", title='" + title +
              ", content='" + content +
              ", createDate=" + createDate +
              ", modifyDate=" + modifyDate +
              ", infor='" + infor +
              '}';
   }
}