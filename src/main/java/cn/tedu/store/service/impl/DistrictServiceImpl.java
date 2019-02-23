package cn.tedu.store.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

/**
 * 省市区的业务层实现类
 * @author Administrator
 *
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
	@Autowired
	DistrictMapper districtMapper;

	@Override
	public List<District> findListByParent(String parent) {
		return getListByParent(parent);
	}

	@Override
	public District findByCode(String code) {
		return getByCode(code);
	}
	/**
	 * 根据父级代号返回省市区的列表
	 * @param parent
	 * @return 市的集合
	 */
	private List<District> getListByParent(String  parent){
		return districtMapper.findListByParent(parent);
	}
	/**
	 * 根据省市区的代号获取详情
	 * @param code
	 * @return 返回district对象
	 */
	private District getByCode(String code) {
		return districtMapper.findByCode(code);
	}
	
	
}
