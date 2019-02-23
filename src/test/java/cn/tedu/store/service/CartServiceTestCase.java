package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {
	@Autowired
	ICartService cartService;
	@Test
	public void addnew() {
		try {
			Cart cart=new Cart();
			cart.setUid(2);
			cart.setGid(10000002L);
			cart.setNum(4);
			String username="linwei";
			cartService.addToCart(cart, username);
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void findListCartByUid() {
		List<CartVO> list =cartService.getListCartByUid(2);
		for (CartVO cartVO : list) {
			System.err.println("cartVO:"+cartVO);
		}
	}
	@Test
	public void addNum() {
		try {
			Integer cid = 7;
			Integer uid = 2;
			String username = "TICK";
			Integer num = cartService.addNum(cid, uid, username);
			System.err.println("num=" + num);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void findByCids() {
		Integer[] cids= {6,5,4};
		List<CartVO> list =cartService.getByCids(cids);
		for (CartVO cartVO : list) {
			System.err.println("cartVO:"+cartVO);
		}
	}
}
