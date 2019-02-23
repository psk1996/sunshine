package cn.tedu.store.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.vo.OrderVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestCase {
	@Autowired
	OrderMapper orderMapper;
	@Test
	public void insertOrder() {
		Order order=new Order();
		order.setUid(2);
		order.setReceiver("linwei");
		order.setTotalPrice(998L);
		orderMapper.insertOrder(order);
		System.err.println("插入成功");
	}
	@Test
	public void insertOrderItem() {
		OrderItem orderItem=new OrderItem();
		orderItem.setOid(1);
		orderItem.setGoodsId(10000001L);
		orderItem.setGoodsTitle("饮料");
		orderItem.setGoodsPrice(3L);
		orderItem.setGoodsNum(6);
		orderMapper.insertOrderItem(orderItem);
		System.err.println("插入成功");
	}
	@Test
	public void findByOid() {
		OrderVO data=orderMapper.findByOid(15);
		System.err.println("data:"+data);
	}
}
