package dal.jdbc;

import bo.Todo;
import dal.DALException;
import dal.TodoDAO;
import org.sqlite.date.DateParser;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDAOJdbcImpl implements TodoDAO {
    Connection connection = null;
    public final String INSERT = "INSERT INTO todo (date, texte) values (strftime('%s', CURRENT_DATE), ?);";
    public final String SELECT_ALL = "SELECT id, DATETIME((date), 'unixepoch') AS date, texte FROM todo;";
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

                liste.add(new Todo(rs.getInt("id"), rs.getDate("Date").toLocalDate(), rs.getString("texte")));
            }
            connection.close();
            return liste;
        } catch (SQLException e) {
            throw  new DALException(e.getMessage());
        }
    }
}
