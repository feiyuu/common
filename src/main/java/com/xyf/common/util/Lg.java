package com.xyf.common.util;

import io.reactivex.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class Lg {

    private static final Map<String, Logger> LOGGER_MAP = new HashMap<>();

    private static Logger getLogger(@Nonnull String tag) {
        if (!LOGGER_MAP.containsKey(tag)) {
            LOGGER_MAP.put(tag, LoggerFactory.getLogger(tag));
        }

        return LOGGER_MAP.get(tag);
    }

    private static final String BORDER = "----------------------------------------------------------------------------------------------";

    private static void logMethodHead(@Nonnull TYPE type, @Nonnull String tag) {
        logBorder(type, tag);
        final StackTraceElement element = new Throwable().getStackTrace()[3];
        logLine(type, tag, String.format("%s(%s:%d)", element.getMethodName(), element.getFileName(), element.getLineNumber()));
        logBorder(type, tag);
    }

    private enum TYPE {
        INFO, ERROR
    }

    private static void logMethodTail(@Nonnull TYPE type, @Nonnull String tag) {
        switch (type) {
            case ERROR:
                getLogger(tag).error(BORDER);
                break;
            default:
                getLogger(tag).info(BORDER);
                break;
        }
    }

    public static void i(@Nonnull String tag, @Nonnull Object... messages) {
        log(TYPE.INFO, tag, messages);
    }

    private static void log(@NonNull TYPE type, @Nonnull String tag, @Nonnull Object... objects) {
        logMethodHead(type, tag);
        if (CollectionUtils.isEmpty(objects)) {
            return;
        }

        for (Object obj : objects) {
            for (String s : obj.toString().split("\n")) {
                logLine(type, tag, s);
            }
            if (obj instanceof Throwable) {
                Throwable throwable = (Throwable) obj;
                StackTraceElement[] elements = throwable.getStackTrace();
                for (StackTraceElement element : elements) {
                    logLine(type, tag, element);
                }
            }
        }
        logMethodTail(type, tag);
    }

    private static void logLine(@NonNull TYPE type, @Nonnull String tag, @Nonnull Object message) {
        final String line = "| " + message;
        switch (type) {
            case ERROR:
                getLogger(tag).error(line);
                break;
            default:
                getLogger(tag).info(line);
                break;
        }
    }

    private static void logBorder(@NonNull TYPE type, @Nonnull String tag) {
        switch (type) {
            case ERROR:
                getLogger(tag).error(BORDER);
                break;
            default:
                getLogger(tag).info(BORDER);
                break;
        }
    }

    public static void e(@Nonnull String tag, @Nonnull Object... messages) {
        log(TYPE.ERROR, tag, messages);
    }

}
