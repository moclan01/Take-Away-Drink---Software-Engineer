package DB;

import model.Account;
import model.Size;

import java.sql.SQLException;
import java.util.List;

public class DAOSize extends AbsDao<Size>{
    @Override
    public boolean insert(Size size) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Size size) {
        return false;
    }

    @Override
    public List<Size> selectAll() throws SQLException {
        List<Size> sizes = (List<Size>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from size").mapToBean(Size.class).list();
        });
        return sizes;
    }

    @Override
    public boolean update(Size size) {
        return false;
    }
    public Size getSizeByID(String idsize) throws SQLException {
        Size size = JDBIConnector.getJdbi().withHandle((handle -> {
            return handle.createQuery("select * from size where idsize = ?").bind(0, idsize).mapToBean(Size.class).findFirst().orElse(null);
        }));
        return size;
    }


    public static void main(String[] args) throws SQLException {
        List<Size> sizes = new DAOSize().selectAll();
        for (Size size : sizes) {
            System.out.println(size.getPrice());
        }
        Size size = new DAOSize().getSizeByID("l");
        System.out.println(size);
    }
}
