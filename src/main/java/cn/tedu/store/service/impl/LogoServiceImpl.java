package cn.tedu.store.service.impl;


import cn.tedu.store.entity.Logo;
import cn.tedu.store.mapper.LogoMapper;
import cn.tedu.store.service.ILogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收获地址的业务层实现类
 *
 * @author Administrator
 */
@Service
public class LogoServiceImpl implements ILogoService {
    @Autowired
    private LogoMapper mapper;

    @Override
    public Integer addNew(Logo logo) {

        return mapper.addNew(logo);

    }

    @Override
    public Integer deleteOne(Integer id) {
        return mapper.deleteOne(id);
    }

    @Override
    public Integer updateOne(Integer id) {
        return mapper.updateOne(id);
    }

    @Override
    public Logo selectOne(Integer id) {
        return mapper.selectOne(id);
    }


}