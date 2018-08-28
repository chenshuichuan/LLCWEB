package llcweb.com.domain.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="file")
public class File {

    //主键
    @Id
    @GeneratedValue
    private int id;
    //文件简介
    @Column(length = 50)
    private String introduction;
    //文件位于项目中的路径
    @Column(length = 50)
    private String path;
    //文件日期
    @Column(columnDefinition="DATE")
    private Date date;
    //组别
    @Column(length = 10)
    private String model;
    //文件上传者
    @Column(length = 10)
    private String owner;

    public File() {
    }

    public File(String introduction, Date date, String model) {
        this.introduction = introduction;
        this.date = date;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
