package org.example;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilsPK.findElems.*;

public class addressSteps {

    WebDriver driver ;
    WebElement buttElem ;

    List<WebElement> elemAux ;

    List<String> listFields ;

    List<WebElement> listFieldElems ;

    List<String> listKeys ;

    int auxLengthBeforeAddition ;

    int auxLengthAfterAddition ;

    @After
    public void closeBrowser(){
        driver.quit();
    }
    @Given("an existing user logged in")
    public void signingIn(){
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
        driver = new ChromeDriver() ;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mystore-testlab.coderslab.pl/");


        buttElem = findBySelectorPK( "#_desktop_user_info > div > a > span", driver) ;
        buttElem.click();

        listFields =  new ArrayList<>();
        listFields.add("#field-email") ;
        listFields.add("#field-password");
        listFieldElems = findBySelectorsPK(listFields , driver) ;


        listKeys = new ArrayList<>();
        listKeys.add("MiaGodfrey@dayrep.com") ;
        listKeys.add("Il6ohg6Pohh");
        sendKeysPK(listFieldElems , listKeys);


        buttElem = findBySelectorPK("#submit-login" , driver) ;
        buttElem.click();
    }
    @And("redirected to addresses")
    public void redirected_to_addresses() {
        buttElem = findBySelectorPK("#addresses-link" , driver) ;
        buttElem.click();
    }
    @And("there exists at least one defined address")
    public void assertionExistenceOfAddresses(){
        elemAux = driver.findElements(By.cssSelector(".address-body")) ;
        auxLengthBeforeAddition = elemAux.size() ;
        Assertions.assertTrue(auxLengthBeforeAddition >=1);
    }
    @When("clicked button to create a new address")
    public void clickingCreateAddress(){
        buttElem = findBySelectorPK("#content > div.addresses-footer > a" , driver) ;
        buttElem.click();
    }
    @And("the required fields are filled")
    public void fillingRequiredFields(){
        listFields.clear();
        listFields.add("#field-alias") ;
        listFields.add("#field-address1") ;
        listFields.add("#field-city") ;
        listFields.add("#field-postcode") ;
        listFields.add("#field-phone") ;


        listFieldElems = findBySelectorsPK(listFields , driver) ;

        listKeys.clear();
        listKeys.add("Guie1971") ;
        listKeys.add("84 Manor Close");
        listKeys.add("DIDSBURY");
        listKeys.add("M20 0YB");
        listKeys.add("070 5124 9699");
        sendKeysPK(listFieldElems , listKeys);

        buttElem = findBySelectorPK("#field-id_country" , driver) ;
        buttElem.click();
    }
    @And("submitted")
    public void submittingAddressFields(){
        buttElem = findBySelectorPK("#content > div > div > form > footer > button" , driver);
        buttElem.click();
    }
    @Then("the new address is displayed")
    public void checkingIfAddressDisplayed(){
        buttElem = findBySelectorPK( "#notifications > div > article", driver) ;
        org.assertj.core.api.Assertions.assertThat(buttElem.getText()).contains("successfully added") ;

        elemAux.clear();
        elemAux = driver.findElements(By.cssSelector(".address-body")) ;
        auxLengthAfterAddition = elemAux.size() ;
        Assertions.assertEquals(auxLengthBeforeAddition+1 , auxLengthAfterAddition );

        List<String> listStr = new LinkedList<String>(elemAux.get(elemAux.size()-1).getText().lines().toList()) ;
        listStr.remove(1);
        listStr.remove(listStr.size()-2);
        for (int i = 0; i <listKeys.size() ; i++) {
            assertEquals(listKeys.get(i) , listStr.get(i) );
        }
    }



}
