package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.ResponseResult;
import cn.tedu.store.vo.CartVO;

/**
 * 控制器实现类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
	@Autowired
	ICartService cartService;
	@RequestMapping("add")
	public ResponseResult<Void> addToCart(@RequestParam("gid") Long gid,
			@RequestParam("num") Integer num,HttpSession session) {
		Integer uid= getUidFromSession(session);
		String username=(String) session.getAttribute("username");
		Cart cart=new Cart();
		cart.setUid(uid);
		cart.setGid(gid);
		cart.setNum(num);
		cartService.addToCart(cart, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	@RequestMapping("{cid}/add_num")
	public ResponseResult<Integer> addNum(
		@PathVariable("cid") Integer cid,
		HttpSession session){
		// 从Session中获取当前登录的用户的信息
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		// 执行
		Integer num = cartService.addNum(cid, uid, username);
		// 返回
		return new ResponseResult<Integer>(SUCCESS, num);
	}
	@RequestMapping("/")
	public ResponseResult<List<CartVO>> getByCids(HttpSession session,String by,Integer[] cids){
		Integer uid= getUidFromSession(session);
		List<CartVO> data;
		if("cids".equals(by)) {
			data=cartService.getByCids(cids);
		}else {
			data=cartService.getListCartByUid(uid);
		}
		
		return new ResponseResult<List<CartVO>>(SUCCESS,data);
	}
}
