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


}
