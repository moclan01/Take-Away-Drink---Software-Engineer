package DB;

import model.Account;
import model.Product;
import model.Type;
import org.jdbi.v3.core.Handle;

import java.sql.SQLException;
import java.util.List;

public class DAOProduct extends AbsDao<Product>{
    @Override
    public boolean insert(Product product) throws SQLException {
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("insert into product(idproduct,idtype,name,price,`describe`,srcIMG) values(?,?,?,?,?,?)")
                        .bind(0, product.getIdproduct())
                        .bind(1, product.getType().getidtype())
                        .bind(2, product.getName())
                        .bind(3, product.getPrice())
                        .bind(4, product.getDescribe())
                        .bind(5, product.getSrcIMG())
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean deleteByIdProduct(String idproduct){
        try {
            JDBIConnector.getJdbi().useHandle(handle -> {
                handle.createUpdate("delete from product where idproduct = ?")
                        .bind(0, idproduct)
                        .execute();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public List<Product> selectAll() throws SQLException {
        List<Product> products;
        try (Handle handle = JDBIConnector.getJdbi().open()) {
            String sql = "SELECT * FROM product";
            products = handle.createQuery(sql)
                    .mapToBean(Product.class)
                    .list();

            if (products != null && !products.isEmpty()) {
                DAOType daoType = new DAOType();
                for (Product product : products) {
                    String nameType = daoType.getNameTypeByIDProduct(product.getIdproduct());
                    Type type = daoType.getTypeByID(nameType);
                    product.setType(type);
                }
            }

            return products;
        }
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    public Product getProductByID(String idproduct) throws SQLException {
        Product product =null;
        try (Handle handle = JDBIConnector.getJdbi().open()) {
            String sql = "SELECT * FROM product WHERE idproduct = ?";
            product = handle.createQuery(sql)
                    .bind(0, idproduct)
                    .mapToBean(Product.class)
                    .findFirst()
                    .orElse(null);

            if (product != null) {
                DAOType daoType = new DAOType();
                String nameType = daoType.getNameTypeByIDProduct(product.getIdproduct());
                Type type = daoType.getTypeByID(nameType);
                product.setType(type);
            }

            return product;
        }
    }

    public List<Product> getTop3(String idtype) throws SQLException {
        List<Product> products;
        try (Handle handle = JDBIConnector.getJdbi().open()) {
            String sql = "SELECT * FROM product WHERE idtype = ? ORDER BY price DESC LIMIT 3";
            products = handle.createQuery(sql)
                    .bind(0, idtype)
                    .mapToBean(Product.class)
                    .list();

            if (products != null && !products.isEmpty()) {
                DAOType daoType = new DAOType();
                for (Product product : products) {
                    String nameType = daoType.getNameTypeByIDProduct(product.getIdproduct());
                    Type type = daoType.getTypeByID(nameType);
                    product.setType(type);
                }
            }

            return products;
        }
    }
    public List<Product> getBottom3(String str) throws SQLException {
        List<Product> products;
        try (Handle handle = JDBIConnector.getJdbi().open()) {
            String sql = "SELECT * FROM product where idtype = ? ORDER BY price ASC LIMIT 3";
            products = handle.createQuery(sql).bind(0, str)
                    .mapToBean(Product.class)
                    .list();

            if (products != null && !products.isEmpty()) {
                DAOType daoType = new DAOType();
                for (Product product : products) {
                    String nameType = daoType.getNameTypeByIDProduct(product.getIdproduct());
                    Type type = daoType.getTypeByID(nameType);
                    product.setType(type);
                }
            }

            return products;
        }
    }

    public static void main(String[] args) throws SQLException {

        Product arr = new DAOProduct().getProductByID("product1");
        System.out.println(arr);
        arr.setPrice(arr.getPrice()+new DAOSize().getSizeByID("l").getPrice());
        System.out.println(arr);
    }


}
