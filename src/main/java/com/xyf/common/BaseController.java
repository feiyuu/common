package com.xyf.common;

import com.xyf.common.annotation.UiThread;
import io.reactivex.disposables.Disposable;
import javafx.fxml.Initializable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController implements Initializable {

    @UiThread
    public void onStop() {
        clearDisposable();
    }

    @UiThread
    protected void addDisposable(@Nonnull String tag, @Nonnull Disposable disposable) {
        disposableMap.put(tag, disposable);
    }

    @UiThread
    protected void removeDisposable(@Nonnull String tag) {
        if (disposableMap.containsKey(tag)) {
            disposableMap.get(tag).dispose();
        }
        disposableMap.remove(tag);
    }

    @UiThread
    private void clearDisposable() {
        for (Disposable disposable : disposableMap.values()) {
            disposable.dispose();
        }
        disposableMap.clear();
    }

    private final Map<String, Disposable> disposableMap = new HashMap<>();

}
