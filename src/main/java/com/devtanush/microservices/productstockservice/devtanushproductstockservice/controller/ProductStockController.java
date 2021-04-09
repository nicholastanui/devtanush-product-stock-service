package com.devtanush.microservices.productstockservice.devtanushproductstockservice.controller;

import com.devtanush.microservices.productstockservice.devtanushproductstockservice.beans.ProductStockBean;
import com.devtanush.microservices.productstockservice.devtanushproductstockservice.entity.ProductStock;
import com.devtanush.microservices.productstockservice.devtanushproductstockservice.entity.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ProductStockController {

    @Autowired
    ProductStockRepository repository;
    @Autowired
    Environment environment;

    @GetMapping("/check-product-stock/productName/{productName}/productAvailability/{productAvailability}")
    public ProductStockBean checkProductStock(@PathVariable String productName, @PathVariable String productAvailability) {
        ProductStock productStock = repository.findByProductNameAndProductAvailability(productName, productAvailability);

        return new ProductStockBean(
                productStock.getId(),
                productStock.getProductName(),
                productStock.getProductPrice(),
                productStock.getProductAvailability(),
                productStock.getDiscountOffer(),
                Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port")))
        );
    }
}
