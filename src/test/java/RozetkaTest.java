import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

public class RozetkaTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void verifyProductAdding() {
        // 1. Open website https://rozetka.com.ua/
        driver.get("https://rozetka.com.ua/");
        BasePage basePage = new BasePage(driver);

        // 2. Find “MD506Z/A” product through search panel
        String productCode = "MD506Z/A";
        basePage.searchProduct(productCode);

        // 3. Open the product page from search panel
        ProductPage productPage = basePage.openProductFromSearchResults();

        // 4. Add this product to the shopping cart
        CartPopup cartPopup = productPage.addProductToCart();

        // 5. Close shopping cart popup
        cartPopup.close();

        // 6. Go to the main page of the site
        MainPage mainPage = productPage.goToMainPage();

        // 7. Open the shopping cart and check the product is added
        String firstProductName = mainPage
                .openCart()
                .getFirstProductName();

        assertThat(firstProductName)
                .as("First product title in cart should contain search code")
                .containsIgnoringCase(productCode);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
