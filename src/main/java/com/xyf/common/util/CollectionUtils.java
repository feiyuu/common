package com.xyf.common.util;

import javax.annotation.Nullable;
import java.util.List;

public class CollectionUtils {

    public static boolean isEmpty(@Nullable List<?> list) {
        return list == null || list.isEmpty();
}

    public static boolean isEmpty(@Nullable Object[] directoryList) {
        return directoryList == null || directoryList.length == 0;
    }
}
