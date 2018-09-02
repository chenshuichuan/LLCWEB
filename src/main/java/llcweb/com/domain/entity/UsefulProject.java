package llcweb.com.domain.entity;

import java.util.Date;
/**
 * @Author tong
 * @Description 封装搜索信息的项目类, 主要在搜索项目时封装搜索条件
 * @Date 16:20 2018/09/02
 * @Param
 * @return
 **/
public class UsefulProject {
	
	//项目负责人
	private String responsiblePerson;
	//项目编号
	private String requireNum;
	//项目类型
	private String projectType;
	//项目名称
	private String projectName;
	//项目所属组别
	private String team;
	//项目日期
    public Date FirstDate;
    public Date LastDate;
	
	public UsefulProject() {
		
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getRequireNum() {
		return requireNum;
	}

	public void setRequireNum(String requireNum) {
		this.requireNum = requireNum;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
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
	@Override
	public String toString() {
		return "UsefulProject [responsiblePerson=" + responsiblePerson + ", requireNum=" + requireNum + ", projectType="
				+ projectType + ", projectName=" + projectName + ", team=" + team + ", FirstDate=" + FirstDate
				+ ", lastDate=" + LastDate + "]";
	}

	
}
