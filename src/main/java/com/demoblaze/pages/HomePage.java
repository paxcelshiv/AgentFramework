package com.demoblaze.pages;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    // Selectors
    private static final String LOGO = "a.navbar-brand";
    private static final String PHONES_BUTTON = "//a[contains(text(),'Phones')]";
    private static final String LAPTOPS_BUTTON = "//a[contains(text(),'Laptops')]";
    private static final String MONITORS_BUTTON = "//a[contains(text(),'Monitors')]";
    private static final String CART_LINK = "#cartur";
    private static final String HOME_LINK = "//a[contains(text(),'Home')]";

    public HomePage(Page page) {
        super(page);
    }

    /**
     * Validate home page with logo
     */
    public boolean validateHomePage() {
        logger.info("Validating home page");
        return isElementVisible(LOGO);
    }

    /**
     * Validate logo text contains "PRODUCT STORE"
     */
    public boolean validateLogoText() {
        logger.info("Validating logo text");
        String logo = getText(LOGO);
        boolean isValid = logo != null && logo.contains("PRODUCT STORE");
        logger.info("Logo text is: " + logo + ", Valid: " + isValid);
        return isValid;
    }

    /**
     * Click on Phones button
     */
    public void clickPhonesButton() {
        logger.info("Clicking on Phones button");
        click(PHONES_BUTTON);
        waitForSeconds(1);
    }

    /**
     * Click on Laptops button
     */
    public void clickLaptopsButton() {
        logger.info("Clicking on Laptops button");
        click(LAPTOPS_BUTTON);
        waitForSeconds(1);
    }

    /**
     * Click on Monitors button
     */
    public void clickMonitorsButton() {
        logger.info("Clicking on Monitors button");
        click(MONITORS_BUTTON);
        waitForSeconds(1);
    }

    /**
     * Click on Cart link
     */
    public void clickCartLink() {
        logger.info("Clicking on Cart link");
        click(CART_LINK);
        waitForSeconds(1);
    }

    /**
     * Click on Home link
     */
    public void clickHomeLink() {
        logger.info("Clicking on Home link");
        click(HOME_LINK);
        waitForSeconds(1);
    }

    /**
     * Check if product exists with name
     */
    public boolean checkProductExists(String productName) {
        logger.info("Checking if product exists: " + productName);
        String selector = "//a[contains(text(),'" + productName + "')]";
        return isElementVisible(selector);
    }

    /**
     * Click on product by name
     */
    public void clickProduct(String productName) {
        logger.info("Clicking on product: " + productName);
        String selector = "//a[contains(text(),'" + productName + "')]";
        click(selector);
        waitForSeconds(1);
    }
}
