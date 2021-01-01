package cn.itcast.service.impl;

import itcast.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.dao.IProductDao;
import cn.itcast.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();
    }

    @Override
    public Integer saveProduct(Product product) throws Exception {
        return iProductDao.saveProduct(product);
    }

    @Override
    public Product findById(Integer id) throws Exception {
        return iProductDao.findById(id);
    }

    @Override
    public Integer editProduct(Product product) throws Exception {
        return iProductDao.editProduct(product);
    }

    @Override
    public Integer deleteProduct(String productIds) throws Exception {
        return iProductDao.deleteProduct(productIds);
    }
}
