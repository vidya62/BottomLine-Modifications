package Methods;

import org.openqa.selenium.WebDriver;

public class BookSearchApplication {
    
    public void openSite(WebDriver driver,String siteURL) {
    	driver.get(siteURL);
    }
}
