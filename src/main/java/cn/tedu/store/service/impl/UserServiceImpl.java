package cn.tedu.store.service.impl;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserConflictException;
import cn.tedu.store.service.ex.UserNotFoundException;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserMapper userMapper;
	@Override
	public void reg(User user) throws InsertException, UserConflictException {
			String username=user.getUsername();
			User data=findByUsername(username);
			if(data==null) {
				String salt=UUID.randomUUID().toString();
				user.setSalt(salt);
				String md5Password=getMdPassword(user.getPassword(),salt);
				user.setPassword(md5Password);
				user.setIsDelete(0);
				Date now =new Date();
				user.setCreatedUser(username);
				user.setCreatedTime(now);
				user.setModifiedUser(username);
				user.setModifiedTime(now);
				addnew(user);
			}else {
				throw new UserConflictException("您输入的用户名("+user.getUsername()+")已经存在");
			}
	}
	
	@Override
	public User login(String username, String password) throws UserConflictException, PasswordNotMatchException {
		User user=findByUsername(username);
		if(user!=null) {
			if(user.getIsDelete().equals(1)) {
				throw new UserNotFoundException("您输入的用户名("+username+")已经被删除");
			}
			String salt=user.getSalt();
			String getMdPassword=getMdPassword(password,salt);
			if(user.getPassword().equals(getMdPassword)) {
				user.setSalt(null);
				user.setPassword(null);
				user.setIsDelete(null);
				return user;
			}else {
				throw new PasswordNotMatchException("密码不正确");
			}
		}else {
			throw new UserNotFoundException("您输入的用户名("+username+")不存在");
		}
	}
	@Override
	public void changePassword(Integer id, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		    User user=findById(id);
		    if(user!=null) {
		    	if(user.getIsDelete().equals(1)) {
		    		throw new UserNotFoundException("您修改的用户("+user.getUsername()+")已经被删除");
		    	}else {
		    		String salt=user.getSalt();
					String getMdPassword=getMdPassword(oldPassword,salt);
					if(user.getPassword().equals(getMdPassword)) {
						String setMdPassword=getMdPassword(newPassword,salt);
						updatePassword(id,setMdPassword,user.getUsername(),new Date());
					}else {
						throw new PasswordNotMatchException("你输入的密码与原密码不匹配");
					}
		    	}
		    }else {
		    	throw new UserNotFoundException("您修改的用户不存在");
		    }
		    
	}
	
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		User data=findById(user.getUid());
		if(data!=null) {
			if(data.getIsDelete().equals(1)) {
				throw new UserNotFoundException("您输入的用户名("+data.getUsername()+")已经被删除");
			}else {
				user.setModifiedUser(data.getUsername());
				user.setModifiedTime(new Date());
				updateInfo(user);
			}
		}else {
			throw new UserNotFoundException("您修改的用户不存在");
		}
		
	}
	@Override
	public User getByUid(Integer id) {
		User data=findById(id);
		//判断用户数据是否存在
		if(data==null) {
			throw new UserNotFoundException("您访问的用户不存在");
		}
		//判断用户是否被删除
		if(data.getIsDelete().equals(1)) {
			throw new UserNotFoundException("您访问的用户不存在");
		}
		//清楚不希望对外显示的数据
		data.setPassword(null);
		data.setSalt(null);
		data.setIsDelete(null);
		return data;
	}
	@Override
	public void changeAvatar(Integer id, String avatar) throws UserNotFoundException, UpdateException {
		User data=findById(id);
		if(data!=null) {
			if(data.getIsDelete().equals(1)) {
				throw new UserNotFoundException("您输入的用户名("+data.getUsername()+")已经被删除");
			}else {
				updateAvatar(id,avatar,data.getUsername(),new Date());
			}
		}else {
			throw new UserNotFoundException("您修改的用户不存在");
		}
	}
	/**
	 * 插入用户数据
	 * @param user
	 * @return 受影响的行数
	 */
	private void addnew(User user) {
		Integer rows= userMapper.addnew(user);
		if(rows!=1) {
			throw new InsertException("未知错误！请联系管理员");
		}
	}
	/**
	 * 根据用户名查找用户信息
	 * @param username
	 * @return 查找匹配数据的用户，没有返回null
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	private String getMdPassword(String password,String salt) {
		String str=password+salt;
		str=DigestUtils.md5DigestAsHex(str.getBytes());
		return str;
	}

	/**
	 * 通过Id查找密码和盐值
	 * @param uid
	 * @return 密码和盐值
	 */
	 private User findById(Integer uid) {
		return userMapper.findById(uid);
	}
	/**
	 * 通过Id修改密码和修改者修改时间
	 * @param uid
	 * @param password
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return 受影响的行数
	 */
	private void updatePassword(Integer uid, String password, String modifiedUser, Date modifiedTime) {
		Integer rows= userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("更新错误！请联系管理员");
		}
	}

	/**
	 * 修改用户信息
	 * @param user
	 */
	private void updateInfo(User user) {
		Integer rows= userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateException("更新错误！请联系管理员");
		}
	}
	
	/**
	 * 执行修改头像
	 * @param uid 用户Id
	 * @param avatar 头像的路径
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	private void  updateAvatar(Integer uid,String avatar,
			String modifiedUser,Date modifiedTime) {
		Integer rows= userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("更新错误！请联系管理员");
		}
	}
}
