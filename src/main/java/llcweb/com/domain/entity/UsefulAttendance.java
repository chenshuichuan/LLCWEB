package llcweb.com.domain.entity;

import java.util.Date;

/**
 * @Author zeqin
 * @Description 封装搜索信息的打卡类, 主要在搜索打卡记录时封装搜索条件，考虑到搜索信息可能与打卡类属性有出入，
 *              比如按照时间段搜索，那么应该增加一个时间段的属性
 * @Date 9:55 2018/8/24
 * @Param
 * @return
 **/
public class UsefulAttendance {
	   public String peopleId; //对应people表的id
	   public String peopleName;//对应people表的名称
	   
	   /* 按以下时间段搜索
	     */
	   public Date FirstDate;
	   public Date LastDate;
	public UsefulAttendance() {
	}
	   
	public UsefulAttendance(String peopleId ,String peopleName ,Date firstDate, Date lastDate) {
		// TODO Auto-generated constructor stub
		peopleId = this.peopleId;
		peopleName = this.peopleName;
		FirstDate = firstDate;
		LastDate = lastDate;
	}
	
	public String getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	public String getPeopleName() {
		return peopleName;
	}
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
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
        return "UsefulAttendance{" +
                ", author='" + peopleId + '\'' +
                ", title='" + peopleName + '\'' +
                ", FirstDate=" + FirstDate +
                ", LastDate=" + LastDate +
                '}';
    }
}
