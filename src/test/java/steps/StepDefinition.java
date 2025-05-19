package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class StepDefinition {
    WebDriver driver = Hooks.driver;

    @Given("I open the B2C2 homepage")
    public void i_open_the_B2C2_homepage() throws InterruptedException {
        driver.get("https://www.b2c2.com/");
        sleep(2000);
    }

    @When("I click on the {string} link in the navigation menu")
    public void i_click_on_the_link_in_the_navigation_menu(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1: Hover over the 'About' menu
        WebElement aboutMenu = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@class='link-8' and contains(text(),'About')]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(aboutMenu).perform();

        // Step 2: Wait and click the "About Us" submenu item
        WebElement aboutUsLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.linkText(linkText)));
        aboutUsLink.click();
    }

    @Then("the page should contain the text {string}")
    public void the_page_should_contain_the_text(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait until the page content is loaded
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // Assertion: verify expected text is on the page
        boolean isTextPresent = driver.getPageSource().contains(expectedText);
        Assert.assertTrue("Expected text not found: " + expectedText, isTextPresent);

        // Optional: Assert URL contains /about-us
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("URL does not contain '/about-us'", currentUrl.contains("/about-us"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        //driver.quit();
    }

    @Given("I open the About us page")
    public void i_open_the_about_us_page() throws InterruptedException {
        driver.get("https://www.b2c2.com/about/about-us");
        sleep(2000);
    }

    @When("I click on the B2C2 image")
    public void i_click_on_the_B2C2_image() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            List<WebElement> desktopLogos = driver.findElements(By.cssSelector("a.for-desktop[aria-label='home']"));
            List<WebElement> mobileLogos = driver.findElements(By.cssSelector("a.for-mobile[aria-label='home']"));

            WebElement logoToClick = null;
            wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            if (!desktopLogos.isEmpty() && desktopLogos.get(0).isDisplayed()) {
                logoToClick = desktopLogos.get(0);
            } else if (!mobileLogos.isEmpty() && mobileLogos.get(0).isDisplayed()) {
                logoToClick = mobileLogos.get(0);
            }
            wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            if (logoToClick != null) {
                wait.until(ExpectedConditions.elementToBeClickable(logoToClick)).click();
            } else {
                Assert.fail("No visible B2C2 logo found to click.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click the B2C2 logo: " + e.getMessage());
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // ✅ Wait until the page URL confirms redirection
        wait.until(ExpectedConditions.urlToBe("https://www.b2c2.com/"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Not redirected to homepage", "https://www.b2c2.com/", currentUrl);

        // ✅ Add small buffer to allow logo to load (or wait for body to load)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // ✅ Then check logo visibility (robust against race conditions)
        List<WebElement> logos = driver.findElements(By.cssSelector("a.for-desktop[aria-label='home'], a.for-mobile[aria-label='home']"));

        boolean isVisible = logos.stream().anyMatch(WebElement::isDisplayed);
        Assert.assertTrue("Homepage logo is not visible after redirect", isVisible);
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        //driver.quit();
    }





}
