package component;

import app.AppStore;
import dagger.Component;

@Component
public interface MenuContextComponent {
    void inject(AppStore appStore);

}
