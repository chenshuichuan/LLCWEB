package llcweb.com.domain.models; /***********************************************************************
 * Module:  Image.java
 * Author:  Ricardo
 * Purpose: Defines the Class Image
 ***********************************************************************/

import java.util.*;

/** @pdOid 09bbc5c7-3bed-42f7-87a0-87cafad28b0f */
public class Image {
   /** @pdOid 7eda8b9d-bb39-4f55-8fdd-944f7bb7b076 */
   public int id;
   /** 照片描述
    * 
    * @pdOid 8393be4e-3238-42d5-8eec-5d65bb26e282 */
   public String description;
   /** 照片日期
    * 
    * @pdOid ccf35a27-a918-4f8c-b220-ceb345850357 */
   public Date date;
   /** 照片拥有者、或是上传者
    * 
    * @pdOid f1c4220d-41c3-4f25-a5b2-d941bd4a8b8c */
   public int owner;
   /** 片照地址
    * 
    * @pdOid e64fc9fb-4cfb-43c2-9371-52bd901dd1a9 */
   public String path;

}