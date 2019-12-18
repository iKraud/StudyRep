package hw15Task1;

import java.sql.*;
import java.util.Random;

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
    public void renewAllTables() {
        try (Statement statement = connect().createStatement()) {
            statement.execute("drop table if exists USER;");
            statement.execute("create table USER ( \n" +
                    "id integer primary key not null, \n" +
                    "name text null, \n" +
                    "birthday text null, \n" +
                    "login_ID text null, \n" +
                    "city text null, \n" +
                    "email text null, \n" +
                    "description text null);");

            statement.execute("drop table if exists roleName;");
            statement.execute("create table roleName ( \n" +
                    "id integer primary key not null, \n" +
                    "name text not null);");
            statement.execute("insert into roleName values (1, 'Administration'), \n" +
                    "(2, 'Clients'), \n" +
                    "(3, 'Billing');");

            statement.execute("drop table if exists ROLE;");
            statement.execute("create table ROLE ( \n" +
                    "id integer primary key not null, \n" +
                    "name text references roleName(name), \n" +
                    "description text null);");

            statement.execute("drop table if exists USER_ROLE;");
            statement.execute("create table USER_ROLE ( \n" +
                    "id integer primary key, \n" +
                    "user_id integer references USER(id) not null, \n" +
                    "role_id integer references ROLE(id) not null);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPreparedToUser (int id, String name, String birthday, int loginID, String city, String email, String description) {
        try (PreparedStatement preparedStatement = connect()
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

    public void insertBatchToRole (int amountOfBatch) {
        Random rnd = new Random();
        ResultSet resultSet;
        String strRes = null;
        try (PreparedStatement preparedStatement = connect()
                .prepareStatement("insert into ROLE values (?,?,?)")) {
            for (int i = 1; i <= amountOfBatch; i++) {
                try (Statement statement = connect().createStatement()) {
                    resultSet = statement.executeQuery("select name from roleName where id = " + (rnd.nextInt(3) + 1));
                    strRes = resultSet.getString("name");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, strRes);
                preparedStatement.setString(3,"default");
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
