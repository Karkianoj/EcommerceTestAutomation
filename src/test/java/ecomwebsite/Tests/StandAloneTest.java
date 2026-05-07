package ecomwebsite.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecomwebsite.TestComponents.BaseTest;
import ecomwebsite.pageobjects.CartPage;
import ecomwebsite.pageobjects.CheckOutPage;
import ecomwebsite.pageobjects.ConfirmationPage;
import ecomwebsite.pageobjects.OrderPage;
import ecomwebsite.pageobjects.ProductCatalogue;


public class StandAloneTest extends BaseTest {	
	    String productName = "ZARA COAT 3";
		@Test(dataProvider = "getData", groups = {"Purchase"})
		public void submitOrder(HashMap<String, String> input)throws IOException, InterruptedException {		
			
		

			ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductTocart(input.get("product"));
			productCatalogue.goToCartPage();

			CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			CheckOutPage checkoutPage = cartPage.goToCheckOut();
			checkoutPage.selectCountry("india");
			ConfirmationPage confirmationPage = checkoutPage.submitOrder();

			String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		}
		
		// To Verify ZARA COAT3 is displaying in orders page
		@Test(dependsOnMethods={"submitOrder"})
		public void OrderHistoryTest() {
			
			//"ZARA COAT 3
			ProductCatalogue productCatalogue = landingPage.loginApplication("ankarki9@gmail.com", "J0hn@123!");
			OrderPage ordersPage = productCatalogue.goToOrdersPage();
			ordersPage.VerifyOrderDisplay(productName);	
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		}
		
	
		
		//Extent Reports
		
		@DataProvider
		public Object[] [] getData() throws IOException {
			
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "/src/test/java/ecomwebsite/data/PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
		}
		
		
		
		
		
		

}
