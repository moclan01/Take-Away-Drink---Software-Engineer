package DB;

import java.sql.SQLException;
import java.util.List;

public abstract class AbsDao<T> {
    public abstract  boolean insert(T t) throws SQLException;
    public abstract boolean delete(T t);
    public abstract List<T> selectAll() throws SQLException;
    public abstract boolean update(T t);


}
