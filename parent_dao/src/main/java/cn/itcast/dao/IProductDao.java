package cn.itcast.dao;

import itcast.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {
    // 查询产品列表
    @Select("SELECT * FROM `product`")
    public abstract List<Product> findAll() throws Exception;

    @Insert("INSERT INTO product(product_no, product_name, city_name, departure_time, product_price, product_status,product_desc) " +
            "VALUES(#{product_no},#{product_name},#{city_name}, #{departure_time}, #{product_price}, #{product_status}, #{product_desc})")
    public abstract Integer saveProduct(Product product) throws Exception;

    @Select("SELECT * FROM `product` WHERE id=#{id}")
    public abstract Product findById(Integer id) throws Exception;

    @Update("UPDATE product set product_name=#{product_name}, city_name=#{city_name}, departure_time=#{departure_time}, product_price=#{product_price}, product_desc=#{product_desc},product_status=#{product_status} WHERE product_no = #{product_no} ")
    public abstract Integer editProduct(Product product) throws Exception;

    @Delete("DELETE FROM product WHERE id in (#{productIds})")
    public abstract Integer deleteProduct(String productIds) throws Exception;
}
