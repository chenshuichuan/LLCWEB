package llcweb.com.domain.models; /***********************************************************************
 * Module:  Document.java
 * Author:  Ricardo
 * Purpose: Defines the Class Document
 ***********************************************************************/

import java.util.*;

/** @pdOid f86d4902-a99e-4dbd-97ed-b6c5472765bc */
public class Document {
   /** 文章id
    * 
    * @pdOid b19668b5-6362-4ec1-8d8b-e8083a74a39f */
   public long id;
   /** 作者id
    * 
    * @pdOid c9d8e504-8f4d-44b2-ab87-5b5062aa42f0 */
   public int authorId = 0;
   /** 文章标题
    * 
    * @pdOid 2a5cdd5d-0200-497c-b388-fee7931829aa */
   public String title;
   /** 文章内容
    * 
    * @pdOid b6632517-862f-4d63-8faa-8623d1d90cf0 */
   public String content;
   /** 创建时间
    * 
    * @pdOid a8870d71-f2da-4d8a-bfad-efdffd3aa2d4 */
   public Date createDate;
   /** 修改时间
    * 
    * @pdOid 8117e7b1-5fdd-4b3b-9a8d-2a1d58284cc6 */
   public Date modifyDate;
   /** 注释
    * 
    * @pdOid 8639391d-4744-442b-9b12-febf59fdbf3a */
   public String infor;

}