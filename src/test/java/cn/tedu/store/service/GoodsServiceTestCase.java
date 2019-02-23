package cn.tedu.store.service;


import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTestCase {
	@Autowired
	IGoodsService goodsService;
	@Test
	public void getHotGoods() {
		List<Goods> list=goodsService.getHotGoods(10);
		for (Goods goods : list) {
			System.err.println("goods:"+goods);
		}
	}
	@Test
	public void getById() {
		Goods goods=goodsService.getById(10000001l);
		System.err.println("goods:"+goods);
		
	}
}
