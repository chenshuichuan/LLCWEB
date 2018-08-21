package llcweb.com.domain.models; /***********************************************************************
 * Module:  Users.java
 * Author:  Ricardo
 * Purpose: Defines the Class Users
 ***********************************************************************/

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/** 用户表
 * 
 * @pdOid 46004338-2d9a-4942-aa30-2ec6003aefd5 */

@Entity
@Table(name="users")
public class Users implements Serializable,UserDetails {
   /** user id
    * 
    * @pdOid d05d1325-1951-432b-b019-e7056fd21a15 */
   @Id
   @GeneratedValue
   public long id;

   /** user name
    * 
    * @pdOid 3ece049d-ae34-40ff-851e-874a89284d0a */
   private String username;

   /** @pdOid a1788584-1977-45d8-aaef-fb7c598274b2 */
   public String password;

   /** @pdOid e975446e-34c9-4731-b9aa-7e1081145dcd */
   public Date updateTime;
   /** @pdOid e4b378a6-835c-4092-b64b-e40192b32cb8 */
   public int peopleId;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
           name = "users_roles",
           joinColumns = {
                   @JoinColumn(name = "ur_user_id")
           },
           inverseJoinColumns = {
                   @JoinColumn(name = "ur_role_id")
           }
   )
   private List<Roles> roles;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> auths = new ArrayList<>();
      List<Roles> roles = getRoles();
      for(Roles role : roles)
      {
         auths.add(new SimpleGrantedAuthority(role.getrFlag()));
      }
      return auths;
   }

   @Override
   public String getPassword() {
      return this.password;
   }

   @Override
   public String getUsername() {
      return this.username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public List<Roles> getRoles() {
      return roles;
   }

   public void setRoles(List<Roles> roles) {
      this.roles = roles;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Date getUpdateTime() {
      return updateTime;
   }

   public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
   }

   public int getPeopleId() {
      return peopleId;
   }

   public void setPeopleId(int peopleId) {
      this.peopleId = peopleId;
   }
}