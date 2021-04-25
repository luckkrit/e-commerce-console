package repository;

import lombok.Data;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ProductRepository {
    private List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        int max = products.stream().mapToInt(Product::getId).max().orElseThrow(NoSuchFieldError::new);
        product.setId(max + 1);
        products.add(product);
    }

    public void removeProduct(int id) {
        Optional<Product> findProduct = products.stream().filter(x -> x.getId() == id).findFirst();
        findProduct.ifPresent(product -> products.remove(product));
    }

    public Product getProduct(int id) {
        Optional<Product> findProduct = products.stream().filter(x -> x.getId() == id).findFirst();
        return findProduct.orElse(null);
    }
}
