package com.xyf.common;

import com.xyf.common.annotation.UiThread;
import com.xyf.common.util.RxLifeCircle;
import javafx.fxml.Initializable;

public abstract class BaseController extends RxLifeCircle implements Initializable {

    @UiThread
    public void onStop() {
        clearDisposable();
    }

}
