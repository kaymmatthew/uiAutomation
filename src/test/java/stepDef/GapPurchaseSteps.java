package stepDef;

import base.BaseUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GapPurchaseSteps extends BaseUtil {
    private BaseUtil base;

    public GapPurchaseSteps(BaseUtil base) {
        this.base = base;

    }

    @Given("I am on the Gap Home page")
    public void i_am_on_the_gap_home_page() {
        base.driver.manage().window().maximize();
        base.driver.get("https://www.gap.co.uk/");
        base.driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        //*[@id='onetrust-accept-btn-handler']
    }

    @When("I click on accept all cookies")
    public void i_click_on_accept_all_cookies() throws InterruptedException{
        base.driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        Thread.sleep(2000);
    }

    //*[contains(@id,'meganav-link')][.='women']
    @When("I choose the Summer Essentials under the {string} category")
    public void i_choose_the_summer_essentials_under_the_category(String string) throws InterruptedException {
        WebElement womensMenu = base.driver.findElement(By.xpath("//div[contains(text(),'women')]"));
        //*[@data-testid='meganav-primarynav-link-women']
        //*[contains(@id,'meganav-link')][.='women']
        Actions actionProvider = new Actions(base.driver);
        actionProvider.moveToElement(womensMenu).perform();
        Thread.sleep(2000);
        List<WebElement> womensSummerEssentials = base.driver.findElements(By.xpath("//*[contains(text(),'New In')]"));
        womensSummerEssentials.get(0).click();
        //*[contains(@class, 'component_TitleContainer- ')][.='A11 Women']
    }

    @When("I choose the first item on the list")
    public void i_choose_the_first_item_on_the_list() {
        //close the pop up
        //base.driver.findElement(By.cssSelector("svg.email-popup-close")).click();

        //base.driver.findElements(By.cssSelector("div.product-name>a")).get(0).click();
        base.driver.findElements(By.xpath("//*[@class='Item  Fashion  ']")).get(0).click();
        //*[contains(text(),'Blue Oversized Turtleneck Jumper')]
    }

    @When("I choose size as {string} on the list of sizes")
    public void i_choose_size_as_m_reg_on_the_list_of_sizes(String text) throws InterruptedException {
        //base.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));


        //WebElement clickSize = base.driver.findElement(By.xpath("//*[@class='dk_dropdown_option'][.='M Reg']"));
        //Select selectObject = new Select(clickSize);

        WebElement drpdown = base.driver.findElement(By.xpath("//*[@class='dk_toggle dk_label']"));
        drpdown.click();
        WebElement allsizesOption = base.driver.findElement(By.xpath("//*[@class='dk_options']//li/a[.='"+text+"']"));
        allsizesOption.click();
    }

    @When("I click on {string}")
    public void i_click_on(String text) {
        // Write code here that turns the phrase above into concrete actions
        base.driver.findElement(By.xpath("//*[contains(@class, 'nxbtn primary btn')][.='Add To Bag']")).click();
//        base.driver.findElement(By.xpath("//button[.='Add to Bag']")).click();
//        base.driver.findElement(By.xpath("//button[.='" + text +"']")).click();
    }

    @Then("I should see error msg displayed as {string}")
    public void i_should_see_error_msg_displayed_as(String string) throws InterruptedException {
        Thread.sleep(2000);
        assertThat(base.driver.findElement(By.xpath("//div[.='Please select Size before adding to bag']")).isDisplayed(), is(equalTo(true)));
    }
}