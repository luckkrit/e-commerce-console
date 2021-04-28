package com.k9.ecommerce.cart;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CheckoutService {
    private final File savedir = new File(new File(System.getProperty("user.home")), "Checkout");

    public void saveToFile(String checkout) throws IOException {
        if (!savedir.exists()) {
            if (!savedir.mkdir()) {
                throw new IOException("Cannot create folder");
            }
        }
        FileWriter writer = new FileWriter(new File(savedir, "invoice.txt"));
        writer.write(checkout);
        writer.flush();
        writer.close();
    }

    @Inject
    public CheckoutService() {
    }
}
