package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static utilsPK.findElems.findBySelectorPK;

import static utilsPK.findElems.findBySelectorsPK;

import static utilsPK.findElems.sendKeysPK;


public class TestClass {

    /*static final String[] tabPersData = {"Mia Godfrey" , "41 Withers Close" , "ALLERBY"
            , "CA5 3XH" ,"United Kingdom" ,"070 8791 3194"} ;*/

    @Test
    void test1(){
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver() ;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mystore-testlab.coderslab.pl/");


        WebElement buttElem = findBySelectorPK( "#_desktop_user_info > div > a > span", driver) ;
        buttElem.click();

        List<String> listFields =  new ArrayList<>();
        listFields.add("#field-email") ;
        listFields.add("#field-password");
        List<WebElement> listFieldElems = findBySelectorsPK(listFields , driver) ;

        List<String> listKeys = new ArrayList<>();
        listKeys.add("MiaGodfrey@dayrep.com") ;
        listKeys.add("Il6ohg6Pohh");
        sendKeysPK(listFieldElems , listKeys);

        buttElem = findBySelectorPK("#submit-login" , driver) ;
        buttElem.click();

        buttElem = findBySelectorPK("#addresses-link" , driver) ;
        buttElem.click();

        List<WebElement> elemAux = driver.findElements(By.cssSelector(".address-body")) ;
        int auxLengthBeforeAddition = elemAux.size() ;
        Assertions.assertTrue(auxLengthBeforeAddition >=1); // to powinna byc nie asercja a raczej rzucenie wyjatku

        buttElem = findBySelectorPK("#content > div.addresses-footer > a" , driver) ;
        buttElem.click();

        listFields.clear();
        listFields.add("#field-alias") ;
        listFields.add("#field-address1") ;
        listFields.add("#field-city") ;
        listFields.add("#field-postcode") ;
        listFields.add("#field-phone") ;

        listFieldElems.clear();
        listFieldElems = findBySelectorsPK(listFields , driver) ;

        listKeys.clear();
        listKeys.add("Guie1971") ;
        listKeys.add("84 Manor Close");
        listKeys.add("DIDSBURY");
        listKeys.add("M20 0YB");
        listKeys.add("070 5124 9699");
        sendKeysPK(listFieldElems , listKeys);

        buttElem = findBySelectorPK(/*"#field-id_country"*/"#field-id_country > option:nth-child(2)" , driver) ;
        buttElem.click();

        buttElem = findBySelectorPK("#content > div > div > form > footer > button" , driver);
        buttElem.click();

        buttElem = findBySelectorPK( "#notifications > div > article", driver) ;
        org.assertj.core.api.Assertions.assertThat(buttElem.getText()).contains("successfully added") ;

        elemAux.clear(); ;
        elemAux = driver.findElements(By.cssSelector(".address-body")) ;
        int auxLengthAfterAddition = elemAux.size() ;
        Assertions.assertEquals(auxLengthBeforeAddition+1 , auxLengthAfterAddition );

        List<String> listStr = new LinkedList<String>(elemAux.get(elemAux.size()-1).getText().lines().toList()) ;
        listStr.remove(1);
        listStr.remove(listStr.size()-2);
        for (int i = 0; i <listKeys.size() ; i++) {
            assertEquals(listKeys.get(i) , listStr.get(i) );
        }

        elemAux.clear();
        elemAux = driver.findElements(By.cssSelector(".page-addresses .address .address-footer a")) ;
        elemAux.get(elemAux.size()-1).click();

        buttElem = findBySelectorPK( "#notifications > div > article", driver) ;
        org.assertj.core.api.Assertions.assertThat(buttElem.getText()).contains("successfully deleted") ;

        elemAux.clear();
        elemAux = driver.findElements(By.cssSelector(".address-body")) ;
        int auxLengthAfterDeletion = elemAux.size() ;
        Assertions.assertEquals(auxLengthBeforeAddition , auxLengthAfterDeletion);

        //this test lacks verification if the just added address was the one deleted;
        //to this aim one could  implement randomization of the address data
        // and checking whether there still exists the just added and removed address

        driver.quit();
    }




}
