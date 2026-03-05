# Demoblaze Automation Framework - Setup Complete

## Project Summary

A comprehensive Playwright Java automation framework for testing the Demoblaze e-commerce website with Page Object Model pattern, extensive logging, and 9 complete test scenarios.

## Technology Stack

- **Language**: Java 11+
- **Build Tool**: Maven 3.6+
- **Test Library**: Playwright 1.40.0
- **Test Framework**: JUnit 4.13.2
- **Logging**: Log4j2 2.21.0
- **Design Pattern**: Page Object Model (POM)

## Project Structure

```
AgentFramrwork/
├── pom.xml                              # Maven configuration
├── README.md                            # Complete documentation
├── .gitignore                           # Git ignore rules
│
├── src/main/java/com/demoblaze/
│   ├── pages/                           # Page Object Models
│   │   ├── BasePage.java                # Base class for all pages
│   │   ├── HomePage.java                # Home page object
│   │   ├── ProductPage.java             # Product page object
│   │   ├── CartPage.java                # Shopping cart page object
│   │   └── OrderPage.java               # Order/checkout page object
│   │
│   ├── utils/                           # Utility classes
│   │   └── ScreenshotUtil.java          # Screenshot capture utility
│   │
│   └── config/                          # Configuration
│       └── TestConfig.java              # Test configuration constants
│
├── src/test/
│   ├── java/com/demoblaze/tests/
│   │   ├── BaseTest.java                # Base test class
│   │   └── DemoblazeTest.java           # Test scenarios
│   │
│   └── resources/
│       └── log4j2.xml                   # Logging configuration
│
├── screenshots/                         # Automated screenshot storage
└── logs/                                # Test execution logs
```

## Key Components

### Page Object Models (POM)
- **BasePage.java**: Common methods for all pages (clicks, fills, waits, etc.)
- **HomePage.java**: Product category navigation and home page actions
- **ProductPage.java**: Product display and "Add to cart" functionality
- **CartPage.java**: Cart management (view, delete items, place order)
- **OrderPage.java**: Order form filling and purchase completion

### Test Class
- **BaseTest.java**: Setup/teardown with Playwright browser initialization
- **DemoblazeTest.java**: 9 complete test scenarios covering full purchase flow

### 9 Test Scenarios

1. **ProductCategoryNavigation** - Browse categories and validate products
2. **AddSonyVaioToCart** - Add laptop to cart with confirmation
3. **AddDellI7ToCart** - Add another laptop to cart
4. **DeleteProductFromCart** - Remove product from shopping cart
5. **ClickPlaceOrder** - Navigate to checkout
6. **FillOrderFormAndPurchase** - Fill order form and submit
7. **CapturePurchaseDetails** - Extract purchase ID and amount
8. **AssertPurchaseDetails** - Verify purchase information
9. **CompletePurchaseFlow** - Full end-to-end workflow

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher
- Internet connection (for dependency download)

### Quick Start

```bash
# 1. Build and install dependencies
mvn clean install

# 2. Run all tests
mvn test

# 3. Run specific scenario
mvn test -Dtest=DemoblazeTest#testScenario1_ProductCategoryNavigation
```

### Installation Output Expected

The first run will:
- Download all Maven dependencies
- Install Playwright browser binaries (Chromium)
- Compile 8 Java files
- Generate test reports

### Verification

Test execution produces:
- **Screenshots**: `screenshots/` directory with timestamped PNG files
- **Logs**: `logs/demoblaze-automation.log` with detailed execution trace
- **Errors**: `logs/demoblaze-errors.log` with error-only logs
- **Console Output**: Real-time test execution status

## Configuration

### Customize Test Parameters (TestConfig.java)

```java
// Change base URL
public static final String DEMOBLAZE_URL = "https://www.demoblaze.com/index.html";

// Adjust timeouts (milliseconds)
public static final int PAGE_LOAD_TIMEOUT = 10000;
public static final int ELEMENT_WAIT_TIMEOUT = 5000;

// Browser mode
public static class BrowserSettings {
    public static final boolean HEADED_MODE = true;  // false for headless
}

// Order form data
public static class TestData {
    public static final String CUSTOMER_NAME = "ARSALAN";
    public static final String CUSTOMER_COUNTRY = "Khan";
    public static final String CUSTOMER_CITY = "testing";
    public static final String CUSTOMER_MONTH = "7";
    public static final String CUSTOMER_YEAR = "2025";
}
```

### Customize Logging (log4j2.xml)

Edit `src/test/resources/log4j2.xml` to:
- Change log file location
- Adjust log level (DEBUG, INFO, WARN, ERROR)
- Modify rolling file policy
- Add custom appenders

## Running Tests

### All Tests
```bash
mvn clean test
```

### Specific Test
```bash
mvn test -Dtest=DemoblazeTest#testScenario1_ProductCategoryNavigation
```

### Multiple Tests
```bash
mvn test -Dtest=DemoblazeTest#testScenario1*,DemoblazeTest#testScenario2*
```

### Headless Mode
```bash
mvn clean test -Dheadless=true
```
(Also update TestConfig.java: `HEADED_MODE = false`)

## Viewing Results

### Screenshots Location
```
screenshots/
├── Scenario1_01_HomePage.png
├── Scenario1_02_HomePageValidated.png
├── Scenario2_03_SonyVaioProductPage.png
└── ...
```

### Log Files Location
```
logs/
├── demoblaze-automation.log      # All logs
├── demoblaze-errors.log           # Errors only
└── demoblaze-rolling-*.log        # Daily rolling logs
```

### Sample Log Output
```
2026-03-05 10:30:45 [main] INFO DemoblazeTest - === SCENARIO 1: Product Category Navigation ===
2026-03-05 10:30:47 [main] INFO HomePage - ✓ Logo validated as PRODUCT STORE
2026-03-05 10:30:48 [main] INFO ScreenshotUtil - Screenshot saved: screenshots/Scenario1_02_HomePageValidated.png
```

## Common Issues

### Issue: Playwright browsers not found
```bash
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
```

### Issue: Test timeout
Update `TestConfig.java`:
```java
public static final int PAGE_LOAD_TIMEOUT = 15000; // Increase timeout
```

### Issue: Element not found
1. Verify element selectors match current website
2. Take screenshot to inspect element
3. Check browser console for JavaScript errors

### Issue: Popup alerts not handled
Ensure `handleAlert()` is called before button click:
```java
productPage.handleAlert();
productPage.clickAddToCartButton();
```

## Best Practices

✅ **Use Page Object Model** - Keep selectors in page classes
✅ **Add Logging** - Log every significant action
✅ **Take Screenshots** - Capture at critical steps
✅ **Use Assertions** - Verify expected behavior
✅ **Handle Waits** - Wait for dynamic elements to load
✅ **Meaningful Names** - Test method names describe intent
✅ **Resource Cleanup** - tearDown closes all resources

## Documentation

- Full documentation in [README.md](../README.md)
- Page Object code comments for selector details
- Test method comments describing scenario steps
- Inline logging for execution trace

## Next Steps

1. ✅ Build project: `mvn clean install`
2. ✅ Run sample test: `mvn test -Dtest=DemoblazeTest#testScenario1*`
3. ✅ Review screenshots and logs
4. ✅ Customize TestConfig.java for your needs
5. ✅ Integrate with CI/CD pipeline

## Support Resources

- [Playwright Java Documentation](https://playwright.dev/java/)
- [JUnit 4 Documentation](https://junit.org/junit4/)
- [Maven Documentation](https://maven.apache.org/)
- [Log4j2 Configuration](https://logging.apache.org/log4j/2.x/)

---

**Framework Version**: 1.0.0
**Created**: March 5, 2026
**Playwright**: 1.40.0
**Java Target**: 11+
**Status**: ✅ Ready to Use
