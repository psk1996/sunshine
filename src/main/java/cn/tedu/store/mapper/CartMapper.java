package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

/**
 * 处理购物车持久层的接口
 * @author Administrator
 *
 */
public interface CartMapper {
	/**
	 * 插入购物车数据
	 * @param user
	 * @return 受影响的行数
	 */
	Integer insert(Cart cart);
	/**
	 * 修改购物车商品的数量
	 * @param cid
	 * @param num
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateNum(@Param("num")Integer num, @Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime,@Param("cid")Integer cid);
	/**
	 * 通过uid和gid找到购物车商品
	 * @param uid
	 * @param gid
	 * @return 购物车商品
	 */
	Cart findByUidAndGid(@Param("uid")Integer uid,@Param("gid") Long gid);
	/**
	 * 查询购物车商品信息
	 * @param uid
	 * @return 购物车信息
	 */
	List<CartVO> findListCartByUid(Integer uid);
	/**
	 * 查询购物车数量和用户
	 * @param cid
	 * @return 购物车数量和用户
	 */
	Cart findByCid(Integer cid);
	/**
	 * 查询某些id的购物车数据
	 * @param cid
	 * @return 某些id的购物车数据
	 */
	List<CartVO> findByCids(Integer[] cids);
	
}
