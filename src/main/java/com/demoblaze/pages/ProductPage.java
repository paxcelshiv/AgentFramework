package com.demoblaze.pages;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    // Selectors
    private static final String PRODUCT_NAME = "h2.name";
    private static final String PRODUCT_PRICE = ".price-container";
    private static final String PRODUCT_DESCRIPTION = "#more-information";
    private static final String ADD_TO_CART_BUTTON = "//a[contains(text(),'Add to cart')]";

    public ProductPage(Page page) {
        super(page);
    }

    /**
     * Get product name
     */
    public String getProductName() {
        logger.info("Getting product name");
        return getText(PRODUCT_NAME);
    }

    /**
     * Get product price
     */
    public String getProductPrice() {
        logger.info("Getting product price");
        return getText(PRODUCT_PRICE);
    }

    /**
     * Get product description
     */
    public String getProductDescription() {
        logger.info("Getting product description");
        return getText(PRODUCT_DESCRIPTION);
    }

    /**
     * Click Add to cart button
     */
    public void clickAddToCartButton() {
        logger.info("Clicking Add to cart button");
        handleAlert();
        click(ADD_TO_CART_BUTTON);
        waitForSeconds(2);
    }

    /**
     * Verify product is displayed
     */
    public boolean isProductDisplayed() {
        logger.info("Verifying product is displayed");
        return isElementVisible(PRODUCT_NAME) && isElementVisible(ADD_TO_CART_BUTTON);
    }

    /**
     * Verify Add to cart button exists
     */
    public boolean isAddToCartButtonVisible() {
        logger.info("Checking if Add to cart button is visible");
        return isElementVisible(ADD_TO_CART_BUTTON);
    }
}
