package cn.tedu.store.service;




import cn.tedu.store.entity.Order;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;


/**
 * 购物车数据的业务层接口
 * @author Administrator
 *
 */
public interface IOrderService {
	/**
	 * 新增购物车数据
	 * @param Cart 
	 * @param username 操作人
	 */
	Order createOrder(Integer uid,Integer aid,Integer[] cids,String username) throws InsertException,UpdateException;

}
