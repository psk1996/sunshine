package cn.tedu.store.mapper;

import cn.tedu.store.entity.Logo;
import org.apache.ibatis.annotations.Param;

public interface LogoMapper {

    /**
     * //插入新的一条数据
     * @param logo
     * @return
     */
    Integer addNew(Logo logo);

    /**
     * //根据id删除一条logo
     * @param id
     * @return
     */
    Integer deleteOne(Integer id);

    /**
     * 根据id修改一条数据
     * @param id
     * @return
     */
    Integer updateOne(@Param("id")Integer id);

    /**
     * 根据Logo名字查询一条数据
     * @param LogoName
     * @return
     */
    String selectOne(String LogoName);



}
