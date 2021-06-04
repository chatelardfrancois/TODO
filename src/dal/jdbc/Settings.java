package dal.jdbc;

import java.util.Properties;

public class Settings {
    private static Properties properties;
    static{
        try{
            properties = new Properties();
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getProperty(String cle){
        return properties.getProperty(cle, null);
    }
}
