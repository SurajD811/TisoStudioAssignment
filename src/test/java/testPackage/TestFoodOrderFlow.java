package testPackage;


import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.CustomListner;
import generic.RetryAnalyzer;
import pagePackage.FoodOrderFlow;

@Listeners(CustomListner.class)
public class TestFoodOrderFlow extends BaseTest{

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void foodOrderingFlow() throws InterruptedException, IOException {
		FoodOrderFlow fof=new FoodOrderFlow(driver);
		fof.foodOrderFlow();
	}
}
