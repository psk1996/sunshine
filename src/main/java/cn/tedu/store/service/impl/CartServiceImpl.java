package cn.tedu.store.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.AccessDeniedException;
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
public class CartServiceImpl implements ICartService {
	@Autowired
	CartMapper cartMapper;
	@Override
	public void addToCart(Cart cart, String username) throws InsertException,UpdateException{
		Date now=new Date();
		Cart data=findByUidAndGid(cart.getUid(),cart.getGid());
		if(data==null) {
			cart.setCreatedUser(username);
			cart.setCreatedTime(now);
			insert(cart);
		}else {
			Integer num=data.getNum()+cart.getNum();
			updateNumInteger(num,username,now,data.getCid());
		}
	}
	@Override
	public List<CartVO> getListCartByUid(Integer uid) {
		return findListCartByUid(uid);
	}
	@Override
	public Integer addNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
				// 根据参数cid查询数据
				Cart data = findByCid(cid);
				// 判断查询结果是否为null
				if (data == null) {
					// 是：抛出异常：CartNotFoundException
					throw new CartNotFoundException(
						"增加数量失败！尝试访问的数据不存在，可能已经被删除！");
				}

				// 判断查询结果中的uid与参数uid是否不同
				if (!data.getUid().equals(uid)) {
					// 是：抛出异常：AccessDeniedException
					throw new AccessDeniedException(
						"增加数量失败！非法访问！");
				}
				// 从查询结果中取出当前的num
				Integer num = data.getNum();
				// 将num自增
				num++;
				// 创建当前时间对象
				Date now = new Date();
				// 更新：updateNum(cid, num, modifiedUser, modifiedTime)
				updateNumInteger(num, username, now,cid);
				// 返回
				return num;
	}
	@Override
	public List<CartVO> getByCids(Integer[] cids) {
		
		return findByCids(cids);
	}
	/**
	 * 插入购物车数据
	 * @param user
	 * @return 受影响的行数
	 */
	private void insert(Cart cart) {
		Integer rows= cartMapper.insert(cart);
		if(rows!=1) {
			throw new InsertException("新增购物车数据出现未知错误");
		}
	}
	/**
	 * 修改购物车商品的数量
	 * @param cid
	 * @param num
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	private void updateNumInteger(Integer num, String modifiedUser,
			Date modifiedTime,Integer cid) {
		Integer rows=cartMapper.updateNum(num, modifiedUser, modifiedTime, cid);
		if(rows!=1) {
			throw new UpdateException("更新数据异常");
		}
	}
	/**
	 * 通过uid和gid找到购物车商品
	 * @param uid
	 * @param gid
	 * @return 购物车商品
	 */
	private Cart findByUidAndGid(Integer uid, Long gid) {
		return cartMapper.findByUidAndGid(uid, gid);
	}
	/**
	 * 查询购物车商品信息
	 * @param uid
	 * @return 购物车信息
	 */
	private List<CartVO> findListCartByUid(Integer uid){
		return cartMapper.findListCartByUid(uid);
	}
	private Cart findByCid(Integer cid) {
		return cartMapper.findByCid(cid);
	}
	private List<CartVO> findByCids(Integer[] cids){
		return cartMapper.findByCids(cids);
	}
}
