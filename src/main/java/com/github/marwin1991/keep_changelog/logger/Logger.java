package com.github.marwin1991.keep_changelog.logger;

import org.apache.maven.plugin.logging.SystemStreamLog;

public class Logger {
    private static final SystemStreamLog logger = new SystemStreamLog();

    public static SystemStreamLog getLogger() {
        return logger;
    }
}
