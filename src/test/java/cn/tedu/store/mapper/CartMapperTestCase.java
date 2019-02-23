package cn.tedu.store.mapper;

import java.util.Date;
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
public class CartMapperTestCase {
	@Autowired
	CartMapper cartMapper;
	@Test
	public void insert() {
		Cart cart=new Cart();
		cart.setGid(10000001L);
		cart.setUid(1);
		cart.setNum(1);
		cart.setCreatedUser("Mark");
		cart.setCreatedTime(new Date());
		cartMapper.insert(cart);
		System.err.println("插入成功");
	}
	@Test
	public void updateNum() {
		Integer row=cartMapper.updateNum(2,"Tony",new Date(),1);
		System.err.println("row:"+row);
	}
	@Test
	public void findByUidAndGid() {
		Cart cart =cartMapper.findByUidAndGid(1, 10000001L);
		System.err.println("cart"+cart);
	}
	@Test
	public void findListCartByUid() {
		List<CartVO> list =cartMapper.findListCartByUid(2);
		for (CartVO cartVO : list) {
			System.err.println("cartVO:"+cartVO);
		}
	}
	@Test
	public void findByCid() {
		Cart cart =cartMapper.findByCid(4);
		System.err.println("cart"+cart);
	}
	@Test
	public void findByCids() {
		Integer[] cids= {6,5,4};
		List<CartVO> list =cartMapper.findByCids(cids);
		for (CartVO cartVO : list) {
			System.err.println("cartVO:"+cartVO);
		}
	}
	
}
