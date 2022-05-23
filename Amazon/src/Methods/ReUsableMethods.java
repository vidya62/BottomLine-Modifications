package Methods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReUsableMethods {
	
	public static void languageSelect(WebDriver driver, String language) {
		click(driver, "xpath", "(//span[normalize-space()='Book Language']/following::i/following::span[text()='"+language+"'])[1]");
	}
    
    public static void setValue(WebDriver driver, String findBy, String typeValue, String value) {
    	WebElement element = null;
    	if(findBy.equalsIgnoreCase("id")) {
    		element = driver.findElement(By.id(typeValue));
    	}
    	if(findBy.equalsIgnoreCase("classname")) {
    		element = driver.findElement(By.className(typeValue));
    	}
    	element.sendKeys(value);
    }
    
 public static String getValue(WebDriver driver, String findBy, String typeValue) {
    	WebElement element = null;
    	if(findBy.equalsIgnoreCase("id")) {
    		element = driver.findElement(By.id(typeValue));
    	}
    	if(findBy.equalsIgnoreCase("classname")) {
    		element = driver.findElement(By.className(typeValue));
    	}
    	return element.getText();
    }
    
 public static void click(WebDriver driver, String findBy, String typeValue) {
    	WebElement element = null;
    	if(findBy.equalsIgnoreCase("xpath")) {
    		element = driver.findElement(By.xpath(typeValue));
    	}
    	if(findBy.equalsIgnoreCase("id")) {
    		element = driver.findElement(By.id(typeValue));
    	}
    	if(findBy.equalsIgnoreCase("classname")) {
    		element = driver.findElement(By.className(typeValue));
    	}
    	element.click();
    }
}
