package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static utilsPK.findElems.*;

public class SignInPage {

    WebDriver driver ;

    public SignInPage(WebDriver driver) {
        this.driver = driver ;
    }

    public void signIn(){

        List<String> listFields =  new ArrayList<>();
        listFields.add("#field-email") ;
        listFields.add("#field-password");
        List<WebElement> listFieldElems = findBySelectorsPK(listFields , this.driver) ;

        List<String> listKeys = new ArrayList<>();
        listKeys.add("MiaGodfrey@dayrep.com") ;
        listKeys.add("Il6ohg6Pohh");
        sendKeysPK(listFieldElems , listKeys);


        WebElement buttElem ;
        buttElem = findBySelectorPK("#submit-login" , this.driver) ;
        buttElem.click();
    }
}
