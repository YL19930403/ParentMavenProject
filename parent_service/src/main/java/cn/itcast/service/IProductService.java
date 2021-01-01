package cn.itcast.service;

import itcast.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {
    public abstract List<Product> findAll() throws Exception;

    public abstract Integer saveProduct(Product product) throws Exception;

    public abstract Product findById(Integer id) throws Exception;

    public abstract Integer editProduct(Product product) throws Exception;

    public abstract Integer deleteProduct(String productIds) throws Exception;
}
