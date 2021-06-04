package dal.jdbc;

import java.util.Properties;

public class Settings {
    private static Properties propriete;
    static{
        try{
            propriete = new Properties();
            propriete.load(Settings.class.getResourceAsStream("settings.properties"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getProperty(String cle){
        return propriete.getProperty(cle, null);
    }
}
