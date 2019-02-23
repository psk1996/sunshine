package cn.tedu.store.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotMatchException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;


/**
 * 收获地址的业务层实现类
 * @author Administrator
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	IAddressService addressService;
	@Autowired
	ICartService cartService;
	@Override
	public Order createOrder(Integer uid, Integer aid, Integer[] cids, String username)
			throws InsertException, UpdateException {
		List<CartVO> list=cartService.getListCartByUid(uid);
		Long totalPrice = 0L;
		for (CartVO cartVO : list) {
			totalPrice+=cartVO.getPrice()*cartVO.getNum();
		}
		Date now=new Date();
		Order order=new Order();
		order.setUid(uid);
		Address data=addressService.getByAid(aid);
		if(data!=null) {
			order.setReceiver(data.getReceiver());
			order.setPhone(data.getPhone());
			order.setAddress(data.getDistrict()+data.getAddress());
			order.setOrderTime(now);
			order.setState(0);
			order.setTotalPrice(totalPrice);
			order.setCreatedUser(username);
			order.setCreatedTime(now);
			order.setModifiedUser(username);
			order.setModifiedTime(now);
			insertOrder(order);
			for (CartVO cartVO : list) {
				OrderItem orderItem=new OrderItem();
				orderItem.setOid(order.getOid());
				orderItem.setGoodsId(cartVO.getGid());
				orderItem.setGoodsTitle(cartVO.getTitle());
				orderItem.setGoodsPrice(cartVO.getPrice());
				orderItem.setGoodsNum(cartVO.getNum());
				orderItem.setGoodsImage(cartVO.getImage());
				orderItem.setCreatedUser(username);
				orderItem.setCreatedTime(now);
				orderItem.setModifiedUser(username);
				orderItem.setModifiedTime(now);
				insertOrderItem(orderItem);
			}
		}else {
			throw new AddressNotMatchException("地址找不到异常");
		}
		return order;
	}
	/**
	 * 插入订单数据
	 * @param user
	 * @return 受影响的行数
	 */
	private void insertOrder(Order order) {
		Integer rows=orderMapper.insertOrder(order);
		if(rows!=1) {
			throw new InsertException("插入数据异常");
		}
	}
	/**
	 * 插入订单商品数据
	 * @param user
	 * @return 受影响的行数
	 */
	private void insertOrderItem(OrderItem orderItem) {
		Integer rows=orderMapper.insertOrderItem(orderItem);
		if(rows!=1) {
			throw new InsertException("插入数据异常");
		}
	}
}
