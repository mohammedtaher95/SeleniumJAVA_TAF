package utilities;

import java.io.*;

public class AllureBatchGenerator {

    public static void generateBatFile() throws IOException {
        File file = new File("generateAllureReport.bat");
        FileOutputStream outputStream = new FileOutputStream(file);

        DataOutputStream dOut = new DataOutputStream(outputStream);

        dOut.writeBytes("@echo off\n");
        dOut.writeBytes("allure serve target/allure-results\n");
        dOut.writeBytes("pause\n");
        dOut.writeBytes("exit");

        dOut.close();
        outputStream.close();
    }
}
