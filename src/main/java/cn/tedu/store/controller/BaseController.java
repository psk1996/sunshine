package cn.tedu.store.controller;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.ExceptionHandler;
import cn.tedu.store.controller.ex.FileContentTypeException;
import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileUploadException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.service.ex.AddressNotMatchException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserConflictException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.util.ResponseResult;

/**
 * 控制器的基类
 * @author Administrator
 *
 */
public class BaseController {
	public static final int SUCCESS=200;
	/**
	 * 从session中获取ID
	 * @param session
	 * @return 当前登录的ID值
	 */
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public ResponseResult<Void> handleException(Exception e) {
		ResponseResult<Void> rr=new ResponseResult<Void>();
		rr.setMessage(e.getMessage());
		//-400-用户插入异常
		if(e instanceof UserConflictException) {
			rr.setState(400);
		}
		//-401-用户不存在异常
		else if(e instanceof UserNotFoundException) {
			rr.setState(401);
		}
		//-402-密码不正确异常
		else if(e instanceof PasswordNotMatchException) {
			rr.setState(402);
		}
		//-403-地址没找到异常
		else if(e instanceof AddressNotMatchException) {
				rr.setState(403);
		}
		//-404-用户不匹配异常
		else if(e instanceof AccessDeniedException) {
				rr.setState(404);
		}
		//-500-插入异常
		else if(e instanceof InsertException) {
			rr.setState(500);
		}
		//-501-更新异常
		else if(e instanceof UpdateException) {
			rr.setState(501);
		}
		//-502-删除异常
		else if(e instanceof DeleteException) {
			rr.setState(501);
		}
		//-601-文件为空异常
		else if(e instanceof FileEmptyException) {
			rr.setState(601);
		}
		//-602-文件不匹配异常
		else if(e instanceof FileContentTypeException) {
			rr.setState(602);
		}
		//-603-文件大小异常
		else if(e instanceof FileSizeException) {
			rr.setState(603);
		}
		//-603-文件读写异常
		else if(e instanceof FileUploadIOException) {
			rr.setState(604);
		}
		//-701-购物车没找到异常
		else if(e instanceof CartNotFoundException) {
			rr.setState(604);
		}
		return rr;
	}
}
