package ihm;

import bll.BLLException;
import bll.TodoManager;
import dal.TodoDAO;
import dal.jdbc.TodoDAOJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        TodoManager tm = TodoManager.getInstance();
        try {
            System.out.println(tm.getAllTodo());
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }
}
