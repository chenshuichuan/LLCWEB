package llcweb.com.domain.entity;

public class UsefulPeople {
    public int id;
    public String name;
    public String passwd;
    public int portrait;
    public String position;
    public int introduction;
    public String grade;

    public UsefulPeople(){

    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
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

    public String getGrade() {
        return grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
