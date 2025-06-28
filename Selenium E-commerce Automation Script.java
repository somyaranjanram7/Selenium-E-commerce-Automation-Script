// Main class for the Selenium automation script
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration; 

public class DemoblazeAutomation {

    public static void main(String[] args) {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("Navigating to demoblaze.com...");
        try {

            driver.get("https://www.demoblaze.com/");
            System.out.println("Successfully navigated to demoblaze.com.");

            // --- Login Process ---
            System.out.println("Initiating login process...");
    
            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
            loginLink.click();
            System.out.println("Clicked 'Log in' link.");

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));
            
            // Using a more specific XPath to ensure we click the correct 'Log in' button
            // which is part of the modal and has an onclick attribute.
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Log in') and @onclick='logIn()']")));

            // Enter dummy username and password into the respective fields.
            usernameField.sendKeys("testuser");
            passwordField.sendKeys("testpassword");
            System.out.println("Entered username and password.");

            // Click the login button to submit the credentials.
            loginButton.click();
            System.out.println("Clicked login button.");

            // Wait for login to complete. This is verified by waiting for the 'Log out' link
            // to become visible, indicating that the user is successfully logged in.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
            System.out.println("Login successful: 'Log out' link found.");

            // --- Select a Product ---
            System.out.println("Selecting a product...");
            // Locate and click on a specific product link, e.g., "Samsung galaxy s6".
            
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Samsung galaxy s6']")));
            productLink.click();
            System.out.println("Clicked on 'Samsung galaxy s6' product link.");

            // Wait for the product details page to load.
            // Verification is done by waiting for an element specific to the product details page,
            // such as the 'Add to cart' button.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Add to cart')]")));
            System.out.println("Product details page loaded. 'Add to cart' button found.");

            Thread.sleep(2000); 

            // --- Logout Process ---
            System.out.println("Initiating logout process...");
            // Click on the 'Log out' link, which is now visible after successful login.
            WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
            logoutLink.click();
            System.out.println("Clicked 'Log out' link.");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("logout2")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
            System.out.println("Logout successful: 'Log in' link reappeared.");
            
            System.out.println("Automation task completed successfully!");

        } catch (Exception e) {
    
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
        
            System.out.println("Closing the browser.");
            driver.quit(); 
        }
    }
}
