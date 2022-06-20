import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPopup extends BasePage {

    @FindBy(className = "modal__close")
    private WebElement closeButton;

    @FindBy(className = "cart-product__title")
    private List<WebElement> cartProductTitles;

    public CartPopup(WebDriver driver) {
        super(driver);
    }

    public void close() {
        closeButton.click();
    }

    public String getFirstProductName() {
        if (cartProductTitles.size() > 0) {
            return cartProductTitles
                    .get(0)
                    .getText();
        } else {
            return "";
        }
    }
}
