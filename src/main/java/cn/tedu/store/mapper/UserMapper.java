package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

/**
 * 处理用户持久层的接口
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param user
	 * @return 受影响的行数
	 */
	Integer addnew(User user);
	/**
	 * 根据用户名查找用户信息
	 * @param username
	 * @return 查找匹配数据的用户，没有返回null
	 */
	User findByUsername(String username);
	/**
	 * 通过Id查找用户
	 * @param uid
	 * @return 用户信息
	 */
	User findById(Integer uid);
	/**
	 * 通过Id修改密码和修改者修改时间
	 * @param uid 用户Id
	 * @param password 用户密码
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	Integer updatePassword(@Param("uid") Integer uid,@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	/**
	 * 修改用户数据
	 * @param user
	 * @return 受影响的行数
	 */
	Integer updateInfo(User user);
	/**
	 * 执行修改头像
	 * @param uid 用户Id
	 * @param avatar 头像的路径
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	Integer updateAvatar(@Param("uid") Integer uid,@Param("avatar") String avatar,
			@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
}
