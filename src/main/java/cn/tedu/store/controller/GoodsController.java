package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.util.ResponseResult;

/**
 * 控制器实现类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController {
	@Autowired
	IGoodsService goodsService;
	@RequestMapping("hot")
	public ResponseResult<List<Goods>> getHotGoods() {
		List<Goods> data=goodsService.getHotGoods(4);
		return new ResponseResult<List<Goods>>(SUCCESS,data);
	}
	@RequestMapping("{id}/details")
	public ResponseResult<Goods> getById(@PathVariable("id") Long id) {
		Goods data=goodsService.getById(id);
		return new ResponseResult<Goods>(SUCCESS,data);
	}
}
