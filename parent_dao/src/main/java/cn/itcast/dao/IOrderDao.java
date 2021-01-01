package cn.itcast.dao;

import itcast.domain.Member;
import itcast.domain.Order;
import itcast.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.codehaus.plexus.component.annotations.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDao {

    @Select("SELECT * FROM `order` WHERE id = #{id}")
    @Results({
//            column为数据库字段名，porperty为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id=true),
            @Result(column = "order_no", property = "order_no", jdbcType = JdbcType.VARCHAR),
            @Result(column = "order_time", property = "order_time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "people_count", property = "people_count", jdbcType = JdbcType.INTEGER),
            @Result(column = "order_desc", property = "order_desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pay_type", property = "pay_type", jdbcType = JdbcType.INTEGER, javaType = Integer.class),
            @Result(column = "order_status", property = "order_status", jdbcType = JdbcType.INTEGER),
            @Result(column = "product_id", property = "product", jdbcType = JdbcType.INTEGER, javaType = Product.class, one = @One(select = "cn.itcast.dao.IProductDao.findById")),
            @Result(column = "member_id", property = "member", jdbcType = JdbcType.INTEGER, javaType = Member.class, one = @One(select = "cn.itcast.dao.IMemberDao.findById")),
            // 拿订单的id去关联表中查询出来的数据 (order_people表)
            @Result(column = "id", property = "peoples", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.IOrderPeopleDao.findByOrderId")),
    })
    public abstract Order findById(Integer id) throws Exception;

    @Select("SELECT * FROM `order`")
    @Results({
////            column为数据库字段名，porperty为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id=true),
            @Result(column = "order_no", property = "order_no", jdbcType = JdbcType.VARCHAR, javaType = String.class),
            @Result(column = "order_time", property = "order_time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "people_count", property = "people_count", jdbcType = JdbcType.INTEGER),
            @Result(column = "order_desc", property = "order_desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pay_type", property = "pay_type", jdbcType = JdbcType.INTEGER),
            @Result(column = "order_status", property = "order_status", jdbcType = JdbcType.INTEGER),
            @Result(column = "product_id", property = "product", jdbcType = JdbcType.INTEGER, javaType = Product.class, one = @One(select = "cn.itcast.dao.IProductDao.findById")),
            @Result(column = "member_id", property = "member", jdbcType = JdbcType.INTEGER, javaType = Member.class, one = @One(select = "cn.itcast.dao.IMemberDao.findById")),
            @Result(column = "id", property = "peoples", javaType = java.util.List.class, many = @Many(select = "cn.itcast.dao.IOrderPeopleDao.findByOrderId")),
    })
    public abstract List<Order> findAll() throws Exception;
}
