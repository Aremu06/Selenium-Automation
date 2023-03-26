package Pages;// Page URL: https://web.verimi.de/dipp/api/authenticate?login_challenge=04b6c471311245fea8a8cc8f503bcbc6

import Utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class LoginPage {

    private ElementActions elementActions;

    private By email_TextField = By.id("emailField");
    private By password_TextField = By.id("passwordField");
    private By login_Button = By.id("loginButton");

    public LoginPage(WebDriver driver) throws AWTException {
        elementActions = new ElementActions(driver);
    }

    public void login(String email, String password) {

        elementActions.write(email_TextField, email);
        elementActions.write(password_TextField, password);
        elementActions.click(login_Button);
    }


}
