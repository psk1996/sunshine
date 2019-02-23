package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;

@Service
public class GoodsServiceImpl implements IGoodsService {
	@Autowired
	GoodsMapper goodsMapper;
	@Override
	public List<Goods> getHotGoods(Integer count) {
		return findHotGoods(count);
	}
	@Override
	public Goods getById(Long id) {
		return findById(id);
	}
	/**
	 * 查询热销商品
	 * @param count
	 * @return 热销商品列表
	 */
	private List<Goods> findHotGoods(Integer count){
		return goodsMapper.findHotGoods(count);
	}
	private Goods findById(Long id){
		return goodsMapper.findById(id);
	}

}
