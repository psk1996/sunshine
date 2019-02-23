package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {
	@Autowired
	AddressMapper addressMapper;
	@Test
	public void insert() {
		Address address=new Address();
		address.setUid(12);;
		addressMapper.insert(address);
		System.err.println("插入成功");
	}
	@Test
	public void getCountByUid() {
		Integer row=addressMapper.getCountByUid(12);
		System.err.println("row:"+row);
	}
	@Test
	public void findListByUid() {
		List<Address> list =addressMapper.findListByUid(2);
		System.err.println("begin");
		for (Address address : list) {
			System.err.println(address);
		}
		System.err.println("end");
	}
	@Test
	public void findByAid() {
		Address address =addressMapper.findByAid(2);
		System.err.println(address);
	}
	@Test
	public void updateNonDefault() {
		Integer row=addressMapper.updateNonDefault(2,"tony",new Date());
		System.err.println("row:"+row);
	}
	@Test
	public void updateDefault() {
		Integer row=addressMapper.updateDefault(4,"jack",new Date());
		System.err.println("row:"+row);
	}
	@Test
	public void findLastModifiedByUid() {
		Address address=addressMapper.findLastModifiedByUid(2);
		System.err.println("address:"+address);
	}
	@Test
	public void deleteByAid() {
		Integer row=addressMapper.deleteByAid(8);
		System.err.println("row:"+row);
	}
	
}
