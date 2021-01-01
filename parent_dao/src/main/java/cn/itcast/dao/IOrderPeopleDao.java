package cn.itcast.dao;

import itcast.domain.OrderPeople;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderPeopleDao {

    @Select("SELECT * FROM `order_people` WHERE order_id=#{order_id}")
    public abstract List<OrderPeople> findByOrderId(Integer order_id) throws Exception;
}
