import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BlockchainWikiImageTest {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        // Using a library to ensure the chromedriver is installed and set in the appropriate system variable
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        // Sets the chrome driver to run in headless mode
        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
        driver = new ChromeDriver(ChromeOptions);
    }

    @After
    public void teardown() {
        // Cleanup the driver if the test does not do so
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void ImagesDoNotReturn404() throws Exception {
        // Navigate to the wiki page
        driver.navigate().to("https://en.wikipedia.org/wiki/Blockchain");

        // Get all image elements on the page
        List<WebElement> allImages = driver.findElements(By.tagName("img"));

        // Check that each image source is valid
        for (WebElement ele : allImages) {
            // Check that we have a valid link
            if (!CheckLink(ele)){
                // This message is a bit misleading, it could return false for many reasons
                throw new Exception("Image did not load " + ele.getAttribute("outerHTML"));
            }
        }
        driver.quit();
    }

    private Boolean CheckLink(WebElement Im) {
        int Response = 0;
        String Source = "";
        try {
            Source = Im.getAttribute("src");
            if (Source.equals("#"))
                return false;
            if (Source.startsWith("/")) {
                String BaseUrl = new URL(driver.getCurrentUrl()).getHost();
                Source = BaseUrl + Source;
            }
            HttpURLConnection Connect = (HttpURLConnection) (new URL(Source)).openConnection();
            Response = Connect.getResponseCode();
            System.out.println("Responsecode: " + Response);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (!((Response + "").startsWith("4") || (Response + "").startsWith("5"))) {
            System.out.println("Valid Source:" + ((Source == null) ? "null" : Source));
            return true;
        } else {
            System.out.println("Invalid Source:" + ((Source == null) ? "null" : Source));
            return false;
        }
    }
}
