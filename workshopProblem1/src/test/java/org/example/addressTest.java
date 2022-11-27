package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/addressAdditionAndDeletion.feature" , plugin = {"pretty", "html:out/cucumberReport.html"})
public class addressTest {
}
