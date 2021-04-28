package com.k9.ecommerce.product;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ProductService {
    @Inject
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    private ProductRepository productRepository;

    public void addProduct(Product product) {
        if (product.getId() == 0) {
            long maxProductId = productRepository.getProducts().stream().mapToLong(Product::getId).max().orElse(0);
            product.setId(maxProductId + 1);
            productRepository.addProduct(product);
        }
    }

    public void removeProduct(long productId) {
        Optional<Product> productOptional = productRepository.getProducts().stream().filter(p -> p.getId() == productId).findFirst();
        productOptional.ifPresent(product -> productRepository.removeProduct(product));
    }

    public List<Product> getProducts() {
        return this.productRepository.getProducts();
    }

    public Product getProduct(long productId) {
        return this.productRepository.getProducts().stream().filter(p -> p.getId() == productId).findFirst().orElse(null);
    }

    public String getProductsString() {
        StringBuilder sb = new StringBuilder();
        for (var product : productRepository.getProducts()) {
            sb.append(product.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Inject
    public ProductService() {
    }
}
