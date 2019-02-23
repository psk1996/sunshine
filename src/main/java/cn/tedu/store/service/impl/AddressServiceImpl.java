package cn.tedu.store.service.impl;


import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AddressNotMatchException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;

/**
 * 收获地址的业务层实现类
 * @author Administrator
 *
 */
@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	AddressMapper addressMapper;
	@Autowired
	IDistrictService districtService;
	@Override
	public void addnew(Address address, String username) throws InsertException{
		//TODO 确定district的值
		String district=getDistrictByCodes(address.getProvince(),address.getCity(),address.getArea());
		address.setDistrict(district);
		Integer count= getCountByUid(address.getUid());
		address.setIsDefault(count==0?1:0);
		Date now=new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		insert(address);
	}
	
	@Override
	public List<Address> getListByUid(Integer uid) {
		return findListByUid(uid);
	}

	@Override
	@Transactional
	public void setDefault(Integer uid, Integer aid, String username)
			throws UpdateException, AddressNotMatchException, AccessDeniedException {
		Address address=findByAid(aid);
		if(address!=null) {
			if(address.getUid()==uid) {
				Date now=new Date();
				updateNonDefault(uid,username,now);
				updateDefault(aid,username,now);
			}else {
				throw new AccessDeniedException("当前修改的地址不是登陆用户地址异常");
			}
		}else {
			throw new AddressNotMatchException("地址找不到异常");
		}
		
	}

	@Override
	public void deleteByAid(Integer aid, Integer uid,String username)
			throws DeleteException, AddressNotMatchException, AccessDeniedException {
		Address address=findByAid(aid);
		if(address!=null) {
			if(address.getUid()==uid) {
				getByAid(aid);
				if(address.getIsDefault()==1) {
					Integer count=getCountByUid(uid);
					if(count>0) {
						Address add=findLastModifiedByUid(uid);
						Integer lastAid=add.getAid();
						Date now=new Date();
						addressMapper.updateDefault(lastAid, username, now);
					}else {
						throw new AddressNotMatchException("地址找不到异常");
					}
				}
			}else {
				throw new AccessDeniedException("当前修改的地址不是登陆用户地址异常");
			}
		}else {
			throw new AddressNotMatchException("地址找不到异常");
		}
	}

	@Override
	public Address getByAid(Integer aid) {
		return findByAid(aid);
	}

	/**
	 * 插入地址信息
	 * @param address
	 */
	private void insert(Address address) {
		Integer row= addressMapper.insert(address);
		if(row!=1) {
			throw new InsertException("新增收获地址出现未知错误");
		}
	}
	/**
	 * 查询用户id
	 * @param uid
	 * @return 受影响的行数
	 */
	private Integer getCountByUid(Integer uid) {
		return addressMapper.getCountByUid(uid);
	}
	/**
	 * 根据省市区获取地区的名称
	 * @param province 省的代号
	 * @param city 市的代号
	 * @param area 区的代号
	 * @return
	 */
	private String getDistrictByCodes(String province, String city, String area) {
		String provinceName=null;
		String cityName=null;
		String areaName=null;
		District p=districtService.findByCode(province);
		District c=districtService.findByCode(city);
		District a=districtService.findByCode(area);
		if(p!=null) {
			provinceName=p.getName();
		}
		if(c!=null) {
			cityName=c.getName();
		}
		if(a!=null) {
			areaName=a.getName();
		}
		return provinceName+cityName+areaName;
	}

	/**
	 * 获取指定用户的收获地址列表
	 * @param uid 用户的uid
	 * @return 该用户的收货地址列表
	 */
	private List<Address> findListByUid(Integer uid){
		return addressMapper.findListByUid(uid);
	}
	/**
	 * 通过aid找到该地址
	 * @param aid
	 * @return
	 */
	private Address findByAid(Integer aid) {
		 return addressMapper.findByAid(aid);
	}
	/**
	 * 设置该用户所有地址为非默认地址
	 * @param uid 用户id
	 * @param modifiedUesr 修改者
	 * @param modifiedTime 修改时间
	 */
	private void updateNonDefault( Integer uid,String modifiedUser, Date modifiedTime) {
		Integer rows=addressMapper.updateNonDefault(uid, modifiedUser, modifiedTime);
		if(rows<1) {
			throw new UpdateException("数据更新异常");
		}
	}
	/**
	 * 通过aid设置为默认地址
	 * @param aid 地址id
	 * @param modifiedUesr 修改者
	 * @param modifiedTime 修改时间
	 */
	private void updateDefault( Integer aid,String modifiedUesr,Date modifiedTime) {
		Integer rows =addressMapper.updateDefault(aid, modifiedUesr, modifiedTime);
		if(rows!=1) {
			throw new AddressNotMatchException("地址不匹配异常");
		}
	}
	/**
	 * 删除某个地址
	 * @param id
	 * @return 受影响的行数
	 */
	private void deleteByAid(Integer id) {
		Integer rows=addressMapper.deleteByAid(id);
		if(rows!=1) {
			throw new AddressNotMatchException("地址没找到异常");
		}
	}
	/**
	 * 查询最近一次添加的地址
	 * @param uid
	 * @return
	 */
	private Address findLastModifiedByUid(Integer uid) {
		return addressMapper.findLastModifiedByUid(uid);
	}
}
