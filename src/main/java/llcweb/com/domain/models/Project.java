package llcweb.com.domain.models; /***********************************************************************
 * Module:  Project.java
 * Author:  Ricardo
 * Purpose: Defines the Class Project
 ***********************************************************************/

import java.util.*;

/** @pdOid e7c93698-3864-4894-a748-72ddbf6d5a9c */
public class Project {
   /** @pdOid 8d204204-c591-4ce6-8e16-7fcc74c9490f */
   public int id;
   /** 项目名称
    * 
    * @pdOid a4d053ba-3ce5-49e1-a350-66092354dc4d */
   public String name;
   /** 项目级别，省重点等
    * 
    * @pdOid 92ce2b0d-258e-466a-86ec-9e48f5e7a31b */
   public String level;
   /** 是否在研究
    * 
    * @pdOid 6f902686-92bd-4e26-8242-470342ddc41b */
   public int isWork;
   /** @pdOid 092e0072-648b-4450-ab89-a684807d231f */
   public Date startYear;
   /** @pdOid 29d7fa5e-e24e-46d3-adf2-51a80b2f6d2b */
   public Date endYear;
   /** 简介id，对应article的文章
    * 
    * @pdOid b8ac9fdc-c26c-4228-ad0d-a8e2dd663de0 */
   public int introduction;
   /** 该项目的研究人员列表
    * 
    * @pdOid 0dd70942-75fd-4c58-b599-7698f89bd826 */
   public String peopleList;

}