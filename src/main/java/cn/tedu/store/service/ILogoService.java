package cn.tedu.store.service;

import cn.tedu.store.entity.Logo;


public interface ILogoService {

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
    Integer updateOne(Integer id);

    /**
     * 根据Logo名字查询一条数据
     * @param id
     * @return
     */
    Logo selectOne(Integer id);


}
