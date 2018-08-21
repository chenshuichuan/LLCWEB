package llcweb.com.domain.models; /***********************************************************************
 * Module:  Roles.java
 * Author:  Ricardo
 * Purpose: Defines the Class Roles
 ***********************************************************************/

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

/** 角色表
 * 
 * @pdOid 0961b3e8-b62c-4292-89c1-68778401a430 */

@Entity
@Table(name = "roles")
public class Roles implements Serializable {
   /** @pdOid f16a930e-44f7-408b-a69e-7ff0a4f31fe4 */
   @Id
   public int rId;
   /** @pdOid bcf075ff-93be-46d0-8e20-6989505c871c */
   public String rName;
   /** @pdOid 76dc0984-1bdf-4f52-a1ba-3dd0803d3c86 */
   public String rFlag;

   public int getrId() {
      return rId;
   }

   public void setrId(int rId) {
      this.rId = rId;
   }

   public String getrName() {
      return rName;
   }

   public void setrName(String rName) {
      this.rName = rName;
   }

   public String getrFlag() {
      return rFlag;
   }

   public void setrFlag(String rFlag) {
      this.rFlag = rFlag;
   }
}