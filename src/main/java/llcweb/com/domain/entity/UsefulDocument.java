package llcweb.com.domain.entity; /***********************************************************************
 * Module:  Document.java
 * Author:  Ricardo
 * Purpose: Defines the Class Document
 ***********************************************************************/

import java.util.Date;

/**
 * @Author haien
 * @Description 封装搜索信息的文档类, 主要在搜索文档时封装搜索条件，考虑到搜索信息可能与文档类属性有出入，
 *              比如按照时间段搜索，那么应该增加一个时间段的属性
 * @Date 9:55 2018/8/24
 * @Param
 * @return
 **/
public class UsefulDocument {

    public String author;
    public String title;
    public String model;
    //或者content，反正哪个是简介用哪个
    public String info;

    /**
     * 按以下时间段搜索
     **/
    public Date FirstDate;
    public Date LastDate;

    public UsefulDocument() {

    }

    public UsefulDocument(String author, String title, String model, String info) {
        this.author = author;
        this.title = title;
        this.model = model;
        this.info = info;
    }

    public UsefulDocument(String author, String title, String model, String info, Date firstDate, Date lastDate) {
        this.author = author;
        this.title = title;
        this.model = model;
        this.info = info;
        FirstDate = firstDate;
        LastDate = lastDate;
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

    public Date getFirstDate() {
        return FirstDate;
    }

    public void setFirstDate(Date firstDate) {
        FirstDate = firstDate;
    }

    public Date getLastDate() {
        return LastDate;
    }

    public void setLastDate(Date lastDate) {
        LastDate = lastDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UsefulDocument{" +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", FirstDate=" + FirstDate +
                ", LastDate=" + LastDate +
                '}';
    }
}