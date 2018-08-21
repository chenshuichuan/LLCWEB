package llcweb.com.domain.models; /***********************************************************************
 * Module:  Paper.java
 * Author:  Ricardo
 * Purpose: Defines the Class Paper
 ***********************************************************************/

import java.util.*;

/** @pdOid 171b67a3-b83d-45ee-92ec-603427d254eb */
public class Paper {
   /** @pdOid d4e7b0f6-a337-42cc-b087-ff9b4421e600 */
   public int id;
   /** @pdOid 0e831435-02e8-46a2-9487-5ca74b3914fa */
   public String title;
   /** 发表日期
    * 
    * @pdOid ad91da81-2165-41c0-82c8-e3e37179a3c2 */
   public Date date;
   /** 文章简介
    * 
    * @pdOid 35d1400e-4bed-4d73-92e7-2eb480b94756 */
   public int introduction;
   /** 作者姓名列表，对应people表的姓名，也可以是外面合作人员
    * 
    * @pdOid 59f32dfa-926c-4167-a717-ec189c41fcf2 */
   public String authorList;
   /** 原文链接
    * 
    * @pdOid ace58e53-123a-41e4-9660-e500c0b5665c */
   public String originalLink;
   /** 源码链接
    * 
    * @pdOid ebc8919c-bffe-425b-9677-31473dfa0e42 */
   public String sourceLink;
   /** 所属项目id
    * 
    * @pdOid 4b6c35d1-ef0d-4eb5-991d-ebb5af060d5b */
   public int belongProject;

}