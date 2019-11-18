import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoTest {

    private WebDriver driver;
    @BeforeClass
    public static  void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setupTest(){
        driver =  new ChromeDriver();
    }

    @After
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void testTitle(){
        driver.get("https://the-internet.herokuapp.com/login");
        String a =driver.getTitle();
        Assert.assertEquals(a,"The Internet");
    }
    @Test
    public void testLogin(){
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement username =  driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        WebElement password = driver.findElement(By.cssSelector("input#password"));
        password.sendKeys("SuperSecretPassword!");
        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        button.click();

        WebElement heading  =  driver.findElement(By.tagName("h2"));
        Assert.assertEquals(heading.getText(), "Secure Area");

    }
    @Test
    public  void testLoginFailed(){
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement username =  driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        WebElement password = driver.findElement(By.cssSelector("input#password"));
        password.sendKeys("Wrong");
        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        button.click();
        WebElement heading  =  driver.findElement(By.cssSelector("#flash"));
        Assert.assertTrue(heading.isDisplayed());

    }
}
