package com.k9.ecommerce.cart;

import com.k9.ecommerce.product.Product;
import com.k9.ecommerce.product.ProductRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class CartService {
    @Inject
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    private CartRepository cartRepository;

    @Inject
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    public void addCart(long productId) {
        if (this.cartRepository.getCarts().containsKey(productId)) {
            int total = this.cartRepository.getCarts().get(productId) + 1;
            this.cartRepository.getCarts().put(productId, total);
        }
        this.cartRepository.getCarts().putIfAbsent(productId, 1);
    }

    public void removeCart(long productId) {
        if (this.cartRepository.getCarts().containsKey(productId)) {
            int total = this.cartRepository.getCarts().get(productId) - 1;
            if (total == 0) {
                this.cartRepository.getCarts().remove(productId);
            } else {
                this.cartRepository.getCarts().put(productId, total);
            }
        }
    }

    public int totalCarts() {
        return this.cartRepository.getCarts().size();
    }

    public String getCartsString() {
        List<Product> products = this.productRepository.getProducts();
        Set<Long> productIds = this.cartRepository.getCarts().keySet();
        StringBuilder sb = new StringBuilder();
        List<Product> carts = products.stream().filter(p -> productIds.stream().anyMatch(i -> i == p.getId()))
                .collect(Collectors.toList());

        for (var product : carts) {
            sb.append(product.toString());
            sb.append(String.format("  Quantity: %d\n", cartRepository.getCarts().get(product.getId())));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Inject
    public CartService() {
    }

    public void checkout() {
        this.cartRepository.getCarts().clear();
    }
}
