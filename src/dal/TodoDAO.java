package dal;

import bo.Todo;

import java.util.List;

public interface TodoDAO {
    void insert(Todo todo) throws DALException;
    List<Todo> selectAll() throws DALException;
    void updateReussi(Todo todo) throws DALException;
}
