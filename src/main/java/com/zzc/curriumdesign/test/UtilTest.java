package com.zzc.curriumdesign.test;

import com.zzc.curriumdesign.utils.MD5Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: UtilTest
 * @Author: zzc
 * @CreateTime: 2020/12/15 9:10
 * @Description: 用于工具类测试
 */

public class UtilTest {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    static final String md = "12345";
    public static void main(String[] args) {
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
        System.out.println("Hello World!");
        System.out.println(MD5Util.md5(md));
    }

}
