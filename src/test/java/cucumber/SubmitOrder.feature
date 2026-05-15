
@tag
Feature: Purchase the Order from Ecommerce

	Background:
	Given User landed on Ecommerce Page

	@tag2
	Scenario Outline:Positive test of Submitting the order
	Given User is Logged in with username <name> and password <password>
	When User add the product <productName> to Cart
	And User checkout <productName> and submit the order
	Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
	
	Examples:
	|name			   | password     | productName  |
	|ankarki9@gmail.com| J0hn@123!    | ZARA COAT 3  |