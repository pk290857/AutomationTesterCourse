package org.example;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilsPK.findElems.*;


public class TestClass {

    /*static final String[] tabPersData = {"Mia Godfrey" , "41 Withers Close" , "ALLERBY"
            , "CA5 3XH" ,"United Kingdom" ,"070 8791 3194"} ;*/

    @Test
    void test1(){
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver() ;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //stosuje sie tylko raz
        // explicite wait : istala sie konkretne warunki ale tez tylko raz sie wywoluje ;
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

        buttElem = findBySelectorPK("#_desktop_logo" , driver) ;
        buttElem.click();

        buttElem = findBySelectorPK("#content > section > div > div:nth-child(2) > article > div > div.thumbnail-top > a > img" , driver) ;
        buttElem.click();

        buttElem = findBySelectorPK("#group_1 > option:nth-child(2)" , driver) ;
        buttElem.click();

        buttElem = findBySelectorPK("#quantity_wanted" , driver) ;
        buttElem.click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buttElem.sendKeys(Keys.BACK_SPACE);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buttElem.sendKeys("5");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buttElem.sendKeys(Keys.ENTER);

        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));
        waiting.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#myModalLabel")));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        buttElem = findBySelectorPK("#myModalLabel" , driver) ;
        String auxStr = buttElem.getText() ;
        org.assertj.core.api.Assertions.assertThat(auxStr).contains("Product successfully");

        //czy rozmar OK?
        buttElem = findBySelectorPK("#blockcart-modal > div > div > div.modal-body > div > div.col-md-5.divide-right > div > div:nth-child(2) > span.size" , driver) ;
        auxStr = buttElem.getText();
        org.assertj.core.api.Assertions.assertThat(auxStr).contains("Size: M");
        //czy liczba OK?
        buttElem = findBySelectorPK("#blockcart-modal > div > div > div.modal-body > div > div.col-md-5.divide-right > div > div:nth-child(2) > span.product-quantity" , driver) ;
        auxStr = buttElem.getText();
        //System.out.println(auxStr);
        org.assertj.core.api.Assertions.assertThat(auxStr).contains("Quantity: 5");


        //proceed to checkout
        buttElem = findBySelectorPK("#blockcart-modal > div > div > div.modal-body > div > div.col-md-7 > div > div > a" , driver) ;
        buttElem.click();
        //czy liczba OK?
        buttElem = findBySelectorPK("#main > div > div.cart-grid-body.col-xs-12.col-lg-8 > div > div.cart-overview.js-cart > ul > li > div > div.product-line-grid-right.product-line-actions.col-md-5.col-xs-12 > div > div.col-md-10.col-xs-6 > div > div.col-md-6.col-xs-6.qty > div > input" , driver) ;
        org.assertj.core.api.Assertions.assertThat(buttElem.getAttribute("value")).isEqualTo("5") ;


        //czy rozmiar OK ?
        buttElem = findBySelectorPK("#main > div > div.cart-grid-body.col-xs-12.col-lg-8 > div > div.cart-overview.js-cart > ul > li > div > div.product-line-grid-body.col-md-4.col-xs-8 > div.product-line-info.size > span.value" , driver) ;
        org.assertj.core.api.Assertions.assertThat(buttElem.getText()).isEqualTo("M") ;

        buttElem = findBySelectorPK("#main > div > div.cart-grid-right.col-xs-12.col-lg-4 > div.card.cart-summary > div.cart-detailed-totals.js-cart-detailed-totals > div.card-block.cart-summary-totals.js-cart-summary-totals > div > span.value", driver);
        String auxStrMemory = buttElem.getText() ;

        //procedowac dalej
        buttElem = findBySelectorPK("#main > div > div.cart-grid-right.col-xs-12.col-lg-4 > div.card.cart-summary > div.checkout.cart-detailed-actions.js-cart-detailed-actions.card-block > div > a", driver);
        buttElem.click();

        //continue
        //#checkout-addresses-step > div > div > form > div.clearfix > button
        buttElem = findBySelectorPK("#checkout-addresses-step > div > div > form > div.clearfix > button", driver);
        buttElem.click();
        //zaznaczam opcje pierwsza
        buttElem = findBySelectorPK("#delivery_option_1", driver);
        buttElem.submit();//
        //potwierdz adres
        //
        buttElem = findBySelectorPK("#js-delivery > button", driver);
        buttElem.click();//zaznacz payment
        //#payment-option-1
        buttElem = findBySelectorPK("#payment-option-1", driver);
        buttElem.click();//zaznacz obligacje
        //#conditions_to_approve\[terms-and-conditions\]
        buttElem = findBySelectorPK("#conditions_to_approve\\[terms-and-conditions\\]", driver);
        buttElem.click();//zatwierdz zamowienie
        //#payment-confirmation > div.ps-shown-by-js > button
        buttElem = findBySelectorPK("#payment-confirmation > div.ps-shown-by-js > button\n", driver);
        buttElem.click();//zatwierdz zamowienie

        //czy liczba OK?
        buttElem = findBySelectorPK("#order-items > div.order-confirmation-table > div > div.col-sm-6.col-xs-12.qty > div > div:nth-child(2)", driver);
        auxStr = buttElem.getText() ;
        //System.out.println(auxStr);
        org.assertj.core.api.Assertions.assertThat(auxStr).isEqualTo("5") ;
//
        // czy laczna kwota OK ?
        //
        buttElem = findBySelectorPK("#order-items > div.order-confirmation-table > div > div.col-sm-6.col-xs-12.qty > div > div.col-xs-4.text-sm-center.text-xs-right.bold", driver);
        auxStr = buttElem.getText() ;
        //System.out.println(auxStr);
        org.assertj.core.api.Assertions.assertThat(auxStr).isEqualTo(auxStrMemory) ;

        //czy jest napis confirmed
        //#content-hook_order_confirmation > div > div > div > h3
        buttElem = findBySelectorPK("#content-hook_order_confirmation > div > div > div > h3", driver);
        auxStr = buttElem.getText();
        //System.out.println(auxStr);
        org.assertj.core.api.Assertions.assertThat(auxStr).containsIgnoringCase("is confirmed");

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("C:\\Users\\pk290\\OneDrive\\DokumentyPC\\CodersLabAutomat\\repo\\AutomationTesterCourse\\workshopProblem2\\target\\testScreen.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.findElements(By.te

        driver.quit();
    }




}
