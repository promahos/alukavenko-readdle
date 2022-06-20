import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

public class BasePage {
    protected WebDriver driver;

    @FindBy(name = "search")
    private WebElement searchField;

    @FindBy(className = "suggest-goods")
    private List<WebElement> searchSuggestions;

    @FindBy(className = "header__logo")
    private WebElement headerLogo;

    @FindBy(xpath = "//li[contains(@class,'cart')]//button")
    private WebElement openCartButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public MainPage goToMainPage() {
        headerLogo.click();
        return new MainPage(driver);
    }

    public CartPopup openCart() {
        openCartButton.click();
        return new CartPopup(driver);
    }

    public ProductPage openProductFromSearchResults() {
        if (searchSuggestions.size() > 0) {
            searchSuggestions.get(0).click();
        }
        return new ProductPage(driver);
    }

    public void searchProduct(String searchString) {
        searchField.sendKeys(searchString);
    }
}
