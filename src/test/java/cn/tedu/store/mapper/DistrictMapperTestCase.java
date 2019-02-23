package cn.tedu.store.mapper;


import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.District;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {
	@Autowired
	DistrictMapper DistrictMapper;
	@Test
	public void findListByParent() {
		List<District> data=DistrictMapper.findListByParent("86");
		System.err.println("begin");
		for (District district : data) {
			System.err.println(district);
		}
		System.err.println("end");
	}
	@Test
	public void findByCode() {
		District district=DistrictMapper.findByCode("330000");
		System.err.println("查询成功"+district);
	}
	
}
