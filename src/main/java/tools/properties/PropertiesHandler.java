package tools.properties;

import org.aeonbits.owner.ConfigFactory;

import java.io.*;
import java.util.Properties;

import static tools.properties.DefaultProperties.platformPath;
import static tools.properties.DefaultProperties.webCapPath;

public class PropertiesHandler {

    public static Properties properties;
    protected static FileInputStream inputStream;
    protected static FileOutputStream outputStream;

    public static Properties readPropertyFile(String filePath) throws IOException {

        File propFile = new File(filePath);
        inputStream = new FileInputStream(propFile);
        properties = new Properties();
        properties.load(inputStream);

        return properties;
    }

    public static void initializeProperties() throws IOException {

        DefaultProperties.platform = ConfigFactory.create(ExecutionPlatform.class);
        DefaultProperties.capabilities = ConfigFactory.create(WebCapabilities.class);

        generateDefaultProperties();
    }

    public static void generateDefaultProperties() throws IOException {

        File platformProperties = new File(platformPath);
        File capProperties = new File(webCapPath);


        if(!platformProperties.exists()){
            outputStream = new FileOutputStream(platformPath);
            DefaultProperties.platform.store(outputStream, "#######################################################"
                    + "\n" + "########## TAF Execution Platform Properties ###########"
            + "\n" + "########################################################");
        }

        if(!capProperties.exists()){
            outputStream = new FileOutputStream(webCapPath);
            DefaultProperties.capabilities.store(outputStream, "######################################################"
                    + "\n" + "################ TAF Web Capabilities #################"
                    + "\n" + "#######################################################");
        }
    }

}
