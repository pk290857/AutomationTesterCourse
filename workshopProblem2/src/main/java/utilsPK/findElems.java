package utilsPK;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class findElems {

   static final int one = 1 ;


    public static WebElement findBySelectorPK(String extrSelector, WebDriver driv){
       List<WebElement> elems = driv.findElements(By.cssSelector(extrSelector)) ;
       if( (elems.size()) > one){
           throw new RuntimeException("selector " + extrSelector + "not found") ;
       }
       else {
           return elems.get(one-1) ;
       }

   }

   public static List<WebElement> findBySelectorsPK(List<String> listSelectors , WebDriver driv){
       List<WebElement> listObjects = new ArrayList<>() ;
       for (String ii: listSelectors) {

           listObjects.add(findBySelectorPK(ii, driv)) ;

       }
       return listObjects ;
   }

   public static void sendKeysPK(List<WebElement> listElems , List<String> listKeys){
       for (int i = 0; i < listElems.size(); i++) {
           listElems.get(i).sendKeys(listKeys.get(i));
       }
   }
}
