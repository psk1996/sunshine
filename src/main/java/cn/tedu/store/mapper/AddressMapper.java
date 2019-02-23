package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

/**
 * 处理用户持久层的接口
 * @author Administrator
 *
 */
public interface AddressMapper {
	/**
	 * 插入用户数据
	 * @param user
	 * @return 受影响的行数
	 */
	Integer insert(Address address);
	/**
	 * 查询用户地址数量
	 * @param uid
	 * @return 受影响的行数
	 */
	Integer getCountByUid(Integer uid);
	
	/**
	 * 获取指定用户的收获地址列表
	 * @param uid 用户的uid
	 * @return 该用户的收货地址列表
	 */
	List<Address> findListByUid(Integer uid);
	/**
	 * 通过aid找到该地址
	 * @param aid
	 * @return
	 */
	Address findByAid(Integer aid);
	/**
	 * 设置该用户所有地址为非默认地址
	 * @param uid 用户id
	 * @param modifiedUesr 修改者
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	Integer updateNonDefault(@Param("uid") Integer uid,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	/**
	 * 通过aid设置为默认地址
	 * @param aid 地址id
	 * @param modifiedUesr 修改者
	 * @param modifiedTime 修改时间
	 * @return 受影响的行数
	 */
	Integer updateDefault(@Param("aid") Integer aid,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	/**
	 * 删除某个地址
	 * @param id
	 * @return 受影响的行数
	 */
	Integer deleteByAid(Integer id);
	/**
	 * 查询最近一次添加的地址
	 * @param uid
	 * @return
	 */
	Address findLastModifiedByUid(Integer uid);
}
