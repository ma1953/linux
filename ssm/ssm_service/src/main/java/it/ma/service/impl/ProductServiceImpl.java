package it.ma.service.impl;

import it.ma.dao.ProductDao;
import it.ma.domain.Product;
import it.ma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    @Qualifier("productDao")
    private ProductDao productDao;
    @Override
    public List<Product> findAll() throws Exception {
     return    productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
