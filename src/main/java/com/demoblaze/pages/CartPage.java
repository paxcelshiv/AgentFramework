package com.demoblaze.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class CartPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CartPage.class);

    // Selectors
    private static final String CART_TABLE = "table#tbodyid";
    private static final String PRODUCT_ROWS = "table#tbodyid tbody tr";
    private static final String DELETE_BUTTON_TEMPLATE = "//tr[contains(.,'{product}')]//a[@onclick='deleteItem(this)']";
    private static final String PLACE_ORDER_BUTTON = "//button[contains(text(),'Place Order')]";
    private static final String TOTAL_PRICE = "h3#totalp";

    public CartPage(Page page) {
        super(page);
    }

    /**
     * Check if cart is displayed
     */
    public boolean isCartDisplayed() {
        logger.info("Checking if cart is displayed");
        return isElementVisible(CART_TABLE);
    }

    /**
     * Get total price from cart
     */
    public String getTotalPrice() {
        logger.info("Getting total price from cart");
        return getText(TOTAL_PRICE);
    }

    /**
     * Get number of items in cart
     */
    public int getNumberOfItemsInCart() {
        logger.info("Getting number of items in cart");
        try {
            List<Locator> rows = page.locator(PRODUCT_ROWS).all();
            logger.info("Number of items in cart: " + rows.size());
            return rows.size();
        } catch (Exception e) {
            logger.error("Error getting number of items: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Delete product from cart by name
     */
    public void deleteProductFromCart(String productName) {
        logger.info("Deleting product from cart: " + productName);
        String deleteButtonSelector = DELETE_BUTTON_TEMPLATE.replace("{product}", productName);
        click(deleteButtonSelector);
        waitForSeconds(1);
    }

    /**
     * Verify product exists in cart
     */
    public boolean isProductInCart(String productName) {
        logger.info("Checking if product is in cart: " + productName);
        String selector = "//tr[contains(.,'" + productName + "')]";
        return isElementVisible(selector);
    }

    /**
     * Click Place Order button
     */
    public void clickPlaceOrderButton() {
        logger.info("Clicking Place Order button");
        click(PLACE_ORDER_BUTTON);
        waitForSeconds(1);
    }

    /**
     * Get product price from cart
     */
    public String getProductPrice(String productName) {
        logger.info("Getting price for product: " + productName);
        String selector = "//tr[contains(.,'" + productName + "')]//td[3]";
        return getText(selector);
    }
}
