package com.tianqi.common.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuantianqi
 */
@Data
public class BaseException extends RuntimeException {

    private final String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] stackTrace = super.getStackTrace();
        List<StackTraceElement> elements = new ArrayList<>();
        if (stackTrace != null && stackTrace.length != 0) {
            String[] packages = stackTrace[0].getClassName().split("\\.");
            String pkgName = new StringBuffer(packages[0]).append(".").append(packages[1]).toString();
            if (pkgName.contains("com.tianqi")){
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement.getClassName().contains(pkgName)) {
                        elements.add(stackTraceElement);
                    }
                }
            }
        }
        return elements.toArray(new StackTraceElement[]{});
    }
}
