package cn.tedu.store.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.AddressNotMatchException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.util.ResponseResult;

/**
 * 控制器实现类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
	@Autowired
	IAddressService addressService;
	@RequestMapping("addnew")
	public ResponseResult<Void> handle(Address address,HttpSession session) {
		address.setUid(getUidFromSession(session));
		String username=(String) session.getAttribute("username");
		addressService.addnew(address, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	@RequestMapping("/")
	public ResponseResult<List<Address>> handGetListByUid(HttpSession session) {
		Integer id= getUidFromSession(session);
		List<Address> data=addressService.getListByUid(id);
		return new ResponseResult<List<Address>>(SUCCESS,data);
	}
	@RequestMapping("{aid}/set_default")
	public ResponseResult<Void> setDefault(@PathVariable("aid") Integer aid,
			HttpSession session) throws UpdateException, AddressNotMatchException, AccessDeniedException {
		Integer uid= getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		addressService.setDefault(uid, aid, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	@RequestMapping("{aid}/delete")
	public ResponseResult<Void> deleteByAid(@PathVariable("aid") Integer aid,
			HttpSession session) throws DeleteException, AddressNotMatchException, AccessDeniedException {
		Integer uid= getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		addressService.deleteByAid(aid, uid, username);
		return new ResponseResult<Void>(SUCCESS);
	}
}
