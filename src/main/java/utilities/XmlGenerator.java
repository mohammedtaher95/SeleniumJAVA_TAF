package utilities;

import constants.CrossBrowserMode;
import org.testng.ITestContext;
import org.testng.TestNG;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import tools.properties.DefaultProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class XmlGenerator {

    public static XmlSuite suite;

    public static void xmlSuiteGenerator(XmlSuite newSuite) throws IOException {
        suite = newSuite;
        suite.setName("WebDriver Suite");
        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) == CrossBrowserMode.PARALLEL){
            initializeParallelExecution();
        }

        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) != CrossBrowserMode.PARALLEL){
            initializeNormalExecution();
        }

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
//        TestNG testng = new TestNG();
//        testng.setXmlSuites(suites);

        Path destination = Paths.get(".", "TestNG.xml");
        File newFile = new File("TestNG.xml");
        Files.delete(destination);

        Files.writeString(destination, suite.toXml());
        //testng.setUseDefaultListeners(true);
        //testng.run();
    }

    private static void initializeParallelExecution(){
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(2);

        XmlTest chromeTest = new XmlTest(suite);
        chromeTest.setName("Chrome Test");
        chromeTest.addParameter("browserName", "chrome");
        chromeTest.setThreadCount(1);
        chromeTest.setParallel(XmlSuite.ParallelMode.NONE);
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tests.TestClass"));
        chromeTest.setXmlClasses(classes);

        XmlTest firefoxTest = new XmlTest(suite);
        firefoxTest.setName("Firefox Test");
        firefoxTest.setThreadCount(1);
        firefoxTest.setParallel(XmlSuite.ParallelMode.NONE);
        firefoxTest.addParameter("browserName", "firefox");
        firefoxTest.setXmlClasses(classes);
    }

    private static void initializeNormalExecution(){
        suite.setParallel(XmlSuite.ParallelMode.NONE);
        XmlTest test = new XmlTest(suite);
        test.setName("Test");
        test.addParameter("browserName", DefaultProperties.capabilities.targetBrowserName());
        test.setThreadCount(1);
        test.setParallel(XmlSuite.ParallelMode.NONE);
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tests.TestClass"));
        test.setXmlClasses(classes);
    }

}
