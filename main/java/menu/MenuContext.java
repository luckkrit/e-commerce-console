package menu;

import lombok.Data;
import menu.state.MenuState;
import menu.state.main.MainMenu;

import java.io.IOException;

@Data
public class MenuContext {
    private MenuState menuState;

    public MenuContext() {
        this.menuState = new MainMenu();
    }

    public void showMenu() {
        this.menuState.show(this);
    }

    public static void main(String[] args) throws IOException {
        MenuContext menu = new MenuContext();
        menu.showMenu();
    }
}
