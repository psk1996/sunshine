package cn.tedu.store.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileContentTypeException;
import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;
/**
 * 控制器实现类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
	//允许上传的集合
	private static  final List<String> UPLOAD_CONTENT_TYPES=new ArrayList<>();
	//允许上传的大小
	private static final long UPLOAD_CONTENT_SIZE=5*1024*1024;
	//存储上传的文件的文件夹名称
	public static final String UPLOAD_CONTENT_DIR="upload";
	
	/**
	 * 添加允许上传的文件类型
	 */
	static {
		UPLOAD_CONTENT_TYPES.add("image/jpeg");
		UPLOAD_CONTENT_TYPES.add("image/gif");
		UPLOAD_CONTENT_TYPES.add("image/png");
		UPLOAD_CONTENT_TYPES.add("image/bmp");
	}
	@Autowired
	IUserService userService;
	/**
	 * 用户注册
	 * @param user
	 * @return 返回用户成功注册
	 */
	@RequestMapping("reg")
	public ResponseResult<Void> handle(User user) {
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	/**
	 * 用户登陆
	 * @param username 用户名
	 * @param password 密码
	 * @param session 
	 * @return 返回用户对象
	 */
	@RequestMapping("login")
	public ResponseResult<User> handle(@RequestParam("username")String username,
			@RequestParam("password") String password,HttpSession session) {
		User user=userService.login(username, password);
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<User>(SUCCESS,user);
	}
	/**
	 * 修改密码
	 * @param oldPassword 老密码
	 * @param newPassword 新密码
	 * @param session
	 * @return
	 */
	@RequestMapping("change_password")
	public ResponseResult<Void> changePassword(@Param("oldPassword") String oldPassword,
			@Param("newPassword") String newPassword,HttpSession session) {
		Integer uid= getUidFromSession(session);
		userService.changePassword(uid, oldPassword, newPassword);
		return new ResponseResult<Void>(SUCCESS);
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("change_info")
	public ResponseResult<Void> changeInfo(User user,HttpSession session) {
		Integer uid= getUidFromSession(session);
		user.setUid(uid);
		userService.changeInfo(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	/**
	 * 修改用户头像
	 * @param avatar
	 * @param session
	 * @return
	 */
	@RequestMapping("change_avatar")
	public ResponseResult<String> changeAvatar(@RequestParam("avatar") MultipartFile avatar,
			HttpServletRequest request) {
		if(avatar.isEmpty()) {
			throw new FileEmptyException("文件不存在");
		}
		String contentType=avatar.getContentType();
		if(!UPLOAD_CONTENT_TYPES.contains(contentType)) {
			throw new FileContentTypeException("文件格式不符合,不支持"+contentType+"文件格式");
		}
		long size=avatar.getSize();
		if(size>UPLOAD_CONTENT_SIZE) {
			throw new FileSizeException("文件过大,仅+允许上传不超过"+UPLOAD_CONTENT_SIZE/1024/1024+"M的文件");
		}
		//确定上传的文件夹
		String path=request.getServletContext().getRealPath(UPLOAD_CONTENT_DIR);
		File parent=new File(path);
		if(!parent.exists()) {
			parent.mkdirs();
		}
		//获取原文件的名
		String originalFilename=avatar.getOriginalFilename();
		//获取扩展名
		String suffix="";
		int beginIndex=originalFilename.lastIndexOf(".");
		if(beginIndex!=-1) {
			 suffix=originalFilename.substring(beginIndex);
		}
		//上传的文件名
		String filename=UUID.randomUUID()+suffix;
		//保存用户上传的文件
		File dest =new File(parent,filename);
		try {
			avatar.transferTo(dest);
		} catch (IOException e) {
			throw new FileUploadIOException("上传文件时读写错误");
		}
		Integer uid= getUidFromSession(request.getSession());
		String avatarUrl="/"+UPLOAD_CONTENT_DIR+"/"+filename;
		userService.changeAvatar(uid, avatarUrl);
		ResponseResult<String> rr=new ResponseResult<String>();
		rr.setState(SUCCESS);
		rr.setData(avatarUrl);
		return rr;
	}
	/**
	 * 获取用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping("get_info")
	public ResponseResult<User> getInfo(HttpSession session) {
		Integer uid= getUidFromSession(session);
		User user=userService.getByUid(uid);
		return new ResponseResult<User>(SUCCESS,user);
	}
}
