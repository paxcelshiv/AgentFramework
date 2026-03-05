# Demoblaze Automation Framework - Playwright Java

A comprehensive test automation framework built with Playwright and Java for testing the Demoblaze e-commerce website. This framework implements the Page Object Model pattern and includes end-to-end test scenarios for product browsing, cart management, and order placement.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Test Scenarios](#test-scenarios)
- [Running Tests](#running-tests)
- [Reports and Logs](#reports-and-logs)
- [Troubleshooting](#troubleshooting)

## Project Overview

This automation framework tests the Demoblaze website (`https://www.demoblaze.com/index.html`) for the following workflow:
1. Browse product categories (Phones, Laptops, Monitors)
2. Add products to shopping cart
3. Manage cart items (add/delete)
4. Place order with customer details
5. Verify purchase confirmation

## Features

✅ **Page Object Model (POM)** - Clean separation of test logic and page interactions
✅ **Comprehensive Logging** - Log4j2 integration with detailed execution logs
✅ **Screenshot Capture** - Automatic screenshots for each test step
✅ **JUnit 4 Integration** - Standard test execution and assertions
✅ **Reusable Base Classes** - BasePage and BaseTest for code reuse
✅ **Error Handling** - Try-catch blocks with detailed error logging
✅ **Configuration Management** - Centralized test configuration
✅ **Multi-scenario Coverage** - 9 complete test scenarios

## Prerequisites

- **Java JDK 11 or higher**
- **Maven 3.6 or higher**
- **Git** (optional, for version control)
- **Windows/Mac/Linux** operating system

### Verify Java Installation

```bash
java -version
```

### Verify Maven Installation

```bash
mvn -version
```

## Installation

### Step 1: Clone or Download the Project

```bash
git clone <repository-url>
cd AgentFramrwork
```

Or extract the project folder directly.

### Step 2: Install Playwright Browsers

Playwright requires browser binaries to be installed. Maven will handle this during the first test run, but you can install them manually:

```bash
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
```

### Step 3: Build the Project

```bash
mvn clean install
```

This command will:
- Download all dependencies from pom.xml
- Compile Java source files
- Install Playwright browsers
- Prepare the project for testing

### Step 4: Verify Installation

```bash
mvn test -Dtest=DemoblazeTest#testScenario1_ProductCategoryNavigation
```

## Project Structure

```
AgentFramrwork/
├── pom.xml                                    # Maven configuration and dependencies
├── README.md                                  # This file
├── screenshots/                               # Screenshot storage directory
├── logs/                                      # Log files directory
│
├── src/
│   ├── main/
│   │   └── java/com/demoblaze/
│   │       ├── pages/                        # Page Object Models
│   │       │   ├── BasePage.java             # Base class for all pages
│   │       │   ├── HomePage.java             # Home page POM
│   │       │   ├── ProductPage.java          # Product page POM
│   │       │   ├── CartPage.java             # Shopping cart page POM
│   │       │   └── OrderPage.java            # Order/checkout page POM
│   │       ├── utils/                        # Utility classes
│   │       │   └── ScreenshotUtil.java       # Screenshot utility
│   │       └── config/                       # Configuration
│   │           └── TestConfig.java           # Test configuration constants
│   │
│   └── test/
│       ├── java/com/demoblaze/tests/
│       │   ├── BaseTest.java                 # Base test class with setup/teardown
│       │   └── DemoblazeTest.java            # Main test class with all scenarios
│       │
│       └── resources/
│           └── log4j2.xml                    # Log4j2 configuration
```

## Configuration

### Test Configuration (TestConfig.java)

Edit `src/main/java/com/demoblaze/config/TestConfig.java` to customize test parameters:

```java
// URLs
public static final String DEMOBLAZE_URL = "https://www.demoblaze.com/index.html";

// Timeouts (in milliseconds)
public static final int PAGE_LOAD_TIMEOUT = 10000;
public static final int ELEMENT_WAIT_TIMEOUT = 5000;

// Test Data
public static class TestData {
    public static final String CUSTOMER_NAME = "ARSALAN";
    public static final String CUSTOMER_COUNTRY = "Khan";
    // ... more properties
}

// Browser Settings
public static class BrowserSettings {
    public static final boolean HEADED_MODE = true; // Change to false for headless
}
```

### Log Configuration (log4j2.xml)

Edit `src/test/resources/log4j2.xml` to customize logging:
- Console output
- File logging (demoblaze-automation.log)
- Error logging (demoblaze-errors.log)
- Rolling file appender

## Test Scenarios

### Scenario 1: Product Category Navigation
**File**: `testScenario1_ProductCategoryNavigation()`

Tests the following:
- Opens Demoblaze website
- Validates home page with "PRODUCT STORE" logo
- Navigates through Phones, Laptops, and Monitors categories
- Verifies Samsung Galaxy S6 appears in Phones category

### Scenario 2: Add Sony Vaio i5 to Cart
**File**: `testScenario2_AddSonyVaioToCart()`

Tests the following:
- Navigates to Laptops category
- Selects Sony Vaio i5 product
- Clicks "Add to cart" button
- Accepts popup confirmation

### Scenario 3: Add Dell i5 to Cart
**File**: `testScenario3_AddDellI7ToCart()`

Tests the following:
- Navigates to Laptops category
- Selects Dell i5 product
- Adds to cart with popup confirmation

### Scenario 4: Delete Product from Cart
**File**: `testScenario4_DeleteProductFromCart()`

Tests the following:
- Adds Sony Vaio i5 and Dell i5 to cart
- Navigates to shopping cart
- Deletes Dell i5 from cart
- Verifies product is removed

### Scenario 5: Click Place Order
**File**: `testScenario5_ClickPlaceOrder()`

Tests the following:
- Adds products to cart
- Navigates to cart
- Clicks "Place Order" button
- Verifies order form is displayed

### Scenario 6: Fill Order Form and Purchase
**File**: `testScenario6_FillOrderFormAndPurchase()`

Tests the following:
- Adds products to cart
- Navigates to Place Order
- Fills order form with customer details:
  - Name: ARSALAN
  - Country: Khan
  - City: testing
  - Month: 7
  - Year: 2025
- Clicks Purchase button

### Scenario 7: Capture Purchase Details
**File**: `testScenario7_CapturePurchaseDetails()`

Tests the following:
- Executes complete purchase flow
- Captures purchase ID
- Captures purchase amount
- Logs complete confirmation info

### Scenario 8: Assert Purchase Details
**File**: `testScenario8_AssertPurchaseDetails()`

Tests the following:
- Verifies purchase confirmation page
- Asserts Purchase ID is not empty
- Asserts Purchase Amount is not empty

### Scenario 9: Complete Purchase Flow
**File**: `testScenario9_CompletePurchaseFlow()`

Executes all previous scenarios as a complete end-to-end flow.

## Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn test -Dtest=DemoblazeTest
```

### Run Specific Test Scenario

```bash
mvn test -Dtest=DemoblazeTest#testScenario1_ProductCategoryNavigation
```

### Run Multiple Specific Tests

```bash
mvn test -Dtest=DemoblazeTest#testScenario1_ProductCategoryNavigation,DemoblazeTest#testScenario2_AddSonyVaioToCart
```

### Run Tests in Headless Mode

Edit `TestConfig.java` and set:
```java
public static final boolean HEADED_MODE = false;
```

Then run:
```bash
mvn clean test
```

### Run Tests with Slow Motion (for debugging)

Edit `TestConfig.java` and set:
```java
public static final boolean SLOW_MOTION = true;
public static final int SLOW_MOTION_DELAY = 2000; // 2 seconds per action
```

## Reports and Logs

### Screenshots

All screenshots are automatically saved to the `screenshots/` directory with the following naming convention:

```
{scenarioName}_{stepNumber}_{description}.png
```

Example:
- `Scenario1_01_HomePage.png`
- `Scenario1_02_HomePageValidated.png`
- `Scenario2_03_SonyVaioProductPage.png`
- `Scenario6_FAILED.png` (in case of failure)

### Log Files

Log files are stored in the `logs/` directory:

- **demoblaze-automation.log** - All logs
- **demoblaze-errors.log** - Only error logs
- **demoblaze-rolling-{date}-{number}.log** - Rolling daily logs

### Sample Log Output

```
2026-03-05 10:30:45 [main] INFO  DemoblazeTest - === SCENARIO 1: Product Category Navigation ===
2026-03-05 10:30:45 [main] INFO  HomePage - Navigating to: https://www.demoblaze.com/index.html
2026-03-05 10:30:47 [main] INFO  HomePage - Validating home page
2026-03-05 10:30:47 [main] INFO  DemoblazeTest - ✓ Logo validated as PRODUCT STORE
2026-03-05 10:30:47 [main] INFO  ScreenshotUtil - Screenshot saved: screenshots/Scenario1_02_HomePageValidated.png
```

## Troubleshooting

### Issue: Tests timeout after 10 seconds

**Solution**: Increase timeout in `TestConfig.java`:
```java
public static final int PAGE_LOAD_TIMEOUT = 15000; // Increase to 15 seconds
```

### Issue: Element not found errors

**Solution 1**: Increase element wait timeout:
```java
public static final int ELEMENT_WAIT_TIMEOUT = 10000; // Increase to 10 seconds
```

**Solution 2**: Verify element selectors in page classes are correct for current website version.

### Issue: Playwright browsers not found

**Solution**: Reinstall Playwright browsers:
```bash
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
```

### Issue: Screenshot directory doesn't exist

**Solution**: The `screenshots/` directory will be created automatically on first run. If issues persist, create manually:
```bash
mkdir screenshots
```

### Issue: Permission denied errors on Linux/Mac

**Solution**: Grant execute permissions:
```bash
chmod +x mvn
chmod -R +x src/
```

### Issue: Tests pass locally but fail in CI/CD

**Solution 1**: Ensure headless mode in CI/CD environment:
```java
public static final boolean HEADED_MODE = false;
```

**Solution 2**: Add environment variable in CI/CD:
```bash
export PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=0
```

### Issue: Cannot detect popup alerts

**Solution**: Ensure `handleAlert()` is called before clicking the button that triggers alert:
```java
productPage.handleAlert();
productPage.clickAddToCartButton();
```

## Debugging Tests

### Enable Debug Mode in IDE

1. Set breakpoint in test method
2. Right-click on test class → Debug As → JUnit Test
3. Use IDE debugger to step through code

### Add Custom Logging

```java
logger.debug("Debugging message");
logger.info("Information message");
logger.warn("Warning message");
logger.error("Error message", exception);
```

### Take Screenshot at Specific Point

```java
ScreenshotUtil.takeScreenshot(page, "TestName", "CustomStepName");
```

### Slow Down Test Execution

Add waits between actions:
```java
page.waitForTimeout(2000); // Wait 2 seconds
```

## Best Practices

1. **Always use Page Object Model** - Keep selectors and actions in page classes
2. **Use meaningful test names** - Test method names should describe what they test
3. **Log everything** - Add logging at each step for debugging
4. **Take screenshots** - Capture screen at critical steps
5. **Use assertions** - Verify expected behavior with assertions
6. **Handle alerts** - Call `handleAlert()` before clicking buttons that trigger popups
7. **Wait for elements** - Use appropriate waits for dynamic elements
8. **Clean up resources** - tearDown method closes browsers automatically

## Dependencies

The project uses the following key dependencies:

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Playwright | 1.40.0 | Browser automation |
| JUnit | 4.13.2 | Testing framework |
| Log4j2 | 2.21.0 | Logging framework |
| Gson | 2.10.1 | JSON processing |
| Commons Lang | 3.12.0 | Utility functions |

## Continuous Integration

### GitHub Actions Example

Create `.github/workflows/test.yml`:

```yaml
name: Demoblaze Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK
      uses: actions/setup-java@v2
      with:
        java-version: '11'
    - name: Run tests
      run: mvn clean test -Dheadless=true
```

## Support and Contributions

For issues, questions, or contributions:
1. Check existing documentation
2. Review log files for error details
3. Consult inline code comments
4. Create detailed issue report with logs and screenshots

## License

This project is provided as-is for educational and testing purposes.

---

**Last Updated**: March 5, 2026
**Framework Version**: 1.0.0
**Playwright Version**: 1.40.0
**Java Version**: 11+
