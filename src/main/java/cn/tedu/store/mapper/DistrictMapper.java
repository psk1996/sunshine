package cn.tedu.store.mapper;

import java.util.List;
import cn.tedu.store.entity.District;

/**
 * 处理用户持久层的接口
 * @author Administrator
 *
 */
public interface DistrictMapper {
	/**
	 * 根据父级代号返回省市区的列表
	 * @param parent
	 * @return 市的集合
	 */
	List<District> findListByParent(String  parent);
	/**
	 * 根据省市区的代号获取详情
	 * @param code
	 * @return 返回district对象
	 */
	District findByCode(String code);
}
