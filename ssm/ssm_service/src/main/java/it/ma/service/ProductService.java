package it.ma.service;

import it.ma.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll() throws Exception;

    void save(Product product);
}
