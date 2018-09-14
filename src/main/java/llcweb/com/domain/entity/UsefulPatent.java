package llcweb.com.domain.entity;

import java.util.Date;

import llcweb.com.domain.models.Patent;

/**
 * @Author tong
 * @Description 封装搜索信息的专利类, 主要在搜索专利时封装搜索条件
 * @Date 16:20 2018/09/02
 * @Param
 * @return
 **/
public class UsefulPatent {
	
	//专利标题
	public String title;
	//专利作者
	public String authorList;
	//专利所属项目
	public String belongProject;
	//专利公开号
	public String publicNum;
	//专利代理机构
	public String agency;
	
	//按以下时间段搜索
	public Date firstDate;
	public Date lastDate;
	
	public UsefulPatent(Patent patent) {
		super();
		this.title = title;
		this.authorList = authorList;
		this.belongProject = belongProject;
		this.publicNum = publicNum;
		this.agency = agency;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
	}
	
	public UsefulPatent(String title, String authorList, String belongProject, String publicNum, String agency,
			Date firstDate, Date lastDate) {
		super();
		this.title = title;
		this.authorList = authorList;
		this.belongProject = belongProject;
		this.publicNum = publicNum;
		this.agency = agency;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
	}

	public UsefulPatent() {
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorList() {
		return authorList;
	}
	public void setAuthorList(String authorList) {
		this.authorList = authorList;
	}
	public String getBelongProject() {
		return belongProject;
	}
	public void setBelongProject(String belongProject) {
		this.belongProject = belongProject;
	}
	public String getPublicNum() {
		return publicNum;
	}
	public void setPublicNum(String publicNum) {
		this.publicNum = publicNum;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	@Override
	public String toString() {
		return "UsefulPatent [title=" + title + ", authorList=" + authorList + ", belongProject=" + belongProject
				+ ", publicNum=" + publicNum + ", agency=" + agency + ", firstDate=" + firstDate + ", lastDate="
				+ lastDate + "]";
	}
	
	
	
}
