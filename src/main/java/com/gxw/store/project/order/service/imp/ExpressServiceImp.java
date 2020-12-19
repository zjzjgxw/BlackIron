package com.gxw.store.project.order.service.imp;

import com.gxw.store.project.order.entity.Express;
import com.gxw.store.project.order.mapper.ExpressMapper;
import com.gxw.store.project.order.service.ExpressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExpressServiceImp implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;

    @Override
    public List<Express> select() {
        return expressMapper.select();
    }

    @Override
    public String getExpressName(Long id) {
        return expressMapper.getExpressName(id);
    }

}
