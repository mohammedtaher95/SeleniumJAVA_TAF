package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {

    public static Properties properties;
    protected static FileInputStream inputStream;

    public static Properties readPropertyFile(String FilePath) throws IOException {

        File propFile = new File(FilePath);
        inputStream = new FileInputStream(propFile);
        properties = new Properties();
        properties.load(inputStream);

        return properties;
    }
}
