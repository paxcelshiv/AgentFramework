package com.demoblaze.utils;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOTS_DIR = "screenshots";

    static {
        try {
            Path screenshotsPath = Paths.get(SCREENSHOTS_DIR);
            if (!Files.exists(screenshotsPath)) {
                Files.createDirectories(screenshotsPath);
                logger.info("Screenshots directory created: " + SCREENSHOTS_DIR);
            }
        } catch (Exception e) {
            logger.error("Error creating screenshots directory: " + e.getMessage());
        }
    }

    /**
     * Take screenshot with auto-generated timestamp
     */
    public static void takeScreenshot(Page page, String testName) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
            String filename = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOTS_DIR + "/" + filename;

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
            logger.info("Screenshot saved: " + filePath);
        } catch (Exception e) {
            logger.error("Error taking screenshot: " + e.getMessage());
        }
    }

    /**
     * Take screenshot with specific name
     */
    public static void takeScreenshot(Page page, String testName, String screenName) {
        try {
            String filename = testName + "_" + screenName + ".png";
            String filePath = SCREENSHOTS_DIR + "/" + filename;

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
            logger.info("Screenshot saved: " + filePath);
        } catch (Exception e) {
            logger.error("Error taking screenshot: " + e.getMessage());
        }
    }
}
