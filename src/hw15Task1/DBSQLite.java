package hw15Task1;

import java.sql.*;

public class DBSQLite {
    public Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:Inno.db";
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void renewAllTables(Connection cn) {
        try (Statement statement = cn.createStatement()) {
            statement.execute("drop table if exists USER;");
            statement.execute("create table USER ( \n" +
                    "id integer primary key not null, \n" +
                    "name text null, \n" +
                    "birthday text null, \n" +
                    "login_ID text null, \n" +
                    "city text null, \n" +
                    "email text null, \n" +
                    "description text null);");

            statement.execute("drop table if exists ROLE;");
            statement.execute("create table ROLE ( \n" +
                    "id integer primary key not null, \n" +
                    "name text not null, \n" +
                    "description integer not null);");
            statement.execute("insert into ROLE values (1, 'Administration', 1), \n" +
                    "(2, 'Clients', 2), \n" +
                    "(3, 'Billing', 4);");

            statement.execute("drop table if exists USER_ROLE;");
            statement.execute("create table USER_ROLE ( \n" +
                    "id integer primary key autoincrement, \n" +
                    "user_id integer references USER(id) not null, \n" +
                    "role_id integer references ROLE(id) not null);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPreparedToUser (Connection cn, int id, String name, String birthday, int loginID, String city, String email, String description) {
        try (PreparedStatement preparedStatement = cn
                .prepareStatement("insert into USER values (?,?,?,?,?,?,?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, birthday);
            preparedStatement.setInt(4, loginID);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, description);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBatchToUserRole (Connection cn, int userID, int roleSum) {
        ResultSet resultSet;
        try (Statement statement = cn.createStatement();
            PreparedStatement preparedStatement = cn.prepareStatement("insert into USER_ROLE (user_id, role_id) values (" + userID + ", ?);" )) {
            int colCount = statement.executeQuery("select count(*) from ROLE").getInt(1);
            for (int i = 1; i <= colCount; i++) {
                resultSet = statement.executeQuery("select description from ROLE where id =" + i);
                resultSet.next();
                int roleBit = resultSet.getInt(1);
                if ((roleSum & roleBit) != 0) {
                    preparedStatement.setInt(1, roleBit);
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}