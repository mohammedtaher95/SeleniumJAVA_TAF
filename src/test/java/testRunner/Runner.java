//package testRunner;
//
//import coreConfig.Config;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
//
//import java.io.IOException;
//
//@CucumberOptions(features = {"src/test/java/features/UserRegistration.feature"//,
//        /*"src/test/java/features/Register.feature" */},
//        glue = {"stepDefinitions", "coreConfig"},
//        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
//        publish = true)
//public class Runner extends AbstractTestNGCucumberTests {
//
//    private final Config config = new Config();
//
//    public Runner() throws IOException {
//    }
//
//    @BeforeTest
//    @Parameters(value = {"browserName"})
//    public void setUp(@Optional("chrome") String browserName) throws IOException {
//        config.setUp(browserName);
//    }
//}
