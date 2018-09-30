package llcweb.com.domain.entity;

import java.util.Date;

public class UsefulFile extends UsefulResource{

    public UsefulFile() {
    }
    public UsefulFile(String author, String model, String introduction, Date firstDate, Date lastDate) {
        super(author, null, model, null, null, introduction, firstDate, lastDate);
    }
}
