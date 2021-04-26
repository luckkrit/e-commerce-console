package menu;

import menu.state.MenuState;
import menu.state.main.MainMenu;
import repository.CartRepository;
import repository.ProductRepository;
import service.CheckoutService;

import javax.inject.Inject;

public class MenuContext {
    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    private MenuState menuState;

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Inject
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    @Inject
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    private CartRepository cartRepository;

    @Inject
    public MenuContext() {
        this.menuState = new MainMenu();
    }

    public void showMenu() {
        this.menuState.show(this);
    }

    public service.CheckoutService getCheckoutService() {
        return CheckoutService;
    }

    @Inject
    public void setCheckoutService(service.CheckoutService checkoutService) {
        CheckoutService = checkoutService;
    }

    private CheckoutService CheckoutService;

}
