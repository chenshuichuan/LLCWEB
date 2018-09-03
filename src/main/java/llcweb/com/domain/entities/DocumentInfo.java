package llcweb.com.domain.entities;

import llcweb.com.domain.models.Document;

import java.util.Date;

/**
 * 文档简单类，用于分页，不包含content字段，考虑content字段可能大，传输耗时
 */

public  class DocumentInfo {

   private Integer id;
   //作者id
   private Integer authorId = 0;
   //作者
   private String author;
   //文章标题
   private String title;
   //文章内容
   //private String content;
   //创建时间
   private Date createDate;
   //修改时间
   private Date modifyDate;
   //注释
   private String infor;
   //组别
   private String model;

   public DocumentInfo() {
   }

   public DocumentInfo(Document document) {
      this.id = document.getId();
      this.authorId = document.getAuthorId();
      this.author = document.getAuthor();
      this.title = document.getTitle();
      //this.content = content;
      this.createDate = document.getCreateDate();
      this.modifyDate = document.getModifyDate();
      this.infor = document.getInfor();
      this.model = document.getModel();
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getAuthorId() {
      return authorId;
   }

   public void setAuthorId(Integer authorId) {
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

}