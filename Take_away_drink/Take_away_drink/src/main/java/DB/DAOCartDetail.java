package DB;

import model.CartDetail;

import java.sql.SQLException;
import java.util.List;

public class DAOCartDetail extends AbsDao<CartDetail>{
    @Override
    public boolean insert(CartDetail cartDetail) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(CartDetail cartDetail) {
        return false;
    }

    @Override
    public List<CartDetail> selectAll() throws SQLException {
        return List.of();
    }


    @Override
    public boolean update(CartDetail cartDetail) {
        return false;
    }
}
