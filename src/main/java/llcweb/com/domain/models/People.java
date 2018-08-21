package llcweb.com.domain.models; /***********************************************************************
 * Module:  People.java
 * Author:  Ricardo
 * Purpose: Defines the Class People
 ***********************************************************************/

import java.util.*;

/** @pdOid ee060cc6-3f48-4c4f-b05e-e17fd04a593a */
public class People {
   /** 人物id
    * 
    * @pdOid 12cf0736-2148-4fba-ae9a-563f33fc5c44 */
   public int id;
   /** 人物姓名
    * 
    * @pdOid 7490cafc-2960-4563-97f2-ce3ae3ad5d7c */
   public String name;
   /** 登录密码
    * 
    * @pdOid 82b33685-29a7-4c1f-8368-3f8517827de6 */
   public String passwd;
   /** 人物头像路径
    * 
    * @pdOid c1c3a67a-0a13-4b72-a9f1-de202f2a7905 */
   public int portrait;
   /** 职位，教授、副教授、讲师、博士后、博士、硕士，本科生
    * 
    * @pdOid 9d04303d-2463-4031-b06b-d5c99edef958 */
   public String position;
   /** @pdOid 3b33f3fa-b714-4455-88de-a7355b802b3c */
   public int introduction;
   /** 对于本科生、硕士生博士生是届数，讲师，教授，等是加入年份
    * 
    * @pdOid 60d8e701-f88e-4c53-ad5a-61ca9790caac */
   public String grade;

}