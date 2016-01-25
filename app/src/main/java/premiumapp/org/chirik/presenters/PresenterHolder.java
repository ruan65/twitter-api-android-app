package premiumapp.org.chirik.presenters;

import java.util.HashMap;
import java.util.Map;

public class PresenterHolder {

    static volatile PresenterHolder singleton = null;

    private final Map<Class, Presenter> presenterMap;

    public static PresenterHolder getInstance() {

        if (singleton == null) {
            synchronized (PresenterHolder.class) {
                if (singleton == null) {
                    singleton = new PresenterHolder();
                }
            }
        }
        return singleton;
    }

    private PresenterHolder() {
        presenterMap = new HashMap<>();
    }

    public void putPresenter(Class c, Presenter presenter) {
        presenterMap.put(c, presenter);
    }

    public <T extends Presenter> T getPresenter(Class<T> tClass) {
        return (T) presenterMap.get(tClass);
    }

    public void remove(Class c) {
        presenterMap.remove(c);
    }
}
