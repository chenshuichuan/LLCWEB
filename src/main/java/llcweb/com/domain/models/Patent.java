package llcweb.com.domain.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 专利成果表 llc_patent
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Entity
@Table(name = "llc_patent" )
public class Patent  {
    private static final long serialVersionUID = 1L;

    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;
    /**
     * 专利标题
     */
    @Column(name = "title" )
    private String title;
    /**
     * 申请日
     */
    @Column(name = "appli_date" )
    private Date appliDate;
    /**
     * 专利介绍
     */
    @Column(name = "introduction" )
    private Integer introduction;
    /**
     * 作者姓名列表
     */
    @Column(name = "author_list" )
    private String authorList;
    /**
     * 原文链接
     */
    @Column(name = "original_link" )
    private String originalLink;
    /**
     * 关联的文件id
     */
    @Column(name = "source_file" )
    private Integer sourceFile;
    /**
     * 专利申请号
     */
    @Column(name = "appli_num" )
    private String appliNum;
    /**
     * 申请人（一般为广东工业大学）
     */
    @Column(name = "appli_people" )
    private String appliPeople;
    /**
     * 申请日期
     */
    @Column(name = "application_date" )
    private Date applicationDate;
    /**
     * 专利公开号
     */
    @Column(name = "public_num" )
    private String publicNum;
    /**
     * 公开日
     */
    @Column(name = "public_date" )
    private Date publicDate;
    /**
     * 授权日期
     */
    @Column(name = "authorization_date" )
    private Date authorizationDate;
    /**
     * 代理机构
     */
    @Column(name = "agency" )
    private String agency;
    /**
     * 专利状态
     */
    @Column(name = "state" )
    private String state;
    /**
     * 组别
     */
    @Column(name = "model" )
    private String model;
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

    public Patent(String title, Date appliDate, Integer introduction, String authorList, String originalLink,
                  Integer sourceFile, String appliNum, String appliPeople, Date applicationDate, String publicNum, Date publicDate,
                  Date authorizationDate, String agency, String state, String model, Integer isPublish, Date updateTime) {
        this.title = title;
        this.appliDate = appliDate;
        this.introduction = introduction;
        this.authorList = authorList;
        this.originalLink = originalLink;
        this.sourceFile = sourceFile;
        this.appliNum = appliNum;
        this.appliPeople = appliPeople;
        this.applicationDate = applicationDate;
        this.publicNum = publicNum;
        this.publicDate = publicDate;
        this.authorizationDate = authorizationDate;
        this.agency = agency;
        this.state = state;
        this.model = model;
        this.isPublish = isPublish;
        this.updateTime = updateTime;
    }

    public Patent() {
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

    public void setIntroduction(Integer introduction) {
        this.introduction = introduction;
    }

    public Integer getIntroduction() {
        return introduction;
    }

    public void setAuthorList(String authorList) {
        this.authorList = authorList;
    }

    public String getAuthorList() {
        return authorList;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setSourceFile(Integer sourceFile) {
        this.sourceFile = sourceFile;
    }

    public Integer getSourceFile() {
        return sourceFile;
    }

    public void setAppliNum(String appliNum) {
        this.appliNum = appliNum;
    }

    public String getAppliNum() {
        return appliNum;
    }

    public void setAppliPeople(String appliPeople) {
        this.appliPeople = appliPeople;
    }

    public String getAppliPeople() {
        return appliPeople;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
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

    public void setAuthorizationDate(Date authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    public Date getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
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
                .append("originalLink" , getOriginalLink())
                .append("sourceFile" , getSourceFile())
                .append("appliNum" , getAppliNum())
                .append("appliPeople" , getAppliPeople())
                .append("applicationDate" , getApplicationDate())
                .append("publicNum" , getPublicNum())
                .append("publicDate" , getPublicDate())
                .append("authorizationDate" , getAuthorizationDate())
                .append("agency" , getAgency())
                .append("state" , getState())
                .append("model" , getModel())
                .append("isPublish" , getIsPublish())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
