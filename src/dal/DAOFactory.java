package dal;

import dal.jdbc.TodoDAOJdbcImpl;

public class DAOFactory {
    public static TodoDAO getTodoDAO() {
        return new TodoDAOJdbcImpl();
    }
}
