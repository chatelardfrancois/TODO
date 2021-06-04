package dal.jdbc;

import bo.Todo;
import dal.DALException;
import dal.TodoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDAOJdbcImpl implements TodoDAO {
    Connection connection = null;
    public final String INSERT = "INSERT INTO todo (date, texte) values (CURRENT_DATE, ?);";
    public final String SELECT_ALL = "SELECT * FROM todo;";
    @Override
    public void insert(Todo todo) throws DALException {
        try {
            connection = JdbcTools.getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT);
            stmt.setString(1, todo.getTexte());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public List<Todo> selectAll() throws DALException {
        try {
            connection = JdbcTools.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = stmt.executeQuery();
            List<Todo> liste= new ArrayList<>();
            while (rs.next()){
                //Convertion de l'integer en bdd en localdate
                int year = rs.getInt("Date") / 10000;
                int monthDay = rs.getInt("Date") % 10000;
                int month = monthDay / 100;
                int day = monthDay % 100;
                liste.add(new Todo(rs.getInt("id"), LocalDate.of(year, month, day), rs.getString("texte")));
            }
            connection.close();
            return liste;
        } catch (SQLException e) {
            throw  new DALException(e.getMessage());
        }
    }
}
