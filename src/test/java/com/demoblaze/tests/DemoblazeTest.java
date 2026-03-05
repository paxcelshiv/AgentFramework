package com.demoblaze.tests;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.OrderPage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class DemoblazeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(DemoblazeTest.class);

    /**
     * Scenario 1: Open the demoblaze site and Customer navigation through product categories
     * - Open website
     * - Validate home page with logo "PRODUCT STORE"
     * - Click on Phones button
     * - Validate phones tab with "Samsung galaxy s6" name
     * - Click on Laptops button
     * - Click on Monitors button
     */
    @Test
    public void testScenario1_ProductCategoryNavigation() {
        logger.info("=== SCENARIO 1: Product Category Navigation ===");

        try {
            // Step 1: Navigate to website
            navigateToBaseUrl();
            logger.info("Opened demoblaze website");

            HomePage homePage = new HomePage(page);
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "01_HomePage");

            // Step 2: Validate home page with logo
            assertTrue("Home page logo should be visible", homePage.validateHomePage());
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "02_HomePageValidated");

            // Step 3: Validate logo text contains "PRODUCT STORE"
            assertTrue("Logo should contain 'PRODUCT STORE'", homePage.validateLogoText());
            logger.info("✓ Logo validated as PRODUCT STORE");

            // Step 4: Click on Phones button
            homePage.clickPhonesButton();
            logger.info("Clicked on Phones button");
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "03_PhonesCategory");

            // Step 5: Validate Samsung galaxy s6 exists
            assertTrue("Samsung galaxy s6 should be visible in Phones", 
                    homePage.checkProductExists("Samsung galaxy s6"));
            logger.info("✓ Samsung galaxy s6 found in Phones category");
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "04_SamsungGalaxyS6Found");

            // Step 6: Click on Laptops button
            homePage.clickLaptopsButton();
            logger.info("Clicked on Laptops button");
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "05_LaptopsCategory");

            // Step 7: Click on Monitors button
            homePage.clickMonitorsButton();
            logger.info("Clicked on Monitors button");
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "06_MonitorsCategory");

            logger.info("✓ SCENARIO 1 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 1: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 1: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario1", "ERROR");
            throw new RuntimeException("Scenario 1 failed", e);
        }
    }

    /**
     * Scenario 2: Navigate to Laptop then add Sony vaio i5 and add to cart with popup confirmation
     */
    @Test
    public void testScenario2_AddSonyVaioToCart() {
        logger.info("=== SCENARIO 2: Add Sony Vaio i5 to Cart ===");

        try {
            // Step 1: Navigate to home page
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            ScreenshotUtil.takeScreenshot(page, "Scenario2", "01_HomePage");

            // Step 2: Click on Laptops button
            homePage.clickLaptopsButton();
            logger.info("Clicked on Laptops button");
            ScreenshotUtil.takeScreenshot(page, "Scenario2", "02_LaptopsCategory");

            // Step 3: Click on Sony vaio i5 link
            homePage.clickProduct("Sony vaio i5");
            logger.info("Clicked on Sony vaio i5");
            ScreenshotUtil.takeScreenshot(page, "Scenario2", "03_SonyVaioProductPage");

            // Step 4: Click Add to cart button
            ProductPage productPage = new ProductPage(page);
            assertTrue("Product should be displayed", productPage.isProductDisplayed());
            productPage.clickAddToCartButton();
            logger.info("✓ Clicked Add to cart for Sony vaio i5");
            ScreenshotUtil.takeScreenshot(page, "Scenario2", "04_AddedToCart");

            // Step 5: Accept popup confirmation
            logger.info("✓ Popup confirmation accepted");
            ScreenshotUtil.takeScreenshot(page, "Scenario2", "05_PopupAccepted");

            logger.info("✓ SCENARIO 2 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 2: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario2", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 2: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario2", "ERROR");
            throw new RuntimeException("Scenario 2 failed", e);
        }
    }

    /**
     * Scenario 3: Navigate to Laptop then add Dell i7 8gb and add to cart with popup confirmation
     */
    @Test
    public void testScenario3_AddDellI7ToCart() {
        logger.info("=== SCENARIO 3: Add Dell i7 8gb to Cart ===");

        try {
            // Step 1: Navigate to home page
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            ScreenshotUtil.takeScreenshot(page, "Scenario3", "01_HomePage");

            // Step 2: Click on Laptops button
            homePage.clickLaptopsButton();
            logger.info("Clicked on Laptops button");
            ScreenshotUtil.takeScreenshot(page, "Scenario3", "02_LaptopsCategory");

            // Step 3: Click on Dell i7 8gb link
            homePage.clickProduct("Dell i5");
            logger.info("Clicked on Dell i5");
            ScreenshotUtil.takeScreenshot(page, "Scenario3", "03_DellI5ProductPage");

            // Step 4: Click Add to cart button
            ProductPage productPage = new ProductPage(page);
            assertTrue("Product should be displayed", productPage.isProductDisplayed());
            productPage.clickAddToCartButton();
            logger.info("✓ Clicked Add to cart for Dell i5");
            ScreenshotUtil.takeScreenshot(page, "Scenario3", "04_AddedToCart");

            logger.info("✓ SCENARIO 3 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 3: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario3", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 3: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario3", "ERROR");
            throw new RuntimeException("Scenario 3 failed", e);
        }
    }

    /**
     * Scenario 4: Navigate to Cart and Delete Dell i5 from cart
     */
    @Test
    public void testScenario4_DeleteProductFromCart() {
        logger.info("=== SCENARIO 4: Delete Product from Cart ===");

        try {
            // Step 0: Add products to cart first
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            homePage.clickLaptopsButton();
            homePage.clickProduct("Sony vaio i5");
            ProductPage productPage = new ProductPage(page);
            productPage.clickAddToCartButton();
            
            // Add second product
            homePage.clickLaptopsButton();
            homePage.clickProduct("Dell i5");
            productPage.clickAddToCartButton();
            
            logger.info("Products added to cart");
            ScreenshotUtil.takeScreenshot(page, "Scenario4", "00_ProductsAdded");

            // Step 1: Click on Cart link
            homePage.clickCartLink();
            logger.info("Clicked on Cart link");
            ScreenshotUtil.takeScreenshot(page, "Scenario4", "01_CartPage");

            // Step 2: Verify cart is displayed
            CartPage cartPage = new CartPage(page);
            assertTrue("Cart should be displayed", cartPage.isCartDisplayed());
            logger.info("✓ Cart page displayed");

            // Step 3: Get initial number of items
            int initialItemCount = cartPage.getNumberOfItemsInCart();
            logger.info("Initial items in cart: " + initialItemCount);

            // Step 4: Delete Dell i5 from cart
            cartPage.deleteProductFromCart("Dell i5");
            logger.info("✓ Deleted Dell i5 from cart");
            ScreenshotUtil.takeScreenshot(page, "Scenario4", "02_ProductDeleted");

            // Step 5: Verify product is removed
            page.waitForTimeout(1000);
            assertFalse("Dell i5 should not be in cart", cartPage.isProductInCart("Dell i5"));
            logger.info("✓ Dell i5 successfully removed from cart");

            logger.info("✓ SCENARIO 4 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 4: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario4", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 4: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario4", "ERROR");
            throw new RuntimeException("Scenario 4 failed", e);
        }
    }

    /**
     * Scenario 5: Click on Place Order
     */
    @Test
    public void testScenario5_ClickPlaceOrder() {
        logger.info("=== SCENARIO 5: Click Place Order ===");

        try {
            // Step 0: Add products to cart
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            homePage.clickLaptopsButton();
            homePage.clickProduct("Sony vaio i5");
            ProductPage productPage = new ProductPage(page);
            productPage.clickAddToCartButton();
            
            homePage.clickLaptopsButton();
            homePage.clickProduct("Dell i5");
            productPage.clickAddToCartButton();
            
            logger.info("Products added to cart");

            // Step 1: Navigate to cart
            homePage.clickCartLink();
            logger.info("Navigated to Cart");
            ScreenshotUtil.takeScreenshot(page, "Scenario5", "01_CartPage");

            // Step 2: Click Place Order button
            CartPage cartPage = new CartPage(page);
            String totalPrice = cartPage.getTotalPrice();
            logger.info("Total Price: " + totalPrice);
            
            cartPage.clickPlaceOrderButton();
            logger.info("✓ Clicked Place Order button");
            ScreenshotUtil.takeScreenshot(page, "Scenario5", "02_PlaceOrderClicked");

            // Step 3: Verify order form is displayed
            page.waitForTimeout(1000);
            OrderPage orderPage = new OrderPage(page);
            assertTrue("Order form should be displayed", orderPage.isOrderFormDisplayed());
            logger.info("✓ Order form displayed successfully");

            logger.info("✓ SCENARIO 5 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 5: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario5", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 5: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario5", "ERROR");
            throw new RuntimeException("Scenario 5 failed", e);
        }
    }

    /**
     * Scenario 6: Fill in all web form fields and place order
     */
    @Test
    public void testScenario6_FillOrderFormAndPurchase() {
        logger.info("=== SCENARIO 6: Fill Order Form and Purchase ===");

        try {
            // Step 0: Add products to cart
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            homePage.clickLaptopsButton();
            homePage.clickProduct("Sony vaio i5");
            ProductPage productPage = new ProductPage(page);
            productPage.clickAddToCartButton();
            
            homePage.clickLaptopsButton();
            homePage.clickProduct("Dell i5");
            productPage.clickAddToCartButton();
            
            logger.info("Products added to cart");

            // Step 1: Navigate to cart and click Place Order
            homePage.clickCartLink();
            CartPage cartPage = new CartPage(page);
            cartPage.clickPlaceOrderButton();
            page.waitForTimeout(1000);
            ScreenshotUtil.takeScreenshot(page, "Scenario6", "01_OrderForm");

            // Step 2: Fill order form with provided details
            OrderPage orderPage = new OrderPage(page);
            assertTrue("Order form should be displayed", orderPage.isOrderFormDisplayed());

            String name = "ARSALAN";
            String country = "Khan";
            String city = "testing";
            String creditCard = "";
            String month = "7";
            String year = "2025";

            logger.info("Filling order form with following details:");
            logger.info("Name: " + name);
            logger.info("Country: " + country);
            logger.info("City: " + city);
            logger.info("Month: " + month);
            logger.info("Year: " + year);

            orderPage.fillOrderForm(name, country, city, creditCard, month, year);
            logger.info("✓ Order form filled successfully");
            ScreenshotUtil.takeScreenshot(page, "Scenario6", "02_FormFilled");

            // Step 3: Click Purchase button
            orderPage.clickPurchaseButton();
            logger.info("✓ Clicked Purchase button");
            page.waitForTimeout(2000);
            ScreenshotUtil.takeScreenshot(page, "Scenario6", "03_PurchaseClicked");

            logger.info("✓ SCENARIO 6 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 6: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario6", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 6: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario6", "ERROR");
            throw new RuntimeException("Scenario 6 failed", e);
        }
    }

    /**
     * Scenario 7: Capture and log purchase ID and Amount
     */
    @Test
    public void testScenario7_CapturePurchaseDetails() {
        logger.info("=== SCENARIO 7: Capture Purchase Details ===");

        try {
            // Step 0: Execute complete purchase
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            homePage.clickLaptopsButton();
            homePage.clickProduct("Sony vaio i5");
            ProductPage productPage = new ProductPage(page);
            productPage.clickAddToCartButton();
            
            homePage.clickLaptopsButton();
            homePage.clickProduct("Dell i5");
            productPage.clickAddToCartButton();

            homePage.clickCartLink();
            CartPage cartPage = new CartPage(page);
            cartPage.clickPlaceOrderButton();
            page.waitForTimeout(1000);

            OrderPage orderPage = new OrderPage(page);
            orderPage.fillOrderForm("ARSALAN", "Khan", "testing", "", "7", "2025");
            orderPage.clickPurchaseButton();
            page.waitForTimeout(2000);

            ScreenshotUtil.takeScreenshot(page, "Scenario7", "01_ConfirmationPage");

            // Step 1: Verify purchase confirmation is displayed
            assertTrue("Purchase confirmation should be displayed", 
                    orderPage.isPurchaseConfirmationDisplayed());
            logger.info("✓ Purchase confirmation displayed");

            // Step 2: Capture purchase ID
            String purchaseId = orderPage.getPurchaseId();
            logger.info("✓ Purchase ID: " + purchaseId);

            // Step 3: Capture purchase amount
            String purchaseAmount = orderPage.getPurchaseAmount();
            logger.info("✓ Purchase Amount: " + purchaseAmount);

            // Step 4: Log complete confirmation info
            String confirmationInfo = orderPage.getOrderConfirmationInfo();
            logger.info("✓ Complete confirmation info:");
            logger.info(confirmationInfo);

            ScreenshotUtil.takeScreenshot(page, "Scenario7", "02_DetailsLogged");

            logger.info("✓ SCENARIO 7 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 7: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario7", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 7: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario7", "ERROR");
            throw new RuntimeException("Scenario 7 failed", e);
        }
    }

    /**
     * Scenario 8: Assert purchase amount equals expected
     */
    @Test
    public void testScenario8_AssertPurchaseDetails() {
        logger.info("=== SCENARIO 8: Assert Purchase Details ===");

        try {
            // Step 0: Execute complete purchase
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            homePage.clickLaptopsButton();
            homePage.clickProduct("Sony vaio i5");
            ProductPage productPage = new ProductPage(page);
            productPage.clickAddToCartButton();
            
            homePage.clickLaptopsButton();
            homePage.clickProduct("Dell i5");
            productPage.clickAddToCartButton();

            homePage.clickCartLink();
            CartPage cartPage = new CartPage(page);
            cartPage.clickPlaceOrderButton();
            page.waitForTimeout(1000);

            OrderPage orderPage = new OrderPage(page);
            orderPage.fillOrderForm("ARSALAN", "Khan", "testing", "", "7", "2025");
            orderPage.clickPurchaseButton();
            page.waitForTimeout(2000);

            // Step 1: Verify confirmation page
            assertTrue("Purchase confirmation should be displayed", 
                    orderPage.isPurchaseConfirmationDisplayed());
            logger.info("✓ Purchase confirmation verified");

            // Step 2: Assert purchase ID is not empty
            String purchaseId = orderPage.getPurchaseId();
            assertNotNull("Purchase ID should not be null", purchaseId);
            assertNotEquals("Purchase ID should not be empty", "", purchaseId);
            logger.info("✓ Purchase ID assertion passed: " + purchaseId);

            // Step 3: Assert purchase amount is not empty
            String purchaseAmount = orderPage.getPurchaseAmount();
            assertNotNull("Purchase amount should not be null", purchaseAmount);
            assertNotEquals("Purchase amount should not be empty", "", purchaseAmount);
            logger.info("✓ Purchase amount assertion passed: " + purchaseAmount);

            // Step 4: Take final screenshot
            ScreenshotUtil.takeScreenshot(page, "Scenario8", "01_AssertionsCompleted");

            logger.info("✓ SCENARIO 8 COMPLETED SUCCESSFULLY");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 8: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario8", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 8: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario8", "ERROR");
            throw new RuntimeException("Scenario 8 failed", e);
        }
    }

    /**
     * Scenario 9: Complete Purchase Flow - End to End Test
     */
    @Test
    public void testScenario9_CompletePurchaseFlow() {
        logger.info("=== SCENARIO 9: Complete Purchase Flow ===");

        try {
            // Complete end-to-end purchase flow
            navigateToBaseUrl();
            HomePage homePage = new HomePage(page);
            
            // Validate home page
            assertTrue("Home page should be displayed", homePage.validateHomePage());
            logger.info("✓ Home page validated");
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "01_HomePage");

            // Browse categories
            homePage.clickPhonesButton();
            assertTrue("Samsung galaxy s6 should be visible", homePage.checkProductExists("Samsung galaxy s6"));
            logger.info("✓ Products browsed");

            // Add Sony Vaio
            homePage.clickLaptopsButton();
            homePage.clickProduct("Sony vaio i5");
            ProductPage productPage = new ProductPage(page);
            productPage.clickAddToCartButton();
            logger.info("✓ Sony Vaio i5 added");
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "02_SonyVaioAdded");

            // Add Dell i5
            homePage.clickLaptopsButton();
            homePage.clickProduct("Dell i5");
            productPage.clickAddToCartButton();
            logger.info("✓ Dell i5 added");
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "03_DellAdded");

            // Manage cart
            homePage.clickCartLink();
            CartPage cartPage = new CartPage(page);
            assertTrue("Cart should show items", cartPage.isCartDisplayed());
            logger.info("✓ Cart displayed");
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "04_CartView");

            // Place order
            cartPage.clickPlaceOrderButton();
            page.waitForTimeout(1000);
            OrderPage orderPage = new OrderPage(page);
            assertTrue("Order form should be displayed", orderPage.isOrderFormDisplayed());
            logger.info("✓ Order form ready");
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "05_OrderForm");

            // Fill and submit
            orderPage.fillOrderForm("ARSALAN", "Khan", "testing", "", "7", "2025");
            orderPage.clickPurchaseButton();
            page.waitForTimeout(2000);
            logger.info("✓ Purchase submitted");
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "06_PurchaseConfirmed");

            // Verify confirmation
            assertTrue("Purchase confirmation should be displayed", 
                    orderPage.isPurchaseConfirmationDisplayed());
            String purchaseId = orderPage.getPurchaseId();
            String purchaseAmount = orderPage.getPurchaseAmount();
            logger.info("✓ Purchase ID: " + purchaseId);
            logger.info("✓ Purchase Amount: " + purchaseAmount);
            assertNotNull("Purchase ID should not be null", purchaseId);
            assertNotNull("Purchase amount should not be null", purchaseAmount);
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "07_ConfirmationDetails");

            logger.info("✓ SCENARIO 9 COMPLETED SUCCESSFULLY - FULL E2E TEST PASSED");

        } catch (AssertionError e) {
            logger.error("Assertion failed in Scenario 9: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "FAILED");
            throw e;
        } catch (Exception e) {
            logger.error("Error in Scenario 9: " + e.getMessage());
            ScreenshotUtil.takeScreenshot(page, "Scenario9", "ERROR");
            throw new RuntimeException("Scenario 9 failed", e);
        }
    }
}
