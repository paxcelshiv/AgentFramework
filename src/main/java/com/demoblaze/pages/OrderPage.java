package com.demoblaze.pages;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(OrderPage.class);

    // Selectors
    private static final String NAME_FIELD = "#name";
    private static final String COUNTRY_FIELD = "#country";
    private static final String CITY_FIELD = "#city";
    private static final String CREDIT_CARD_FIELD = "#card";
    private static final String MONTH_FIELD = "#month";
    private static final String YEAR_FIELD = "#year";
    private static final String PURCHASE_BUTTON = "//button[contains(text(),'Purchase')]";
    private static final String CLOSE_BUTTON = "//button[contains(text(),'Close')]";
    private static final String MODAL_BODY = ".modal-body";
    private static final String ORDER_CONFIRMATION = "//h2[contains(text(),'Thank you for your purchase!')]";
    private static final String PURCHASE_ID = "//p[1]";
    private static final String PURCHASE_AMOUNT = "//p[2]";

    public OrderPage(Page page) {
        super(page);
    }

    /**
     * Fill Name field
     */
    public void fillNameField(String name) {
        logger.info("Filling name field with: " + name);
        fill(NAME_FIELD, name);
    }

    /**
     * Fill Country field
     */
    public void fillCountryField(String country) {
        logger.info("Filling country field with: " + country);
        fill(COUNTRY_FIELD, country);
    }

    /**
     * Fill City field
     */
    public void fillCityField(String city) {
        logger.info("Filling city field with: " + city);
        fill(CITY_FIELD, city);
    }

    /**
     * Fill Credit Card field
     */
    public void fillCreditCardField(String creditCard) {
        logger.info("Filling credit card field with: " + creditCard);
        fill(CREDIT_CARD_FIELD, creditCard);
    }

    /**
     * Fill Month field
     */
    public void fillMonthField(String month) {
        logger.info("Filling month field with: " + month);
        fill(MONTH_FIELD, month);
    }

    /**
     * Fill Year field
     */
    public void fillYearField(String year) {
        logger.info("Filling year field with: " + year);
        fill(YEAR_FIELD, year);
    }

    /**
     * Fill all order form fields
     */
    public void fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        logger.info("Filling complete order form");
        fillNameField(name);
        fillCountryField(country);
        fillCityField(city);
        fillCreditCardField(creditCard);
        fillMonthField(month);
        fillYearField(year);
    }

    /**
     * Click Purchase button
     */
    public void clickPurchaseButton() {
        logger.info("Clicking Purchase button");
        click(PURCHASE_BUTTON);
        waitForSeconds(2);
    }

    /**
     * Click Close button
     */
    public void clickCloseButton() {
        logger.info("Clicking Close button");
        click(CLOSE_BUTTON);
        waitForSeconds(1);
    }

    /**
     * Verify order form is displayed
     */
    public boolean isOrderFormDisplayed() {
        logger.info("Verifying order form is displayed");
        return isElementVisible(NAME_FIELD) && isElementVisible(PURCHASE_BUTTON);
    }

    /**
     * Verify purchase confirmation
     */
    public boolean isPurchaseConfirmationDisplayed() {
        logger.info("Verifying purchase confirmation is displayed");
        return isElementVisible(ORDER_CONFIRMATION);
    }

    /**
     * Get purchase ID
     */
    public String getPurchaseId() {
        logger.info("Getting purchase ID");
        String purchaseInfo = getText(PURCHASE_ID);
        logger.info("Purchase ID info: " + purchaseInfo);
        // Extract ID from text like "Id: 123456"
        if (purchaseInfo != null && purchaseInfo.contains("Id")) {
            return purchaseInfo.replace("Id: ", "").trim();
        }
        return purchaseInfo;
    }

    /**
     * Get purchase amount
     */
    public String getPurchaseAmount() {
        logger.info("Getting purchase amount");
        String amountInfo = getText(PURCHASE_AMOUNT);
        logger.info("Purchase amount info: " + amountInfo);
        // Extract amount from text like "Amount: $1234.00"
        if (amountInfo != null && amountInfo.contains("Amount")) {
            return amountInfo.replace("Amount: $", "").trim();
        }
        return amountInfo;
    }

    /**
     * Get complete order confirmation info
     */
    public String getOrderConfirmationInfo() {
        logger.info("Getting complete order confirmation information");
        return getText(MODAL_BODY);
    }
}
