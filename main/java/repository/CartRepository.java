package repository;

import lombok.Data;

import javax.inject.Inject;
import java.util.HashMap;

@Data
public class CartRepository {
    private HashMap<Integer, Integer> carts = new HashMap<>();

    @Inject
    public CartRepository() {
    }

    public void addCart(int productId) {
        addCart(productId, 1);
    }

    public void addCart(int productId, int quantity) {
        if (!carts.containsKey(productId))
            carts.put(productId, quantity);
        else {
            int total = carts.get(productId) + quantity;
            carts.put(productId, total);
        }
    }

    public void removeCart(int productId) {
        removeCart(productId, 1);
    }

    public void removeCart(int productId, int quantity) {
        if (!carts.containsKey(productId))
            carts.put(productId, quantity);
        else {
            int total = carts.get(productId) - quantity;
            if (total > 0) {
                carts.put(productId, total);
            } else {
                carts.remove(productId);
            }
        }
    }

    public boolean containsCart(int productId) {
        return carts.containsKey(productId);
    }
}
