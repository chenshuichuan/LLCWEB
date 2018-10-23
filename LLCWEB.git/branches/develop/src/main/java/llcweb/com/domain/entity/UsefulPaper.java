package llcweb.com.domain.entity;

import llcweb.com.domain.models.Paper;

import java.util.Date;
/**
 * @Author tong
 * @Description 封装搜索信息的论文类, 主要在搜索论文时封装搜索条件
 * @Date 16:20 2018/09/02
 * @Param
 * @return
 **/
public class UsefulPaper {
	
	//论文标题
	public String title;
	//论文作者
	public String authorList;
	//所属项目
	public String belongProject;
	//发表期刊
	public String periodical;
	
	
	//按以下时间段搜索
	public Date firstDate;
	public Date lastDate;
	
	public UsefulPaper() {
		
	}
	
	public UsefulPaper(String title, String authorList, String belongProject, String periodical, Date firstDate,
			Date lastDate) {
		super();
		this.title = title;
		this.authorList = authorList;
		this.belongProject = belongProject;
		this.periodical = periodical;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
	}

	public UsefulPaper(Paper paper) {
		super();
		this.title = title;
		this.authorList = authorList;
		this.belongProject = belongProject;
		this.periodical = periodical;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
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

	public String getPeriodical() {
		return periodical;
	}

	public void setPeriodical(String periodical) {
		this.periodical = periodical;
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
		return "UsefulPaper [title=" + title + ", authorList=" + authorList + ", belongProject=" + belongProject
				+ ", periodical=" + periodical + ", firstDate=" + firstDate + ", lastDate=" + lastDate + "]";
	}
	
	
}
