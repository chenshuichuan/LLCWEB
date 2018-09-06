package llcweb.com.domain.models;
/***********************************************************************
 * Module:  Task.java
 * Author:  Ricardo
 * Purpose: 任务类，用于首页展示任务
 *********************************************************************
 * **/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name="task")
public class Task {

   @Id
   @GeneratedValue
   private int id;
   private String author;//对应people表的名称
   private String title; //任务标题

   private String detail;
   private String type; //任务类型，个人，项目组，实验室
   private String status;  //任务状态，任务状态，未开始、进行中、已完成
   private Date endDate;  //任务截止时间

   public Task() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
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

   public String getDetail() {
      return detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Task(String author, String title, String detail, String type, String status) {
      this.author = author;
      this.title = title;
      this.detail = detail;
      this.type = type;
      this.status = status;
   }
}