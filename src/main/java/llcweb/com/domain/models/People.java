package llcweb.com.domain.models;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/***********************************************************************
 * Module:  People.java
 * Author:  Ricardo
 * Purpose: Defines the Class People
 ***********************************************************************/

<<<<<<< HEAD
   @Entity
   @Table(name="t_people")
   public class People {
       @Id
       @GeneratedValue(strategy=GenerationType.AUTO)
    /** 人物id
    * 
    * @pdOid 12cf0736-2148-4fba-ae9a-563f33fc5c44 */
   private int id;

   /** 人物姓名
    * 
    * @pdOid 7490cafc-2960-4563-97f2-ce3ae3ad5d7c */
   private String name;

/** 登录密码
    * 
    * @pdOid 82b33685-29a7-4c1f-8368-3f8517827de6 */
   private String passwd;

/** 人物头像路径
    * 
    * @pdOid c1c3a67a-0a13-4b72-a9f1-de202f2a7905 */
   private int portrait;

/** 职位，教授、副教授、讲师、博士后、博士、硕士，本科生
    * 
    * @pdOid 9d04303d-2463-4031-b06b-d5c99edef958 */
   private String position;

/** @pdOid 3b33f3fa-b714-4455-88de-a7355b802b3c */

   private int introduction;
/** 对于本科生、硕士生博士生是届数，讲师，教授，等是加入年份
    * 
    * @pdOid 60d8e701-f88e-4c53-ad5a-61ca9790caac */

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
=======
/*
public class People {
   */
/** 人物id
    * 
    * @pdOid 12cf0736-2148-4fba-ae9a-563f33fc5c44 *//*

   public int id;
   */
/** 人物姓名
    * 
    * @pdOid 7490cafc-2960-4563-97f2-ce3ae3ad5d7c *//*

   public String name;
   */
/** 登录密码
    * 
    * @pdOid 82b33685-29a7-4c1f-8368-3f8517827de6 *//*

   public String passwd;
   */
/** 人物头像路径
    * 
    * @pdOid c1c3a67a-0a13-4b72-a9f1-de202f2a7905 *//*

   public int portrait;
   */
/** 职位，教授、副教授、讲师、博士后、博士、硕士，本科生
    * 
    * @pdOid 9d04303d-2463-4031-b06b-d5c99edef958 *//*

   public String position;
   */
/** @pdOid 3b33f3fa-b714-4455-88de-a7355b802b3c *//*

   public int introduction;
   */
/** 对于本科生、硕士生博士生是届数，讲师，教授，等是加入年份
    * 
    * @pdOid 60d8e701-f88e-4c53-ad5a-61ca9790caac *//*

   public String grade;

}*/
>>>>>>> 48976cb113d6a1b6e3afccc1fa4a511297c0948e
