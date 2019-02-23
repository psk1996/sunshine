package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	@Autowired
	UserMapper userMapper;
	@Test
	public void addnew() {
		User user=new User();
		user.setUsername("zhangsan");
		user.setPassword("123454");
		userMapper.addnew(user);
		System.err.println("插入成功");
	}
	@Test
	public void findById() {
		User user=userMapper.findById(2);
		System.err.println("user:"+user);
	}
	@Test
	public void updatePassword() {
		Date now=new Date();
		Integer uid=4;
		String password="456789";
		String modifiedUser="Tony";
		Date modifiedTime=now;
		Integer rows=userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.err.println("rows:"+rows);
	}
	@Test
	public void updateInfo() {
		User user=new User();
		user.setUid(2);
		user.setPhone("1836802");
		user.setEmail("70752616@");
		user.setGender(1);
		user.setCreatedUser("Tony");
		user.setCreatedTime(new Date());
		Integer rows=userMapper.updateInfo(user);
		System.err.println("rows:"+rows);
	}
	@Test
	public void updateAvatar() {
		Date now=new Date();
		Integer uid=12;
		String avatar="1234";
		String modifiedUser="jack";
		Date modifiedTime=now;
		Integer rows=userMapper.updatePassword(uid, avatar, modifiedUser, modifiedTime);
		System.err.println("rows:"+rows);
	}
}
