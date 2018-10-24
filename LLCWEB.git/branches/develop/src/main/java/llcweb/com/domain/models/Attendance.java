package llcweb.com.domain.models;
/***********************************************************************
 * Module:  Images.java
 * Author:  Ricardo
 * Purpose: 签到打卡类，定时任务每天凌晨1点生成当天的打卡签到记录，6点再检查生成一次
 * 早上打卡时间段在6-12点之间
 * 下午打卡时间段在12-18点之间
 * 晚上打卡时间段在18-24点之间
 *********************************************************************
 * **/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name="attendance")
public class Attendance {

   @Id
   @GeneratedValue
   private int id;
   private String peopleId; //对应people表的id
   private String peopleName;//对应people表的名称
   private Date attendanceDate;   //标记当天
   private Date morning;   //早上签到时间
   private Date afternoon;//下午签到时间
   private Date evening;  //晚上签到时间

   public Attendance() {
   }

   public Attendance(String peopleId, String peopleName,Date attendanceDate) {
      this.peopleId = peopleId;
      this.peopleName = peopleName;
      this.attendanceDate = attendanceDate;
   }

   public Date getAttendanceDate() {
      return attendanceDate;
   }

   public void setAttendanceDate(Date attendanceDate) {
      this.attendanceDate = attendanceDate;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getPeopleId() {
      return peopleId;
   }

   public void setPeopleId(String peopleId) {
      this.peopleId = peopleId;
   }

   public String getPeopleName() {
      return peopleName;
   }

   public void setPeopleName(String peopleName) {
      this.peopleName = peopleName;
   }

   public Date getMorning() {
      return morning;
   }

   public void setMorning(Date morning) {
      this.morning = morning;
   }

   public Date getAfternoon() {
      return afternoon;
   }

   public void setAfternoon(Date afternoon) {
      this.afternoon = afternoon;
   }

   public Date getEvening() {
      return evening;
   }

   public void setEvening(Date evening) {
      this.evening = evening;
   }
}