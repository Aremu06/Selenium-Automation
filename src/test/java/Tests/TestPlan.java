package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import TestData.TestData;
import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.*;
import java.awt.*;

@Listeners(UniversalVideoListener.class)
public class TestPlan extends BaseTest {


    LoginPage loginPage;
    HomePage homePage;


    @BeforeClass
    public void initialize() throws AWTException {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    // Verify login existing user
    @Test(testName = "Login existing user")
    @Video
    public void loginPage() throws Exception {

        logger = report.createTest("Test Login Functionality ");
        logger.info("Login Using email & password");
        loginPage.login(TestData.UserOneEmail, TestData.UserOnePassword);
        homePage.changeLanguage();


        homePage.getElementsWithTagName();

        homePage.getStream();
    }


}