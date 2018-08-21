package llcweb.com.domain.models; /***********************************************************************
 * Module:  Patent.java
 * Author:  Ricardo
 * Purpose: Defines the Class Patent
 ***********************************************************************/

import java.util.*;

/** @pdOid f0f37e7d-5acf-46c9-a543-c2ad281a1b19 */
public class Patent {
   /** @pdOid 83256c5d-5bfb-4e64-9e7f-304a7f815def */
   public int id;
   /** @pdOid 52e26cb6-baa2-40e8-9b25-760445997251 */
   public String title;
   /** 发表日期
    * 
    * @pdOid 22b409e8-f698-414b-9e3d-8776973960ce */
   public Date date;
   /** 文章简介
    * 
    * @pdOid f14b5ea7-1582-45c1-a652-e86e09a424c5 */
   public int introduction;
   /** 作者姓名列表，对应people表的姓名，也可以是外面合作人员
    * 
    * @pdOid 39247c5e-8b44-4240-9918-510fdc882fc0 */
   public String authorList;
   /** 原文链接
    * 
    * @pdOid 5e6cc1d4-83ff-475d-823d-1b9e31a711fa */
   public String originalLink;
   /** 所属项目id
    * 
    * @pdOid 04a486c4-4677-4b39-8f71-512294f3439d */
   public int belongProject;

}