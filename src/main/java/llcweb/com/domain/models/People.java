package llcweb.com.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/***********************************************************************
 * Module:  People.java
 * Author:  Ricardo
 * Purpose: Defines the Class People
 ***********************************************************************/@Entity
@Table(name="people")public class People {

    @Id
    @GeneratedValue
    private int id;
/**
     * 人物姓名
     */
    private String name;

    private String phone;
    private String email;
    private String researchField;

    /**
     * 人物头像路径(关联image表)
     */
    private int portrait;

    /**
     * 职位，教授、副教授、讲师、博士后、博士、硕士，本科生
     */
    private String position;

    /**
     * 个人简介（关联document表）
     */
    private int introduction;

    /**
     * 对于本科生、硕士生、博士生是届数；讲师，教授，等是加入年份
     */
    private int grade;

    public People() {

    }

    public People(String name, String position, int grade) {
        this.name = name;
        this.position = position;
        this.grade = grade;
    }

    public People(String name, int portrait, String position, int introduction, int grade) {
        this.name = name;
        this.portrait = portrait;
        this.position = position;
        this.introduction = introduction;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public int getGrade() {
        return grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResearchField() {
        return researchField;
    }

    public void setResearchField(String researchField) {
        this.researchField = researchField;
    }
}