package cn.tedu.store.service;



import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;


/**
 * 购物车数据的业务层接口
 * @author Administrator
 *
 */
public interface ICartService {
	/**
	 * 新增购物车数据
	 * @param Cart 
	 * @param username 操作人
	 */
	void addToCart(Cart cart,String username) throws InsertException,UpdateException;
	/**
	 * 查询购物车商品信息
	 * @param uid
	 * @return 购物车信息
	 */
	List<CartVO> getListCartByUid(Integer uid);
	/**
	 * 增加商品数量
	 * @param cid
	 * @param uid
	 * @param username
	 * @return
	 * @throws CartNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	Integer addNum(Integer cid, Integer uid, String username) throws CartNotFoundException, AccessDeniedException, UpdateException;
	/**
	 * 查询某些id的购物车数据
	 * @param cids
	 * @return
	 */
	List<CartVO> getByCids(Integer[] cids);
}
