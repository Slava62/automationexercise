package ru.slava62.automationexercise.util;


import lombok.experimental.UtilityClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class ConfigUtils {
    Properties prop = new Properties();
    private static InputStream configFile;

    static {
        try {
            configFile = new FileInputStream("src/test/resources/application.properties");
            prop.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getBaseUrl() {return prop.getProperty("url");}
    public String getStand() {return prop.getProperty("stand");}

    public void setEMail(String eMail){prop.setProperty("email", eMail);}
    public void setPassword(String password){prop.setProperty("password", password);}

    public String getEMail(){return prop.getProperty("email");}
    public String getPassword(){return prop.getProperty("password");}

}
