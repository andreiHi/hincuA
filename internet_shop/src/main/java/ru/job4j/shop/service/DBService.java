package ru.job4j.shop.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.shop.model.Product;
import ru.job4j.shop.model.Purchase;
import ru.job4j.shop.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class DBService {
    private Properties sqlQuery;
    private BasicDataSource dataSource;
    private static final Logger LOG = LogManager.getLogger(DBService.class);
    private static final String FILE = "settings.properties";
    private static final String SQL_FILE = "sql.properties";

    private DBService() {
        init();
        createTables();
    }

    public boolean checkLogin(String login) {
        boolean exist = true;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("SELECT_LOGIN"))
        ) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exist = false;
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return exist;
    }

    public boolean addNewUser(User user) {
        boolean add = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("ADD_NEW_USER"))) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, "user");
            add = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return add;
    }

    public boolean checkUser(String login, String password) {
        boolean exist = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("GET_USER_BY_PASS_AND_LOGIN"))
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public void addProductToCart(String login, String idProduct) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("SELECT_FUNCTION_ADD_OR_UPDATE"))
        ) {
            ps.setInt(2, Integer.valueOf(idProduct));
            ps.setString(1, login);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String basketAmount(String login) {
        String result = "";
        int count = 0;
        int summa = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("SELECT_COUNT_OF_PRODUCTS_AND_SUMMA"))) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    count = rs.getInt("count");
                    summa = rs.getInt("summa");
                }
            }
            if (count > 0) {
                result = String.format("%d тов. на сумму %d руб", count, summa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Purchase> getUserCart(String login) {
        List<Purchase>list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("SELECT_USER_CART"))
        ){
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Purchase purchase = new Purchase();
                    purchase.setId(rs.getInt("cart_id"));
                    purchase.setDescription(rs.getString("minidescription"));
                    purchase.setTitle(rs.getString("product_name"));
                    purchase.setProductId(rs.getInt("product_id"));
                    purchase.setPrice(rs.getInt("price"));
                    purchase.setQuantity(rs.getInt("count"));
                    list.add(purchase);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) {
            list = null;
        }
        return list;
    }

    public void deleteUserCart(String login) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("DELETE_USER_CART"))
        ) {
            System.out.println(login);
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductFromCart(String idCart) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("DELETE_PRODUCT_FROM_USER_CART"));
            ps.setInt(1, Integer.valueOf(idCart));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementCountOfProducts(String idCart) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("ADD_INCREMENT_PRODUCT_TO_CART"))
        ) {
            ps.setInt(1, Integer.valueOf(idCart));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dicrementCountOfProducts(String idCart) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("ADD_DISCREMENT_PRODUCT_TO_CART"))
        ) {
            ps.setInt(1, Integer.valueOf(idCart));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCountOfProductsInCart(String idCart, Integer count) {
        try (Connection connection = dataSource.getConnection()) {
            if (count == 0 || count < 0) {
                try (PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("DELETE_PRODUCT_FROM_USER_CART"))) {
                    ps.setInt(1, Integer.valueOf(idCart));
                    ps.executeUpdate();
                }
            } else {
                try ( PreparedStatement pst = connection.prepareStatement(sqlQuery.getProperty("SET_COUNT_PRODUCT_BY_ID"))) {
                    pst.setInt(1, Integer.valueOf(count));
                    pst.setInt(2, Integer.valueOf(idCart));
                    pst.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String translationInToSold(String login) {
        String result = "";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("SELECT_CART"))) {
                ps.setString(1, login);
                try (ResultSet rs = ps.executeQuery()) {
                    try {
                        while (rs.next()) {
                            int cartCount = rs.getInt("cart_count");
                            int productCount = rs.getInt("product_count");
                            int resultCount = productCount - cartCount;
                            if (resultCount < 0) {
                                throw new RuntimeException();
                            } else {
                                int idProduct = rs.getInt("id_product");
                                int idCart = rs.getInt("id_cart");
                                try (PreparedStatement ps1 = connection.prepareStatement(sqlQuery.getProperty("UPDATE_COUNT_OF_PRODUCTS"));
                                PreparedStatement ps2 =connection.prepareStatement(sqlQuery.getProperty("UPDATE_CART_STATUS"))
                                ){
                                    ps1.setInt(1, resultCount);
                                    ps1.setInt(2, idProduct);
                                    ps1.executeUpdate();
                                    ps2.setInt(1, idCart);
                                    ps2.executeUpdate();
                                }
                            }
                        }
                        connection.commit();
                        result = "ok";
                    } catch (RuntimeException e) {
                        result = String.format("%s недостаточно на складе.", rs.getString("title"));
                        connection.rollback();
                    }
                }
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public Product getProduct(String id) {
    Product product = new Product();
    try (Connection connection = dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.getProperty("GET_PRODUCT"))
    ) {
        preparedStatement.setInt(1, Integer.valueOf(id));
        try (ResultSet re = preparedStatement.executeQuery()) {
            while (re.next()) {
                product.setId(re.getInt("id"));
                product.setName(re.getString("name"));
                product.setMiniDescription(re.getString("minidescription"));
                product.setDescription(re.getString("description"));
                product.setPrice(re.getInt("price"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return product;
    }

    public boolean checkAdmin(String logon, String password) {
        boolean exist = false;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("SELECT_ADMIN"))
        ) {
            ps.setString(1, logon);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(exist);
        return exist;
    }

    public void deleteProduct(String id) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("CHECK_IF_PAID"))
        ) {
            ps.setInt(1, Integer.valueOf(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    try (PreparedStatement pst = connection.prepareStatement(sqlQuery.getProperty("SET_PRODUCT_NOT_VISIBLE"))) {
                        pst.setInt(1, Integer.valueOf(id));
                        pst.executeUpdate();
                    }
                } else {
                    try (PreparedStatement pst = connection.prepareStatement(sqlQuery.getProperty("DELETE_PRODUCT_FROM_CART"))) {
                        pst.setInt(1, Integer.valueOf(id));
                        pst.executeUpdate();
                    }
                    try (PreparedStatement pst = connection.prepareStatement(sqlQuery.getProperty("DELETE_PRODUCT_FROM_PRODUCT"))) {
                        pst.setInt(1, Integer.valueOf(id));
                        pst.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class DBSer {
        private static final DBService INSTANCE = new DBService();
    }
    public static DBService getInstance() {
        return DBSer.INSTANCE;
    }
    private void init() {
        this.sqlQuery = new Properties();
        Properties pr = new Properties();
        try (InputStream isDb = getClass().getClassLoader().getResourceAsStream(FILE);
             InputStream isSql = getClass().getClassLoader().getResourceAsStream(SQL_FILE)
        ) {
            pr.load(isDb);
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName(pr.getProperty("db.class"));
            this.dataSource.setUrl(pr.getProperty("db.url"));
            this.dataSource.setUsername(pr.getProperty("db.user"));
            this.dataSource.setPassword(pr.getProperty("db.password"));
            this.dataSource.setMinIdle(100);
            this.dataSource.setMaxIdle(1000);
            this.sqlQuery.load(isSql);
            LOG.debug("Server start.");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    private void createTables() {
        try (Connection connection = this.dataSource.getConnection();
             Statement st = connection.createStatement()
        ) {
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_STATUS"));
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_PRODUCTS"));
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_ROLE"));
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_USERS"));
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_CART"));
            st.executeUpdate(sqlQuery.getProperty("FUNCTION_ADD_OR_UPDATE"));
            try (ResultSet rs = st.executeQuery(sqlQuery.getProperty("SELECT_ALL_FROM_USERS"))) {
                if (!rs.next()) {
                    st.executeUpdate(sqlQuery.getProperty("INSERT_ROLE"));
                    st.executeUpdate(sqlQuery.getProperty("INSERT_ROOT_USER"));
                }
            }
            try (Statement psr = connection.createStatement();
                 ResultSet resultSet = psr.executeQuery(sqlQuery.getProperty("SELECT_STATUS"))
            ){
                if (!resultSet.next()) {
                    psr.executeUpdate(sqlQuery.getProperty("INSERT_STATUS"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts(String sort) {
        List<Product> products = new ArrayList<>();
        try {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty(sort));
                 ResultSet rs = ps.executeQuery()
            ) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setMiniDescription(rs.getString("minidescription"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getInt("price"));
                    product.setAmount(rs.getInt("amount"));
                    product.setViews(rs.getInt("views"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return products;
    }
    public void close() {
        try {
            this.dataSource.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
