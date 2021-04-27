package com.k9.ecommerce.app;

import com.k9.ecommerce.cart.CartService;
import com.k9.ecommerce.menu.MainMenu;
import com.k9.ecommerce.menu.Menu;
import com.k9.ecommerce.product.ProductService;

import javax.inject.Inject;

public class AppStore {

    public AppStore() {
        setMenu(new MainMenu());
    }

    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public CartService getCartService() {
        return cartService;
    }

    private ProductService productService;

    @Inject
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    private CartService cartService;

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void showMenu() {
        this.menu.showMenu(this);
    }

    private Menu menu;


}
