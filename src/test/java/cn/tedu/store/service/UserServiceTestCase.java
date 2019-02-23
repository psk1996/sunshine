package cn.tedu.store.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	IUserService userService;
	@Test
	public void reg() {
		try {
			User user=new User();
			user.setUsername("service3");
			user.setPassword("123546");
			user.setPhone("137");
			user.setEmail("7075@");
			user.setGender(1);
			user.setAvatar("http://");
			userService.reg(user);
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void login() {
		try {
			String username="service";
			String password="123546";
			User user=userService.login(username, password);
			System.err.println("登陆成功:"+user);
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void change() {
		try {
			Integer id=2;
			String oldPassword="123546";
			String newPassword="123456";
			userService.changePassword(id, oldPassword, newPassword);
			System.err.println("修改成功");
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void change2() {
		try {
			User user=new User();
			user.setUid(2);
			user.setPhone("183");
			user.setEmail("138@");
			user.setGender(1);
			userService.changeInfo(user);;
			System.err.println("修改成功");
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void getByUid() {
		User user=userService.getByUid(12);
		System.err.println("user:"+user);
	}
	@Test
	public void changeAvatar() {
		try {
			Integer id=12;
			String avatar="456789";
			userService.changeAvatar(id, avatar);
			System.err.println("修改成功");
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}
