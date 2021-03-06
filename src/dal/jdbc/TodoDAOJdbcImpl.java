package dal.jdbc;

import bo.Todo;
import dal.DALException;
import dal.TodoDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDAOJdbcImpl implements TodoDAO {
    Connection connection = null;
    public final String INSERT = "INSERT INTO todo (date, texte, reussi) values (?, ?, ?);";
    public final String SELECT_ALL = "SELECT * FROM todo;";
    public final String UPDATE_REUSSI = "UPDATE todo SET reussi=? WHERE id=?;";
    public final String UPDATE_TODO = "UPDATE todo SET texte=?, reussi=? WHERE id=?;";
    public final String DELETE_TODO = "DELETE FROM todo WHERE id=?;";

    @Override
    public void insert(Todo todo) throws DALException {
        try {
            connection = JdbcTools.getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT);
            stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(2, todo.getTexte());
            stmt.setDate(3, null);
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

                liste.add(new Todo(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getString("texte"), rs.getDate("reussi")==null?null:rs.getDate("reussi").toLocalDate()));
            }
            connection.close();
            return liste;
        } catch (SQLException e) {
            throw  new DALException(e.getMessage());
        }
    }

    @Override
    public void updateReussi(Todo todo) throws DALException {
        try{
            connection = JdbcTools.getConnection();
            PreparedStatement stmt = connection.prepareStatement(UPDATE_REUSSI);
            stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setInt(2, todo.getId());
            stmt.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            throw  new DALException(e.getMessage());
        }
    }

    @Override
    public void updateTexteTodo(String texte, Todo todo) throws DALException {
        try {
            connection = JdbcTools.getConnection();
            PreparedStatement stmt = connection.prepareStatement(UPDATE_TODO);
            stmt.setString(1, texte);
            stmt.setDate(2,null);
            stmt.setInt(3, todo.getId());
            stmt.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void deleteTodo(Todo todo) throws DALException {
        try {
            connection = JdbcTools.getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_TODO);
            stmt.setInt(1, todo.getId());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }
}
