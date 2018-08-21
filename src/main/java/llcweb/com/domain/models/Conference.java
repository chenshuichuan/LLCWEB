package llcweb.com.domain.models; /***********************************************************************
 * Module:  Conference.java
 * Author:  Ricardo
 * Purpose: Defines the Class Conference
 ***********************************************************************/

import java.util.*;

/** @pdOid 77be96b4-76ae-41f7-8f6a-efeb54ec99fd */
public class Conference {
   /** @pdOid c579586a-fb4e-40d6-83db-65f468cc7c76 */
   public int id;
   /** 会议名称
    * 
    * @pdOid afdec756-a27a-4372-a0c6-b06d43332dec */
   public String title;
   /** 会议简介
    * 
    * @pdOid 5deb821e-4c42-4d1a-8385-80616a6bac69 */
   public int introduction;
   /** @pdOid c1318680-5bde-47c3-a562-7f15fe3d9a1d */
   public Date date;
   /** 参与人员列表
    * 
    * @pdOid 80d06b3a-d982-40dc-ab01-2e2952709534 */
   public String peopleList;
   /** @pdOid 265387f5-32b0-44b5-bf04-e79264b6067c */
   public String type;

}