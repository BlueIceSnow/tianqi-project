package com.tianqi.common.exception;

import com.tianqi.common.constant.SystemConstant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuantianqi
 */
@Data
public class BaseException extends RuntimeException {

    private final String message;

    public BaseException(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        final StackTraceElement[] stackTrace = super.getStackTrace();
        final List<StackTraceElement> elements = new ArrayList<>();
        if (stackTrace != null && stackTrace.length != 0) {
            final String[] packages = stackTrace[0].getClassName().split("\\.");
            final String pkgName = new StringBuffer(packages[0]).append(".").append(packages[1])
                    .toString();
            if (pkgName.contains(SystemConstant.SYS_PKG_NAME)) {
                for (final StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement.getClassName().contains(pkgName)) {
                        elements.add(stackTraceElement);
                    }
                }
            }
        }
        return elements.toArray(new StackTraceElement[] {});
    }
}
