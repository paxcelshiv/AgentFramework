package com.demoblaze.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected static final String BASE_URL = "https://www.demoblaze.com/index.html";
    protected static final int TIMEOUT = 10000;

    @Before
    public void setUp() {
        logger.info("================================");
        logger.info("Setting up test environment");
        logger.info("================================");

        try {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            context = browser.newContext();
            page = context.newPage();
            page.setDefaultTimeout(TIMEOUT);
            logger.info("Browser setup completed successfully");
        } catch (Exception e) {
            logger.error("Error during browser setup: " + e.getMessage());
            throw new RuntimeException("Failed to setup browser", e);
        }
    }

    @After
    public void tearDown() {
        logger.info("================================");
        logger.info("Tearing down test environment");
        logger.info("================================");

        try {
            if (page != null && !page.isClosed()) {
                page.close();
                logger.info("Page closed");
            }

            if (context != null) {
                context.close();
                logger.info("Context closed");
            }

            if (browser != null) {
                browser.close();
                logger.info("Browser closed");
            }

            if (playwright != null) {
                playwright.close();
                logger.info("Playwright closed");
            }
        } catch (Exception e) {
            logger.error("Error during browser teardown: " + e.getMessage());
        }
    }

    /**
     * Navigate to base URL
     */
    protected void navigateToBaseUrl() {
        logger.info("Navigating to: " + BASE_URL);
        page.navigate(BASE_URL);
    }
}
