package cn.tedu.store.mapper;

import java.util.List;
import cn.tedu.store.entity.Goods;

/**
 * 处理用户持久层的接口
 * @author Administrator
 *
 */
public interface GoodsMapper {
	/**
	 * 查询热销商品
	 * @param count
	 * @return 热销商品列表
	 */
	List<Goods> findHotGoods(Integer count);
	/**
	 * 查询某个商品
	 * @param id
	 * @return 某个商品详细列表
	 */
	Goods findById(Long id);
}
