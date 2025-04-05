
package generic;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverCommanLib extends BaseTest {

	public void selectTheOptionOfDropdown(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);

	}

	public void verifypageTitle(String pageName, String expectedTitle) {
		String actualPageTitle = driver.getTitle();
		if (actualPageTitle.equals(expectedTitle)) {
			Reporter.log(" " + pageName + " title is matched", true);
		}

		else {

			Reporter.log(" " + pageName + " title is not matched", true);
		}
	}

	public void selectTheOptionOfDropdown(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);

	}

	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBePresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}
		
}
