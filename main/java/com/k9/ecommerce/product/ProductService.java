package com.k9.ecommerce.product;

import javax.inject.Inject;
import java.util.Optional;

public class ProductService {
    @Inject
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    public void addProduct(Product product) {
        long maxProductId = productRepository.getProducts().stream().mapToLong(Product::getId).max().orElse(1);
        if (product.getId() == 0) {
            product.setId(maxProductId);
        } else {
            product.setId(maxProductId);
        }
        productRepository.addProduct(product);
    }

    public void removeProduct(long productId) {
        Optional<Product> productOptional = productRepository.getProducts().stream().filter(p -> p.getId() == productId).findFirst();
        productOptional.ifPresent(product -> productRepository.removeProduct(product));
    }
}
