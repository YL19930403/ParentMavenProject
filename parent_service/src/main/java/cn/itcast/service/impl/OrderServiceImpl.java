package cn.itcast.service.impl;


import cn.itcast.dao.IOrderDao;
import cn.itcast.service.IOrderService;
import com.github.pagehelper.PageHelper;
import itcast.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderDao iOrderDao;

    @Override
    public Order findById(Integer id) throws Exception {
        return iOrderDao.findById(id);
    }

    @Override
    public List<Order> findAll(Integer page, Integer size) throws Exception {
        // pageNum代表页码值，pageSize代表每页条数
        PageHelper.startPage(page, size);
        return iOrderDao.findAll();
    }
}
