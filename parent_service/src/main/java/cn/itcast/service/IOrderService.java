package cn.itcast.service;

import itcast.domain.Order;

import java.util.List;

public interface IOrderService {
    public abstract Order findById(Integer id) throws Exception;

    //Integer page, Integer size
    public abstract List<Order> findAll(Integer page, Integer size) throws Exception;
}
