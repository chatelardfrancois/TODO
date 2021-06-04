package bll;

import bo.Todo;
import dal.DALException;
import dal.DAOFactory;
import dal.TodoDAO;

import java.sql.SQLException;
import java.util.List;

public class TodoManager {
    private static TodoManager instance;
    private static final TodoDAO daoTodo = DAOFactory.getTodoDAO();

    private TodoManager() {
    }

    public static TodoManager getInstance() {
        if (instance == null) {
            instance = new TodoManager();
        }
        return instance;
    }

    public List<Todo> getAllTodo() throws BLLException {
        List<Todo> articles = null;
        try {
            articles = daoTodo.selectAll();
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
        return articles;
    }

    public void addTodo(String texte) throws BLLException {
        try {
            Todo todo = new Todo(texte);
            daoTodo.insert(todo);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
    }

    public void updateTodoReussi(Todo todo) throws BLLException {
        try {
            daoTodo.updateReussi(todo);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
    }




}
