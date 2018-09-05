package llcweb.com.domain.models;




import javax.persistence.*;

/***********************************************************************
 * Module:  People.java
 * Author:  Ricardo
 * Purpose: Defines the Class People
 ***********************************************************************/

@Entity
@Table(name = "people")
public class People {
    @Id
    @GeneratedValue
    /** 人物id
     **/
    private int id;

    /**
     * 人物姓名
     *
     */
    private String name;

    /**
     * 登录密码
     */
    private String passwd;

    /**
     * 人物头像路径
     */
    private int portrait;

    /**
     * 职位，教授、副教授、讲师、博士后、博士、硕士，本科生
     */
    private String position;

    /**
     */

    private int introduction;

    public People() {

    }

    public People(String name, String passwd, int portrait, String position, int introduction, String grade) {
        this.name = name;
        this.passwd = passwd;
        this.portrait = portrait;
        this.position = position;
        this.introduction = introduction;
        this.grade = grade;
    }

    /**
     * 对于本科生、硕士生博士生是届数，讲师，教授，等是加入年份
     */

    private String grade;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

    public int getPortrait() {
        return portrait;
    }

    public String getPosition() {
        return position;
    }

    public int getIntroduction() {
        return introduction;
    }

    public String getGrade() {
        return grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setIntroduction(int introduction) {
        this.introduction = introduction;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}





