package cn.tedu.store.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTestCase {
	@Autowired
	GoodsMapper goodMapper;
	@Test
	public void findHotGoods() {
		List<Goods> list=goodMapper.findHotGoods(10);
		for (Goods goods : list) {
			System.err.println("goods:"+goods);
		}
	}
	@Test
	public void findById() {
		Goods goods=goodMapper.findById(10000001l);
		System.err.println("goods:"+goods);
		
	}
}
