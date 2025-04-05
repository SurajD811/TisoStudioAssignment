package pagePackage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Flib;
import generic.WebDriverCommanLib;

public class FoodOrderFlow extends BaseTest{
	
	@FindBy(xpath="(//a[contains(@href,'restaurants')])[1]") private WebElement firstRestaurant;
	@FindBy(xpath="//button[text()='Add to Cart']")private List<WebElement> addToCartButtons;
	@FindBy(xpath="//a[contains(@href,'cart')]")private WebElement cartLink;
	@FindBy(xpath="//a[contains(@href,'cart')]//div[@class='relative']//div")private WebElement totalItemsInCart;
	@FindBy(xpath="//div[contains(@class,'mt-4')]//p")private List<WebElement> allPrices;
	@FindBy(xpath="//span[text()='Subtotal']/following-sibling::span")private WebElement subTotal;
	@FindBy(xpath="//span[text()='Delivery Fee']/following-sibling::span")private WebElement deleveryFee;
	@FindBy(xpath="//span[text()='Total']/following-sibling::span")private WebElement totalAmount;
	@FindBy(xpath="//button[text()='Proceed to Checkout']")private WebElement proceedButton;
	@FindBy(id="cash")private WebElement cashOnDeliveryRadioButton;
	@FindBy(id="address")private WebElement addressInputField;
	@FindBy(id="city")private WebElement cityField;
	@FindBy(id="zipCode")private WebElement zipCodeField;
	@FindBy(id="phone")private WebElement phoneField;
	@FindBy(xpath="//button[text()='Place Order']")private WebElement placeOrderButton;
	@FindBy(tagName="h1")private WebElement orderSuccesMessage;
	
	public FoodOrderFlow(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@Test
	public void foodOrderFlow() throws InterruptedException, IOException  {

		WebDriverCommanLib wcl=new WebDriverCommanLib();
		wcl.waitForElementToBeClickable(firstRestaurant);
		firstRestaurant.click();
		wcl.waitForElementToBeVisible(cartLink);
		
		if(cartLink.isDisplayed()) {
			System.out.println("Restaurant Menu Page Displayed SucessFully");
		}
		else {
			System.out.println("Restaurant Menu Page not Displayed");
		}
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
			
		for(int i=0;i<2;i++) {
			WebElement addToCart=addToCartButtons.get(i);
			addToCart.click();
		}
		
		String totalCartItems=totalItemsInCart.getText();
		
		if(totalCartItems.equals("2")) {
			System.out.println("Items added to the cart successfully");
		}
		else {
			System.out.println("Items not added to the cart properly");
		}
		
		cartLink.click();
		wcl.waitForElementToBeVisible(proceedButton);
		
		if(proceedButton.isDisplayed()) {
			System.out.println("Cart page displayed Succesfully");
		}
		else {
			System.out.println("Cart page not displayed");
		}
		
		double subTotalPrice=0;
		
		for(WebElement totalCartItemPrice:allPrices) {
			String price=totalCartItemPrice.getText();
			String number=price.replaceAll("[^0-9.]","");
			double numberPrice=Double.parseDouble(number);
			subTotalPrice=subTotalPrice+numberPrice;
		}
		
		String subTotalNumber=subTotal.getText();
		String subTotalnumbervalue=subTotalNumber.replaceAll("[^0-9.]","");
		double subTotaldoubleValue=Double.parseDouble(subTotalnumbervalue);
		
		if(subTotalPrice==subTotaldoubleValue) {
			System.out.println("Sub total value is properly calculated");
		}
		else {
			System.out.println("Sub total value is not properly calculated");
		}
		
		
		proceedButton.click();
		
		wcl.waitForElementToBeVisible(cashOnDeliveryRadioButton);
		if(cashOnDeliveryRadioButton.isSelected()) {
			System.out.println("CashOn Delivery option is selected");
		}
		else {
			cashOnDeliveryRadioButton.click();
		}
		
		
		addressInputField.sendKeys("123 Main Street Anytown, CA 98765");
		cityField.sendKeys("CA");
		zipCodeField.sendKeys("1000001");
		phoneField.sendKeys("1234567890");
		
		placeOrderButton.click();
		wcl.waitForElementToBeVisible(orderSuccesMessage);	
		if(orderSuccesMessage.isDisplayed()) {
			System.out.println("Order placed Succesfully");
		}
		else {
			System.out.println("Order is not placed Succesfully");
		}
		
		
		
	}
	
	

}
