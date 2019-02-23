package cn.tedu.store.service;




import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTestCase {
	@Autowired
	DistrictMapper  districtMapper;
	@Test
	public void findListByParent() {
		List<District> districts=districtMapper.findListByParent("86");
		System.err.println("begin");
		for (District district : districts) {
			System.err.println(district);
		}
		System.err.println("end");
	}
	@Test
	public void findByCode() {
		District district=districtMapper.findByCode("330000");
		System.err.println(district);
	}
}
