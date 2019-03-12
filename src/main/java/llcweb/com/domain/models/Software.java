package llcweb.com.domain.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 软著成果表 llc_software
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Entity
@Table(name = "llc_software" )
public class Software  {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "title" )
    private String title;
    /**
     * 申请日
     */
    @Column(name = "appli_date" )
    private Date appliDate;
    /**
     * 摘要
     */
    @Column(name = "introduction" )
    private String introduction;
    /**
     * 发明人列表
     */
    @Column(name = "author_list" )
    private String authorList;
    /**
     * 关联的文件id
     */
    @Column(name = "source_file" )
    private Integer sourceFile;
    /**
     * 所属项目
     */
    @Column(name = "belong_project" )
    private String belongProject;
    /**
     * 公开号
     */
    @Column(name = "public_num" )
    private String publicNum;
    /**
     * 公开日
     */
    @Column(name = "public_date" )
    private Date publicDate;
    /**
     * 状态
     */
    @Column(name = "status" )
    private String status;
    /**
     * 权利取得方式
     */
    @Column(name = "public_method" )
    private String publicMethod;
    /**
     * 权利范围
     */
    @Column(name = "right_range" )
    private String rightRange;
    /**
     * 是否发布
     */
    @Column(name = "is_publish" )
    private Integer isPublish;
    /**
     * 更新时间
     */
    @Column(name = "update_time" )
    private Date updateTime;

    public Software() {
        this.updateTime = new Date();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAppliDate(Date appliDate) {
        this.appliDate = appliDate;
    }

    public Date getAppliDate() {
        return appliDate;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setAuthorList(String authorList) {
        this.authorList = authorList;
    }

    public String getAuthorList() {
        return authorList;
    }

    public void setSourceFile(Integer sourceFile) {
        this.sourceFile = sourceFile;
    }

    public Integer getSourceFile() {
        return sourceFile;
    }

    public void setBelongProject(String belongProject) {
        this.belongProject = belongProject;
    }

    public String getBelongProject() {
        return belongProject;
    }

    public void setPublicNum(String publicNum) {
        this.publicNum = publicNum;
    }

    public String getPublicNum() {
        return publicNum;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPublicMethod(String publicMethod) {
        this.publicMethod = publicMethod;
    }

    public String getPublicMethod() {
        return publicMethod;
    }

    public void setRightRange(String rightRange) {
        this.rightRange = rightRange;
    }

    public String getRightRange() {
        return rightRange;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("title" , getTitle())
                .append("appliDate" , getAppliDate())
                .append("introduction" , getIntroduction())
                .append("authorList" , getAuthorList())
                .append("sourceFile" , getSourceFile())
                .append("belongProject" , getBelongProject())
                .append("publicNum" , getPublicNum())
                .append("publicDate" , getPublicDate())
                .append("status" , getStatus())
                .append("publicMethod" , getPublicMethod())
                .append("rightRange" , getRightRange())
                .append("isPublish" , getIsPublish())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
