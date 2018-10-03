package llcweb.com.domain.models;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author haien
 * @Description 通知类（主要内容放在document表）
 * @Date 2018/10/2
 **/
@Entity
@Table(name="information")
public class Information {
    //id
    @Id
    @GeneratedValue
    private int id;

    //标题
    private String title;

    //通知内容(关联document表)
    private int content;

    //通知时间
    @Column(columnDefinition = "date")
    private Date date;

    //发布人
    private String author;

    public Information() {
    }

    public Information(String title, int content, Date date, String author) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
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

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
