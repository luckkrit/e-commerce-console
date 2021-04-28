package com.k9.ecommerce.module;

import com.k9.ecommerce.product.ProductRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ProductRepositoryModule {
    static ProductRepository productRepository;

    @Singleton
    @Provides
    ProductRepository providesProductRepository() {
        if (productRepository == null) {
            productRepository = new ProductRepository();
        }
        return productRepository;
    }
}
