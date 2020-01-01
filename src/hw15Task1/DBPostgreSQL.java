package hw15Task1;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBPostgreSQL implements DBSQL {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private Connection connection = null;
    private Statement statement;
    private PreparedStatement preparedStatement;
    public static final String INSERT_PREPARED_TO_USER = "insert into public.USER values (?,?,?,?,?,?,?)";

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/InnoStudy";
            connection = DriverManager.getConnection(url, "postgres", "1");
            logger.info("Соединение создано...");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Ошибка при создании соединения!", e);
        }
        return connection;
    }
    public void renewAllTables(Connection cn) {
        try {
            statement = cn.createStatement();
            statement.execute("drop table if exists public.USER cascade;");
            statement.execute("create table public.USER ( \n" +
                    "id integer primary key not null, \n" +
                    "name text null, \n" +
                    "birthday text null, \n" +
                    "login_ID integer null, \n" +
                    "city text null, \n" +
                    "email text null, \n" +
                    "description text null);");

            statement.execute("drop table if exists public.ROLE cascade;");
            statement.execute("create table public.ROLE ( \n" +
                    "id integer primary key not null, \n" +
                    "name text not null, \n" +
                    "description integer not null);");
            statement.execute("insert into public.ROLE values (1, 'Administration', 1), \n" +
                    "(2, 'Clients', 2), \n" +
                    "(3, 'Billing', 4);");

            statement.execute("drop table if exists public.USER_ROLE;");
            statement.execute("create table public.USER_ROLE ( \n" +
                    "id serial primary key, \n" +
                    "user_id integer references public.USER(id) not null, \n" +
                    "role_id integer references public.ROLE(id) not null);");
            logger.info("Исходные таблицы обновлены...");
        } catch (SQLException e) {
            logger.error("Ошибка при обновлении исходных таблиц!", e);
        }
    }

    public void insertPreparedToUser (Connection cn, int id, String name, String birthday, int loginID, String city, String email, String description) {
        try {
            preparedStatement = cn.prepareStatement(INSERT_PREPARED_TO_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, birthday);
            preparedStatement.setInt(4, loginID);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, description);
            preparedStatement.execute();
            preparedStatement.close();
            logger.info("Prepared Statement выполнен...");
        } catch (SQLException e) {
            logger.error("Ошибка при использовании Prepared Statement!", e);
        }
    }

    public void insertBatchToUserRole (Connection cn, int userID, int roleSum) {
        ResultSet resultSet;
        try {
            statement = cn.createStatement();
            preparedStatement = cn.prepareStatement("insert into public.USER_ROLE (user_id, role_id) values (" + userID + ", ?);" );
            resultSet = statement.executeQuery("select count(*) from public.ROLE");
            resultSet.next();
            int colCount = resultSet.getInt(1);
            for (int i = 1; i <= colCount; i++) {
                resultSet = statement.executeQuery("select description from public.ROLE where id =" + i);
                resultSet.next();
                int roleBit = resultSet.getInt(1);
                if ((roleSum & roleBit) != 0) {
                    preparedStatement.setInt(1, i);
                    logger.info("Попытка добавить statement в batch...");
                    preparedStatement.addBatch();
                    logger.info("После добавления statement в batch...");
                }
            }
            preparedStatement.executeBatch();
            resultSet.close();
            preparedStatement.close();
            logger.info("Batch выполнен...");
        } catch (SQLException e) {
            logger.error("Ошибка при использовании Batch!", e);
        }
    }

    public void selectFromUser (Connection cn, String name, int loginID) {
        try {
            preparedStatement = cn.prepareStatement("select * from public.USER where name=? and login_ID=?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, loginID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Столбец " + resultSet.getMetaData().getColumnName(i) + " = " + resultSet.getString(i));
            }
            resultSet.close();
            preparedStatement.close();
            logger.info("Выполнен поиск пользователя");
        } catch (SQLException e) {
            logger.error("Ошибка при поиске пользователя", e); //e.getStackTrace()[1].getClassName(),
        }
    }
}