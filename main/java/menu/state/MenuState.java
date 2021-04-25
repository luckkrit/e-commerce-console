package menu.state;


import menu.MenuContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class MenuState implements IMenu {
    protected final String BACK = "back";
    protected final String QUIT = "quit";
    protected final String CLEAR = "clear";
    protected Scanner keyboardScanner = new Scanner(System.in);

    @Override
    public void show(MenuContext menuContext) {
    }

    protected String getStringFromFile(String fileName) {
        InputStream body = getFileFromResourceAsStream(fileName);
        return getInputStreamAsString(body);
    }

    protected String getMenuStringFromFile(String fileName) {
        InputStream header = getFileFromResourceAsStream("menu/menu_template.txt");
        InputStream body = getFileFromResourceAsStream(fileName);
        String headerString = getInputStreamAsString(header);
        String bodyString = getInputStreamAsString(body);
        return headerString.replace("{{content}}", bodyString);
    }

    protected void printMenu(String fileName) {
        InputStream header = getFileFromResourceAsStream("menu/menu_template.txt");
        InputStream body = getFileFromResourceAsStream(fileName);
        String headerString = getInputStreamAsString(header);
        String bodyString = getInputStreamAsString(body);
        String menuString = headerString.replace("{{content}}", bodyString);
        System.out.println(menuString);
    }

    protected void clearScreen() {
        System.out.println(System.lineSeparator().repeat(50));
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
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
