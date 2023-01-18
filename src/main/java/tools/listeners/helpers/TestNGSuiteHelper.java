package tools.listeners.helpers;

import constants.CrossBrowserMode;
import org.apache.commons.io.FileUtils;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import tools.properties.DefaultProperties;
import utilities.Classesloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class TestNGSuiteHelper {

    public static XmlSuite testSuite;
    public static XmlTest test;

    public static void SuiteGenerator(XmlSuite suite) throws IOException {
        testSuite = suite;
        test = testSuite.getTests().get(0);

        testSuite.setName("WebDriver Suite");
        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) == CrossBrowserMode.OFF){
            initializeNormalExecution();
        }

        else {
            initializeCrossBrowserSuite();
        }

        Path destination = Paths.get(".", "TestNG.xml");
        File newFile = new File("TestNG.xml");

        try {
            if(newFile.exists()){
                Files.delete(destination);
            }
            else{
                FileUtils.forceMkdir(newFile);
            }
            Files.writeString(destination, testSuite.toXml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeCrossBrowserSuite(){

        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) == CrossBrowserMode.PARALLEL){
            testSuite.setParallel(XmlSuite.ParallelMode.TESTS);
        }

        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) == CrossBrowserMode.SEQUENTIAL){
            testSuite.setParallel(XmlSuite.ParallelMode.NONE);
        }

        testSuite.setThreadCount(2);

        XmlTest chromeTest = test;
        chromeTest.setName("Chrome Test");
        chromeTest.addParameter("browserName", "chrome");
        chromeTest.setThreadCount(1);
        chromeTest.setParallel(XmlSuite.ParallelMode.NONE);
        List<XmlClass> classes = new ArrayList<>();

        if(DefaultProperties.platform.runAllTests()){
            Set<Class> newSet = Classesloader.findAllClassesUsingReflectionsLibrary("tests");
            for (Class aClass : newSet) {
                classes.add(new XmlClass(String.valueOf(aClass).replaceFirst("class ", "")));
            }
        }
        else {
            classes.add(new XmlClass(test.getClasses().get(0).getName()));
        }        chromeTest.setXmlClasses(classes);

        XmlTest firefoxTest = new XmlTest(testSuite);
        firefoxTest.setName("Firefox Test");
        firefoxTest.setThreadCount(1);
        firefoxTest.setParallel(XmlSuite.ParallelMode.NONE);
        firefoxTest.addParameter("browserName", "firefox");
        firefoxTest.setXmlClasses(classes);
    }

    private static void initializeNormalExecution(){
        testSuite.setParallel(XmlSuite.ParallelMode.NONE);
        test.setName("Test");
        test.addParameter("browserName", DefaultProperties.capabilities.targetBrowserName());
        test.setThreadCount(1);
        test.setParallel(XmlSuite.ParallelMode.NONE);
        List<XmlClass> classes = new ArrayList<>();

        if(DefaultProperties.platform.runAllTests()){
            Set<Class> newSet = Classesloader.findAllClassesUsingReflectionsLibrary("tests");
            for (Class aClass : newSet) {
                classes.add(new XmlClass(String.valueOf(aClass).replaceFirst("class ", "")));
            }
        }
        else {
            classes.add(new XmlClass(test.getClasses().get(0).getName()));
        }
        test.setXmlClasses(classes);
    }

}
