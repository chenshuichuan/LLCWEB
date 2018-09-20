package llcweb.com.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/***********************************************************************
 * Module:  People.java
 * Author:  Ricardo
 * Purpose: Defines the Class People
 ***********************************************************************/
@Entity
@Table(name="people")
public class People {
	@Id
	@GeneratedValue	
    private int id;
    private String name;
    private String passwd;
    private int portrait;				// 人物头像图片
    private String position;			// 职位，教授、副教授、讲师、博士后、博士、硕士，本科生,若是硕士则需要打卡
    private int introduction;
    private String grade;				// 对于本科生、硕士生博士生是届数，讲师，教授，等是加入年份
   

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getPortrait() {
		return portrait;
	}
	public void setPortrait(int portrait) {
		this.portrait = portrait;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getIntroduction() {
		return introduction;
	}
	public void setIntroduction(int introduction) {
		this.introduction = introduction;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
