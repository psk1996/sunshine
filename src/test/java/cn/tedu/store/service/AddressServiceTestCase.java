package cn.tedu.store.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
	@Autowired
	IAddressService addressService;
	@Test
	public void addnew() {
		try {
			Address address=new Address();
			address.setUid(8);
			String username="linwei";
			addressService.addnew(address, username);
		}catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void getListByUid() {
		List<Address> list =addressService.getListByUid(2);
		System.err.println("begin");
		for (Address address : list) {
			System.err.println(address);
		}
		System.err.println("end");
	}
	@Test
	public void setDefault() {
		try {
			addressService.setDefault(10, 8,"MARk");
			System.err.println("ok");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	@Test
	public void deleteByAid() {
		try {
			addressService.deleteByAid(2, 6, "Tony");
			System.err.println("ok");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
}
