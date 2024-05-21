package DB;

import model.Product;
import model.Size;
import model.Topping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTopping extends AbsDao<Topping>{
    @Override
    public boolean insert(Topping topping) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Topping topping) {
        return false;
    }

    @Override
    public List<Topping> selectAll() throws SQLException {
        List<Topping> toppings = (List<Topping>) JDBIConnector.getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from topping").mapToBean(Topping.class).list();
        });
        return toppings;
    }

    @Override
    public boolean update(Topping topping) {
        return false;
    }
    public Topping getToppingByID(String idtopping) throws SQLException {
        Topping topping = JDBIConnector.getJdbi().withHandle((handle -> {
            return handle.createQuery("select * from topping where idtopping = ?").bind(0, idtopping).mapToBean(Topping.class).findFirst().orElse(null);
        }));
        return topping;
    }
    public static void main(String[] args) throws SQLException {
        Product product1 = new DAOProduct().getProductByID("product1");
        String[] names = {"topping1", "topping2"};
       List<Topping> topping = new ArrayList<>();
       for (String name : names) {
           Topping topping1 = new DAOTopping().getToppingByID(name);
           topping.add(topping1);
       }
       for(Topping topping1 : topping) {
           product1.setPrice(product1.getPrice()+topping1.getPricetopping());
           System.out.println(topping1.getIdtopping());

       }
        System.out.println(new DAOTopping().selectAll());
    }

}
