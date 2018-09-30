package llcweb.com.domain.entity;

import java.util.Date;

/**
 * 封装搜索信息的会议类
 */
public class UsefulConference {

    //会议名称
   private String title;
   //搜索日期
   private Date firstDate;
   private Date lastDate;
   //会议类型
   private String type;
   //组别
   private String model;
   //上传者
   private String author;

   public UsefulConference() {
   }
   public UsefulConference(String title, Date firstDate, Date lastDate, String type, String model, String author) {
        this.title = title;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.type = type;
        this.model = model;
        this.author = author;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Date getFirstDate() {
        return firstDate;
    }

   public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

   public Date getLastDate() {
        return lastDate;
   }

   public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
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
}