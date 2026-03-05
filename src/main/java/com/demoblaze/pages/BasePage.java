package com.demoblaze.pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.MouseButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    protected Page page;
    protected Browser browser;
    protected BrowserContext context;

    public BasePage(Page page) {
        this.page = page;
    }

    /**
     * Navigate to a specific URL
     */
    public void navigate(String url) {
        logger.info("Navigating to: " + url);
        page.navigate(url);
    }

    /**
     * Wait for element to be visible
     */
    public void waitForElementVisible(String selector, int timeoutMs) {
        logger.info("Waiting for element: " + selector);
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeoutMs));
    }

    /**
     * Click on element
     */
    public void click(String selector) {
        logger.info("Clicking on element: " + selector);
        page.click(selector);
    }

    /**
     * Fill text in input field
     */
    public void fill(String selector, String text) {
        logger.info("Filling text in element: " + selector + " with value: " + text);
        page.fill(selector, text);
    }

    /**
     * Get text from element
     */
    public String getText(String selector) {
        logger.info("Getting text from element: " + selector);
        return page.textContent(selector);
    }

    /**
     * Check if element is visible
     */
    public boolean isElementVisible(String selector) {
        logger.info("Checking if element is visible: " + selector);
        try {
            Locator locator = page.locator(selector);
            return locator.isVisible();
        } catch (Exception e) {
            logger.error("Element not visible: " + selector);
            return false;
        }
    }

    /**
     * Wait for element and click
     */
    public void waitAndClick(String selector, int timeoutMs) {
        logger.info("Waiting and clicking on element: " + selector);
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeoutMs));
        click(selector);
    }

    /**
     * Accept alert/popup
     */
    public void acceptAlert() {
        logger.info("Accepting alert popup");
        page.onceDialog(dialog -> {
            logger.info("Dialog type: " + dialog.type() + " Message: " + dialog.message());
            dialog.accept();
        });
    }

    /**
     * Wait for navigation to complete
     */
    public void waitForNavigation() {
        logger.info("Waiting for navigation");
        try {
            page.waitForNavigation(() -> {});
        } catch (Exception e) {
            logger.warn("Navigation wait timeout or already navigated: " + e.getMessage());
        }
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        String url = page.url();
        logger.info("Current URL: " + url);
        return url;
    }

    /**
     * Switch to alert context
     */
    public void handleAlert() {
        logger.info("Setting up alert handler");
        page.onDialog(dialog -> {
            logger.info("Alert message: " + dialog.message());
            dialog.accept();
        });
    }

    /**
     * Take screenshot
     */
    public void takeScreenshot(String fileName) {
        logger.info("Taking screenshot: " + fileName);
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("screenshots/" + fileName + ".png")));
    }

    /**
     * Wait for specific time
     */
    public void waitForSeconds(int seconds) {
        logger.info("Waiting for " + seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Wait interrupted: " + e.getMessage());
        }
    }

    /**
     * Get attribute value
     */
    public String getAttribute(String selector, String attribute) {
        logger.info("Getting attribute: " + attribute + " from element: " + selector);
        return page.getAttribute(selector, attribute);
    }

    /**
     * Check if element exists
     */
    public boolean elementExists(String selector) {
        logger.info("Checking if element exists: " + selector);
        try {
            page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(2000));
            return true;
        } catch (Exception e) {
            logger.info("Element does not exist: " + selector);
            return false;
        }
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        String title = page.title();
        logger.info("Page title: " + title);
        return title;
    }

    /**
     * Scroll to element
     */
    public void scrollToElement(String selector) {
        logger.info("Scrolling to element: " + selector);
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Double click on element
     */
    public void doubleClick(String selector) {
        logger.info("Double clicking on element: " + selector);
        page.dblclick(selector);
    }

    /**
     * Right click on element
     */
    public void rightClick(String selector) {
        logger.info("Right clicking on element: " + selector);
        page.click(selector, new Page.ClickOptions().setButton(MouseButton.RIGHT));
    }

    /**
     * Close page
     */
    public void closePage() {
        logger.info("Closing page");
        page.close();
    }
}
