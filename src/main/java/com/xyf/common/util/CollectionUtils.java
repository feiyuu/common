package com.xyf.common.util;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

public class CollectionUtils {

    public static boolean isEmpty(@Nullable Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(@Nullable Object[] directoryList) {
        return directoryList == null || directoryList.length == 0;
    }
}
