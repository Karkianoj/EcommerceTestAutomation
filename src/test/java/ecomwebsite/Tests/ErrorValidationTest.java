package ecomwebsite.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecomwebsite.TestComponents.BaseTest;
import ecomwebsite.pageobjects.CartPage;
import ecomwebsite.pageobjects.ProductCatalogue;


public class ErrorValidationTest extends BaseTest {	
		
		@Test(groups = {"ErrorHandling"})
		public void submitOrder()throws IOException, InterruptedException {		
			
			String productName = "ZARA COAT 3";
			landingPage.loginApplication("ankarki9@gmail.com", "J0hn@123!");
			String errorMessage = landingPage.getErrorMessage();
			Assert.assertEquals("Incorrect email or password.", errorMessage);
		}
		
		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException {
			String productName = "ZARA COAT 3";
			ProductCatalogue productCatalogue = landingPage.loginApplication("ankarki9@gmail.com", "J0hn@123!");
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductTocart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
			Assert.assertFalse(match);
			
		}

}
