package tools.properties;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({"file:ExecutionPlatform.properties", "classpath:ExecutionPlatform.properties"})
public interface ExecutionPlatform extends Config, Accessible {

    @Key("ENV_TYPE")
    @DefaultValue("LOCAL")
    String EnvironmentType();

    @Key("CROSS_BROWSER_MODE")
    @DefaultValue("off")
    String CrossBrowserMode();

    @Key("REMOTE_ENV_URL")
    @DefaultValue("")
    String RemoteURL();


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
