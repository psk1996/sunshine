package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.tedu.store.entity.Order;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.util.ResponseResult;
/**
 * 控制器实现类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
	@Autowired
	IOrderService orderService;
	@RequestMapping("create")
	public ResponseResult<Order> create(@RequestParam("aid") Integer aid,
			@RequestParam("cids") Integer[] cids,HttpSession session) {
		Integer uid= getUidFromSession(session);
		String username=(String) session.getAttribute("username");
		Order order=orderService.createOrder(uid, aid, cids, username);
		return new ResponseResult<Order>(SUCCESS,order);
	}

}
