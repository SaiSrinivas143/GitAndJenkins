package framework.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.parent.DriverManager;

public class CustomTestUtils extends DriverManager{
	
	public void waitUntilPageLoad() {
		try {
			new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("Complete");
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void waitUntilDisplayed(WebElement ele) {
		try {
			new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds *1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
