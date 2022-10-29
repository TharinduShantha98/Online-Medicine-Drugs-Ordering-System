package bo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Crud<T, ID> extends  SuperBo {
    boolean add(T t) throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    T search(ID id) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
}
