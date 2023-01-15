package tools.properties;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.*;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({"file:src/main/resources/properties/ExecutionPlatform.properties",
        "classpath:src/main/resources/properties/ExecutionPlatform.properties"})
public interface ExecutionPlatform extends Config, Accessible {

    @Key("ENV_TYPE")
    @DefaultValue("LOCAL")
    String EnvironmentType();

    @Key("CROSS_BROWSER_MODE")
    @DefaultValue("OFF")
    String CrossBrowserMode();

    @Key("REMOTE_ENV_URL")
    @DefaultValue("")
    String RemoteURL();


}
