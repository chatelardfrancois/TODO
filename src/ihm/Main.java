package ihm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:todo_db.sqlite";
        try{
            Connection connection = DriverManager.getConnection(url);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
