package cn.tedu.store.service;


import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserConflictException;
import cn.tedu.store.service.ex.UserNotFoundException;

/**
 * 用户数据的业务层接口
 * @author Administrator
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user
	 * @throws InsertException 插入异常
	 * @throws UserConflictException 用户名冲突异常
	 */
	void reg(User user) throws InsertException,UserConflictException;
	/**
	 * 用户登陆
	 * @param username
	 * @param password
	 * @return 返回用户对象
	 * @throws UserNotFoundException 找不到用户异常
	 * @throws PasswordNotMatchException 密码不匹配异常
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @throws UserNotFoundException 找不到用户异常
	 * @throws PasswordNotMatchException 密码不匹配异常
	 * @throws UpdateException 密码更新异常
	 */
	void changePassword(Integer id,String oldPassword,String newPassword) throws UserNotFoundException,
		PasswordNotMatchException,UpdateException;
	/**
	 * 修改用户信息
	 * @param user
	 * @throws UserNotFoundException 找不到用户异常
	 * @throws UpdateException 用户信息更新异常
	 */
	void changeInfo(User user) throws UserNotFoundException,UpdateException;
	/**
	 * 用过id获取用户信息
	 * @param id
	 * @return 用户信息
	 */
	User getByUid(Integer id);
	/**
	 * 修改头像
	 * @param id 用户id
	 * @param avatar 用户头像
	 * @throws UserNotFoundException  找不到用户异常
	 * @throws UpdateException 用户信息更新异常
	 */
	void changeAvatar(Integer id,String avatar) throws UserNotFoundException,UpdateException;
}
