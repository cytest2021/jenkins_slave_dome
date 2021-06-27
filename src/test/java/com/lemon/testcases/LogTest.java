package com.lemon.testcases;

import org.apache.log4j.Logger;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-23 14:00
 * @Desc：
 **/
public class LogTest {
    private static Logger logger = Logger.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.debug("debug级别的日志");
        logger.info("info级别的日志");
        logger.warn("warn级别的日志");
        logger.error("error级别的日志");
    }
}
