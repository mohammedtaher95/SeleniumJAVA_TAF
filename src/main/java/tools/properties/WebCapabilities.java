package tools.properties;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.*;
import org.aeonbits.owner.Config.Sources;


@LoadPolicy(LoadType.MERGE)
@Sources({
          "file:src/main/resources/properties/WebCapabilities.properties",
          "classpath:src/main/resources/properties/WebCapabilities.properties"})

public interface WebCapabilities extends Config, Accessible {

    @Key("TARGET_BROWSER_NAME")
    @DefaultValue("chrome")
    String targetBrowserName();

    @Key("BASE_URL")
    @DefaultValue("")
    String baseURL();

    @Key("EXECUTION_METHOD")
    @DefaultValue("normal")
    String executionMethod();

//    @Override
//    default void setProperty(String key, String value) {
//        Properties updatedProps = new java.util.Properties();
//        updatedProps.setProperty(key, value);
//        Properties.ExecutionPlatform = ConfigFactory.create(ExecutionPlatform.class, updatedProps);
//        // temporarily set the system property to support hybrid read/write mode
//        System.setProperty(key, value);
//        ReportManager.logDiscrete("Setting \"" + key + "\" property with \"" + value + "\".");
//    }

}
