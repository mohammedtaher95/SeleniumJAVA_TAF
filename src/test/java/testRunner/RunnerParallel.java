package testRunner;

import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(features = {"src/test/java/features/UserRegistration.feature"//,
        /*"src/test/java/features/Register.feature" */},
        glue = {"stepDefinitions", "coreConfig"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        publish = true)
public class RunnerParallel extends AbstractTestNGCucumberTests {


    public TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Run Cucumber Features", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper){
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        testNGCucumberRunner.finish();
    }
}
