package hw15Task1;

import java.sql.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DBSQLite {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:Inno.db";
            connection = DriverManager.getConnection(url);
            logger.info("Соединение создано...");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Ошибка при создании соединения!", e);
        }
        return connection;
    }
    public void renewAllTables() {
        try (Connection cn = getConnection();
             Statement statement = cn.createStatement()) {
            statement.execute("drop table if exists USER;");
            statement.execute("create table USER ( \n" +
                    "id integer primary key not null, \n" +
                    "name text null, \n" +
                    "birthday text null, \n" +
                    "login_ID integer null, \n" +
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
            logger.info("Исходные таблицы обновлены...");
        } catch (SQLException e) {
            logger.error("Ошибка при обновлении исходных таблиц!", e);
        }

    }

    public void insertPreparedToUser (int id, String name, String birthday, int loginID, String city, String email, String description) {
        try (Connection cn = getConnection();
             PreparedStatement preparedStatement = cn
                .prepareStatement("insert into USER values (?,?,?,?,?,?,?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, birthday);
            preparedStatement.setInt(4, loginID);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, description);
            preparedStatement.execute();
            logger.info("Использован Prepared Statement...");
        } catch (SQLException e) {
            logger.error("Ошибка при использовании Prepared Statement!", e);
        }
    }

    public void insertBatchToUserRole (int userID, int roleSum) {
        ResultSet resultSet;
        try (Connection cn = getConnection();
             Statement statement = cn.createStatement();
             PreparedStatement preparedStatement = cn.prepareStatement(
                     "insert into USER_ROLE (user_id, role_id) values (" + userID + ", ?);" )) {
            int colCount = statement.executeQuery("select count(*) from ROLE").getInt(1);
            for (int i = 1; i <= colCount; i++) {
                resultSet = statement.executeQuery("select description from ROLE where id =" + i);
                resultSet.next();
                int roleBit = resultSet.getInt(1);
                if ((roleSum & roleBit) != 0) {
                    preparedStatement.setInt(1, i);
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
            cn.close();
            logger.info("Использован Batch...");
        } catch (SQLException e) {
            logger.error("Ошибка при использовании Batch!", e);
        }
    }

    public void selectFromUser (String name, int loginID) {
        try (Connection cn = getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("select * from USER where name=? and login_ID=?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, loginID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Столбец " + resultSet.getMetaData().getColumnName(i) + " = " + resultSet.getString(i));
            }
            cn.close();
            logger.info("Выполнен поиск пользователя");
        } catch (SQLException e) {
            logger.error("Ошибка при поиске пользователя", e); //e.getStackTrace()[1].getClassName(),
        }
    }
}
