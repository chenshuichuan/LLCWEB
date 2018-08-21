package llcweb.com.domain.models; /***********************************************************************
 * Module:  Images.java
 * Author:  Ricardo
 * Purpose: Defines the Class Images
 ***********************************************************************/

import java.util.*;

/** @pdOid f90e190d-bbec-4d57-9bfc-b4454e7f2dd3 */
public class Images {
   /** @pdOid e971810a-1810-4bb6-ae7e-9ca92b512dbf */
   public int id;
   /** 影集图片列表、以英文逗号分隔
    * 
    * @pdOid b34cfefc-fa03-4622-9588-895ee27ecdcc */
   public String imageList;
   /** 影集类型，对应activity、conference、people、project表等类型
    * 
    * @pdOid 694db22a-69c8-4593-86a7-50fc5e0896a7 */
   public String type;
   /** 应activity、conference、people等表相应的id号
    * 
    * @pdOid 30fd9fb8-1bca-4806-8ed8-ad5638e6fdcf */
   public int typeId;
   /** 影集描述
    * 
    * @pdOid b1e03227-e078-4071-ac4b-2b48b61cce49 */
   public String description;

}