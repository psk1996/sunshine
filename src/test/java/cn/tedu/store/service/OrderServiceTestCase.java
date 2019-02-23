package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.Order;
import cn.tedu.store.service.ex.ServiceException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTestCase {
	@Autowired
	IOrderService orderService;
	@Test
	public void createOrder() {
		try {
			Integer uid=2;
			Integer aid=5;
			Integer[] cids= {6,5,4};
			String username="linwei";
			Order order=orderService.createOrder(uid, aid, cids, username);
			System.err.println("order:"+order);
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
}
