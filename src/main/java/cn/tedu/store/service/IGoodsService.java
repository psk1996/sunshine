package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

/**
 * 商品数据的业务层接口
 * @author Administrator
 *
 */
public interface IGoodsService {
	/**
	 * 查询热销商品
	 * @param count
	 * @return 热销商品列表
	 */
	List<Goods> getHotGoods(Integer count);
	/**
	 * 查询某个商品
	 * @param id
	 * @return 某个商品详细列表
	 */
	Goods getById(Long id);
}
