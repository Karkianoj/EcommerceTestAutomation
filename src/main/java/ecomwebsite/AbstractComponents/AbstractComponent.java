package ecomwebsite.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecomwebsite.pageobjects.CartPage;
import ecomwebsite.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	@FindBy(css = ".ngx-spinner-overlay")
	WebElement newspinner;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(findBy)); // ✅ safe
	}

	public void waitForElementToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); // ✅ safe
	}
	
	public void waitForSpinnerToDisappear() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(
	        By.cssSelector(".ngx-spinner-overlay")
	    ));
	}
	
	public CartPage goToCartPage() {
		waitForSpinnerToDisappear();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cartIcon = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		cartIcon.click();
		return new CartPage(driver);
	}

	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
