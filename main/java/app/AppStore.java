package app;

import repository.ProductRepository;

public final class AppStore {
    // static variable single_instance of type Singleton
    private static AppStore instance = null;

    private final ProductRepository productRepository;

    // private constructor restricted to this class itself
    private AppStore() {
        productRepository = new ProductRepository();
    }

    // static method to create instance of Singleton class
    public static AppStore getInstance() {
        if (instance == null)
            instance = new AppStore();

        return instance;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
