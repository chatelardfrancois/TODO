package dal;

import bo.Todo;

import java.util.List;

public interface TodoDAO {
    void insert(Todo todo) throws DALException;
    List<Todo> selectAll() throws DALException;
    void updateReussi(Todo todo) throws DALException;
    void updateTexteTodo(String texte, Todo todo) throws DALException;
    void deleteTodo(Todo todo) throws DALException;
}
