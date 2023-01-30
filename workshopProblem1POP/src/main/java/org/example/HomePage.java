package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utilsPK.findElems.findBySelectorPK;

public class HomePage {

    WebDriver driver ;

    String url ="https://mystore-testlab.coderslab.pl/" ;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://mystore-testlab.coderslab.pl/");
    }

    public void goToSignIn(){
        WebElement buttElem = findBySelectorPK( "#_desktop_user_info > div > a > span", this.driver) ;
        buttElem.click();
        return ;
    }
}
