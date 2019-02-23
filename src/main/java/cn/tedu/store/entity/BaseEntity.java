package cn.tedu.store.entity;

import java.util.Date;

/**
 * 实体类的基类
 * @author Administrator
 *
 */
public class BaseEntity {
	private String CreatedUser;
	private Date CreatedTime;
	private String ModifiedUser;
	private Date ModifiedTime;
	public String getCreatedUser() {
		return CreatedUser;
	}
	public void setCreatedUser(String createdUser) {
		CreatedUser = createdUser;
	}
	public Date getCreatedTime() {
		return CreatedTime;
	}
	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}
	public String getModifiedUser() {
		return ModifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		ModifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return ModifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		ModifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "BaseEntity [CreatedUser=" + CreatedUser + ", CreatedTime=" + CreatedTime + ", ModifiedUser="
				+ ModifiedUser + ", ModifiedTime=" + ModifiedTime + "]";
	}
	
}
