package rui.bean;

import dagger.Component;
import rui.todd.MainActivity;

@Component
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
