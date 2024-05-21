package DB;

import model.Account;
import model.Type;

import java.sql.SQLException;
import java.util.List;

public class DAOType extends AbsDao<Type>{
    @Override
    public boolean delete(Type type) {
        return false;
    }

    @Override
    public boolean insert(Type type) throws SQLException {
        return false;
    }

    @Override
    public List<Type> selectAll() throws SQLException {
        List<Type> types = (List<Type>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from type").mapToBean(Type.class).list();
        });
        return types;
    }

    @Override
    public boolean update(Type type) {
        return false;
    }
    public Type getTypeByID(String idtype) throws SQLException {
        Type type = JDBIConnector.getJdbi().withHandle((handle -> {
            return handle.createQuery("select * from type where idtype = ?").bind(0, idtype).mapToBean(Type.class).findFirst().orElse(null);
        }));
        return type;
    }

    public String getNameTypeByIDProduct(String idproduct) throws SQLException {
        String idtype = JDBIConnector.getJdbi().withHandle((handle -> {
            return handle.createQuery("select idtype from product where idproduct = ?").bind(0, idproduct).mapTo(String.class).findFirst().orElse(null);
        }));
        return idtype;
    }
    public static void main(String[] args) throws SQLException {

        System.out.println(new DAOType().getTypeByID("type1"));
        for(Type type : new DAOType().selectAll()) {
            System.out.println(type.getidtype());
        }
    }
}
