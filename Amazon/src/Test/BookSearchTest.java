package Test;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Methods.BookSearchApplication;
import Methods.ReUsableMethods;

public class BookSearchTest {

    static WebDriver driver = null;
    public static String bookName = "the Lost World by Arthur Conan Doyle";

    public static void main(String[] args) throws InterruptedException {
    	contextLoads();
    }
    
    public static void setup() {
    	System.setProperty("webdriver.chrome.driver", "./src/Test/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

    @SuppressWarnings("static-access")
	public static void contextLoads() throws InterruptedException {
        setup();
        BookSearchApplication bookSearchApp = new BookSearchApplication();
        ReUsableMethods reusableMethods = new ReUsableMethods();
        bookSearchApp.openSite(driver, "https://www.amazon.com/");
		 
        if (driver.findElements(By.id("nav-bb-logo")).size() > 0) {
        	reusableMethods.click(driver, "id","nav-bb-logo");
        }
        Thread.sleep(2000);
        reusableMethods.setValue(driver, "id","twotabsearchtextbox", bookName);
        reusableMethods.click(driver, "id","nav-search-submit-button");
      
        String totalSearchResults = reusableMethods.getValue(driver, "className", "rush-component").split(" ")[2];
        System.out.println("Total search results for the book is:" + totalSearchResults);

        reusableMethods.languageSelect(driver, "English");
        String totalEnglishSearchResults = reusableMethods.getValue(driver, "className", "rush-component").split(" ")[2];
        System.out.println("Total search results filtering with English Language " + totalEnglishSearchResults);
        ArrayList<String> bookNames = new ArrayList<String>();
        Boolean isNextButtonDisabled = true;
        Integer counter = 1;
        while (isNextButtonDisabled) {
        	Thread.sleep(5000);
            isNextButtonDisabled = driver.findElements(By.xpath("//span[@class='s-pagination-item s-pagination-next s-pagination-disabled ']")).size() != 1;
            List<WebElement> searchResultOnPage = driver.findElements(By.className("s-title-instructions-style"));
            searchResultOnPage.forEach(book -> {
                if (!book.getText().contains("Sponsored")) {
                    bookNames.add(book.findElement(By.tagName("h2")).getText());
                }
            });
            System.out.println("Searching page " + counter++);
            reusableMethods.click(driver, "className", "s-pagination-next");
        }
        List<String> maxBookName1 = bookNames.stream()
                .filter(bookName -> bookName.toLowerCase().contains(bookName.toLowerCase()))
                .distinct().collect(Collectors.toList());
        Optional maxBookName = maxBookName1.stream()
                .max(Comparator.comparingInt(String::length));
        System.out.println("The max book name is '" + maxBookName.get() + "' and its length is " + maxBookName.get().toString().length());
        assertTrue(maxBookName.get().toString().length() < 71, "Longest book name is more than 70 characters");
	driver.quit();
    }
}
