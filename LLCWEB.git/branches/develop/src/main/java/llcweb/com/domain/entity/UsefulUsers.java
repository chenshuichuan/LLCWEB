package llcweb.com.domain.entity;

/**
 * Create by: Huang
 * Despcription:
 * Date: 2018/8/24/024
 * Time: 19:08
 */

public class UsefulUsers {

    public long id;
    public String username;
    public String password;
    public int peopleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }
}

