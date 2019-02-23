package cn.tedu.store.mapper;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.vo.OrderVO;


/**
 * 处理购物车持久层的接口
 * @author Administrator
 *
 */
public interface OrderMapper {
	/**
	 * 插入订单数据
	 * @param user
	 * @return 受影响的行数
	 */
	Integer insertOrder(Order order);
	/**
	 * 插入订单商品数据
	 * @param user
	 * @return 受影响的行数
	 */
	Integer insertOrderItem(OrderItem orderItem);
	/**
	 * 查询订单数据
	 * @param oid
	 * @return 返回订单详情
	 */
	OrderVO findByOid(Integer oid);
	
}
