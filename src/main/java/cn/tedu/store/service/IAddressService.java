package cn.tedu.store.service;


import java.nio.file.AccessDeniedException;
import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.AddressNotMatchException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

/**
 * 地址数据的业务层接口
 * @author Administrator
 *
 */
public interface IAddressService {
	/**
	 * 新增用户地址
	 * @param address 用户地址信息
	 * @param username  操作人
	 * @throws InsertException 插入数据异常
	 */
	void addnew(Address address,String username) throws InsertException;
	/**
	 * 获取指定用户的收获地址列表
	 * @param uid
	 * @return 该用户的收货地址列表
	 */
	List<Address> getListByUid(Integer uid);
	/**
	 * 设置用户的所有地址为非默认地址并设置指定地址为默认地址
	 * @param uid
	 * @param aid
	 * @param username
	 * @throws UpdateException
	 * @throws AddressNotMatchException
	 * @throws AccessDeniedException
	 */
	void setDefault(Integer uid,Integer aid,String username) throws UpdateException,
	AddressNotMatchException,AccessDeniedException;
	/**
	 * 删除指定的地址
	 * @param aid 
	 * @param uid
	 * @throws DeleteException
	 * @throws AddressNotMatchException
	 * @throws AccessDeniedException
	 */
	void deleteByAid(Integer aid,Integer uid,String username) throws DeleteException,
	AddressNotMatchException,AccessDeniedException;
	/**
	 * 通过地址id找到地址
	 * @param aid
	 * @return 地址信息
	 */
	Address getByAid(Integer aid);
}
