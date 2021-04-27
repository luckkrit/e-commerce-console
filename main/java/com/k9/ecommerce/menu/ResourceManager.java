package com.k9.ecommerce.menu;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceManager {
    private final String RESOURCE_PATH = "com/k9/ecommerce/menu/";

    @Inject
    public ResourceManager() {

    }

    public String getStringFromFile(String fileName) {
        InputStream body = getFileFromResourceAsStream(RESOURCE_PATH + fileName);
        return getInputStreamAsString(body);
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    private String getInputStreamAsString(InputStream is) {
        StringBuilder builder = new StringBuilder();
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            builder.deleteCharAt(builder.length() - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
