package com.xyf.common.util;

import io.reactivex.Observable;
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

    private static final int LOG_LENGTH = 100;

    private static void logMethodHead(@Nonnull TYPE type, @Nonnull String tag) {
        final StackTraceElement element = new Throwable().getStackTrace()[2];

        StringBuilder builder = new StringBuilder();
        builder.append("-------------");
        builder.append(String.format("%s(%s:%d)", element.getMethodName(), element.getFileName(), element.getLineNumber()));
        final int length = builder.length();
        for (int i = 0; i < LOG_LENGTH - length; i++) {
            builder.append("-");
        }

        switch (type) {
            case ERROR:
                getLogger(tag).error(builder.toString());
                break;
            default:
                getLogger(tag).info(builder.toString());
                break;
        }
    }

    public static String getMethodTag() {
        final StackTraceElement element = new Throwable().getStackTrace()[1];
        return String.format("%s(%d)", element.getMethodName(), element.getLineNumber());
    }

    private enum TYPE {
        INFO, ERROR
    }

    private static void logMethodTail(@Nonnull TYPE type, @Nonnull String tag) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < LOG_LENGTH; i++) {
            builder.append("-");
        }
        switch (type) {
            case ERROR:
                getLogger(tag).error(builder.toString());
                break;
            default:
                getLogger(tag).info(builder.toString());
                break;
        }
    }

    public static void i(@Nonnull String tag, @Nonnull Object... messages) {
        logMethodHead(TYPE.INFO, tag);
        for (Object message : messages) {
            final String string = message.toString();
            for (String s : string.split("\n")) {
                getLogger(tag).info("| " + s);
            }
            if (message instanceof Throwable) {
                Throwable throwable = (Throwable) message;
                StackTraceElement[] elements = throwable.getStackTrace();
                for (StackTraceElement element : elements) {
                    getLogger(tag).info("| " + element);
                }
            }
        }
        logMethodTail(TYPE.INFO, tag);
    }

    private static void log(@NonNull TYPE type, @Nonnull String tag, @Nonnull Object... messages) {
        logMethodHead(type, tag);
        for (Object message : messages) {
            for (String s : message.toString().split("\n")) {
                logLine(type, tag, s);
            }
            if (message instanceof Throwable) {
                Throwable throwable = (Throwable) message;
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

    public static void e(@Nonnull String tag, @Nonnull Object... messages) {
        logMethodHead(TYPE.ERROR, tag);
        for (Object message : messages) {
            final String string = message.toString();
            for (String s : string.split("\n")) {
                getLogger(tag).error("| " + s);
            }
            if (message instanceof Throwable) {
                Throwable throwable = (Throwable) message;
                StackTraceElement[] elements = throwable.getStackTrace();
                for (StackTraceElement element : elements) {
                    getLogger(tag).info("| " + element);
                }
            }
        }
        logMethodTail(TYPE.ERROR, tag);
    }

}
