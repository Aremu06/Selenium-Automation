package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utils.ElementActions;

import java.awt.*;

public class HomePage {
    private ElementActions elementAction;

    public HomePage(WebDriver driver) throws AWTException {
        elementAction = new ElementActions(driver);
    }

    private By de_Button = By.id("langDe");
    private By profileIcon_Button = By.id("menu-profile");


    /***
     * Go to the profile page after successfully login
     * */
    public HomePage goToProfilePage() {
        elementAction.click(profileIcon_Button);
        return this;
    }

    /***
     * Print a list of WebElements having paragraph tag 'p'
     * */
    public void getElementsWithTagName() {

        elementAction.getElementsUsingTagName("p");

    }

    /***
     * Print a stream of list from previous step and print all lines containing word "bezahlen".
     **/
    public void getStream() throws Exception {

        elementAction.getStreamList("p", "bezahlen");
    }


    public void changeLanguage() {

        elementAction.click(de_Button);
        try {
            elementAction.forceWait(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
