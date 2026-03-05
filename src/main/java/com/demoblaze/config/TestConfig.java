package com.demoblaze.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestConfig {

    private static final Logger logger = LogManager.getLogger(TestConfig.class);

    // URLs
    public static final String DEMOBLAZE_URL = "https://www.demoblaze.com/index.html";
    public static final String STORE_URL = "https://www.demoblaze.com/";

    // Timeouts (in milliseconds)
    public static final int PAGE_LOAD_TIMEOUT = 10000;
    public static final int ELEMENT_WAIT_TIMEOUT = 5000;
    public static final int POPUP_WAIT_TIMEOUT = 3000;

    // Test Data
    public static class TestData {
        public static final String CUSTOMER_NAME = "ARSALAN";
        public static final String CUSTOMER_COUNTRY = "Khan";
        public static final String CUSTOMER_CITY = "testing";
        public static final String CUSTOMER_CREDIT_CARD = "";
        public static final String CUSTOMER_MONTH = "7";
        public static final String CUSTOMER_YEAR = "2025";
    }

    // Product Names
    public static class ProductNames {
        public static final String SAMSUNG_GALAXY_S6 = "Samsung galaxy s6";
        public static final String SONY_VAIO_I5 = "Sony vaio i5";
        public static final String DELL_I5 = "Dell i5";
        public static final String DELL_I7_8GB = "Dell i7 8gb";
    }

    // Category Names
    public static class CategoryNames {
        public static final String PHONES = "Phones";
        public static final String LAPTOPS = "Laptops";
        public static final String MONITORS = "Monitors";
    }

    // Browser Settings
    public static class BrowserSettings {
        public static final boolean HEADED_MODE = true; // Change to false for headless mode
        public static final boolean SLOW_MOTION = false;
        public static final int SLOW_MOTION_DELAY = 0; // ms, only if SLOW_MOTION is true
    }

    /**
     * Log all configuration settings
     */
    public static void logConfigurationSettings() {
        logger.info("================== TEST CONFIGURATION ==================");
        logger.info("Demoblaze URL: " + DEMOBLAZE_URL);
        logger.info("Page Load Timeout: " + PAGE_LOAD_TIMEOUT + " ms");
        logger.info("Element Wait Timeout: " + ELEMENT_WAIT_TIMEOUT + " ms");
        logger.info("Popup Wait Timeout: " + POPUP_WAIT_TIMEOUT + " ms");
        logger.info("Headed Mode: " + BrowserSettings.HEADED_MODE);
        logger.info("=========================================================");
    }
}
