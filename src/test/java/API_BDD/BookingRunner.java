package API_BDD;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

public class BookingRunner {

    @CucumberOptions(features = "src/test/java/API_BDD/CreateBooking.feature",
            glue = {"API_BDD"}, monochrome = true,
            plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
    public class TestRunner extends AbstractTestNGCucumberTests {
    }
}
