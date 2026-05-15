package ecomwebsite.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ecomwebsite.TestComponents.BaseTest;
import ecomwebsite.pageobjects.CartPage;
import ecomwebsite.pageobjects.CheckOutPage;
import ecomwebsite.pageobjects.ConfirmationPage;
import ecomwebsite.pageobjects.LandingPage;
import ecomwebsite.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("User landed on Ecommerce Page")

	public void User_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given ("^User is Logged in with username (.+) and password (.+)$")
	public void User_is_Logged_in_with_username_and_password (String username, String password) {
		
		productCatalogue= landingPage.loginApplication(username, password);
				
	}
	
	@When ("^User add the product (.+) to Cart$")
	public void User_add_product(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
	}
	
	@When("^User checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));	
	}
	
}
