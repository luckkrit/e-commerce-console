package com.k9.ecommerce.module;

import com.k9.ecommerce.product.ProductRepository;
import com.k9.ecommerce.product.ProductService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ProductServiceModule {

    static ProductService productService;

    @Provides
    @Singleton
    ProductService providesProductService(ProductRepository productRepository) {
        if (productService == null) {
            productService = new ProductService();
            productService.setProductRepository(productRepository);
        }
        return productService;
    }

}
