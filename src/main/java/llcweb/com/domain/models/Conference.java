package llcweb.com.domain.models;

import javax.persistence.*;
import java.util.Date;

/**
 * 会议类
 */
@Entity
@Table(name="conference")
public class Conference {
   @Id
   @GeneratedValue
   private int id;
   //会议名称
   private String title;
   //会议简介
   private int introduction;
   //会议日期
   @Column(columnDefinition="DATE")
   private Date date;
   //参与人员列表
   private String peopleList;
   //会议类型
   private String type;
   //组别
   private String model;
   //上传者
   private String author;

   public Conference() {
   }
   public Conference(String title, int introduction, Date date,
                     String peopleList, String type) {
      this.title = title;
      this.introduction = introduction;
      this.date = date;
      this.peopleList = peopleList;
      this.type = type;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getIntroduction() {
      return introduction;
   }

   public void setIntroduction(int introduction) {
      this.introduction = introduction;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getPeopleList() {
      return peopleList;
   }

   public void setPeopleList(String peopleList) {
      this.peopleList = peopleList;
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