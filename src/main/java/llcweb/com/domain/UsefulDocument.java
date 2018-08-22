package llcweb.com.domain; /***********************************************************************
 * Module:  Document.java
 * Author:  Ricardo
 * Purpose: Defines the Class Document
 ***********************************************************************/

import java.util.Date;

/** 封装搜索信息的文档类
 * @pdOid f86d4902-a99e-4dbd-97ed-b6c5472765bc */
public class UsefulDocument {
   /** 文章id
    * 
    * @pdOid b19668b5-6362-4ec1-8d8b-e8083a74a39f */
   public long id;
   /** 作者id
    * 
    * @pdOid c9d8e504-8f4d-44b2-ab87-5b5062aa42f0 */
   public int authorId = 0;
   /** 作者
    *
    * @pdOid c9d8e504-8f4d-44b2-ab87-5b5062aa42f0 */
  public String author;
   /** 文章标题
    * 
    * @pdOid 2a5cdd5d-0200-497c-b388-fee7931829aa */
   public String title;
   /** 文章内容
    * 
    * @pdOid b6632517-862f-4d63-8faa-8623d1d90cf0 */
   public String content;
   /** 最早时间
    * 
    * @pdOid a8870d71-f2da-4d8a-bfad-efdffd3aa2d4 */
   public Date FirstDate;
   /** 最晚时间
    * 
    * @pdOid 8117e7b1-5fdd-4b3b-9a8d-2a1d58284cc6 */
   public Date LastDate;

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public int getAuthorId() {
      return authorId;
   }

   public void setAuthorId(int authorId) {
      this.authorId = authorId;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
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
        return "UsefulDocument{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", FirstDate=" + FirstDate +
                ", LastDate=" + LastDate +
                '}';
    }
}