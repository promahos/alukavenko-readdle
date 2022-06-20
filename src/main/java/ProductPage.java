import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//app-product-buy-btn//app-buy-button//button[contains(@class,'buy-button')]")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPopup addProductToCart() {
        addToCartButton.click();
        return new CartPopup(driver);
    }
}
