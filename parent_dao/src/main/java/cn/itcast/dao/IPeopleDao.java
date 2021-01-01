package cn.itcast.dao;

import itcast.domain.People;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeopleDao {

    @Select("SELECT * FROM people WHERE id IN (SELECT people_id FROM order_people WHERE order_id=#{order_id})")
    public abstract List<People> findByOrderId(Integer order_id) throws Exception;
}
