import driver.Driver;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.Main;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    private Main objectMain;
    private WebDriver driver;

    // Открытие браузера и сайта
    @Before
    @DisplayName("Opening a browser and a website")
    public void before() {
        driver = Driver.createWebDriver();
        objectMain = new Main(driver);
    }
    // Проверка перехода к "Соусы"
    @Test
    @DisplayName("Checking the transition to the section with sauces")
    public void checkSauce() {
        objectMain.openMainPage();
        assertTrue("Error", objectMain.checkSauce());
    }
    // Проверка перехода "Булки"
    @Test
    @DisplayName("Checking the transition to the section with rolls")
    public void checkBuns() {
        objectMain.openMainPage();
        assertTrue("Error", objectMain.checkBuns());
    }
    // Проверка перехода к "Начинки"
    @Test
    @DisplayName("Checking the transition to the section with fillings")
    public void checkFillings() {
        objectMain.openMainPage();
        assertTrue("Error", objectMain.checkFillings());
    }
    // Закрываем браузер
    @After
    @DisplayName("Closing the browser")
    public void teardown() {
        driver.quit();
    }
}
