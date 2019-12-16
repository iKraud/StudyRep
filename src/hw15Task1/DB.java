package hw15Task1;

import java.sql.*;

public class DB {
    public Connection connect () throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/InnoStudy",
                "postgres",
                "1");
    }
    public void renewTables () {
        try (Statement statement = connect().createStatement()) {
            statement.execute("drop table if exists public.USER cascade;\n" +
                    "create table public.USER (\n" +
                    "id int primary key, \n" +
                    "name varchar(100) not null, \n" +
                    "birthday date not null, \n" +
                    "login_ID varchar(20) not null, \n" +
                    "city varchar(100) not null, \n" +
                    "email varchar(100) null, \n" +
                    "description varchar(100) null);");

            statement.execute("drop type if exists public.role_name cascade;\n" +
                    "create type role_name as enum ('Administration', 'Clients', 'Billing');\n" +
                    "drop table if exists public.ROLE;\n" +
                    "create table public.ROLE (\n" +
                    "id int primary key, \n" +
                    "name role_name, \n" +
                    "description varchar(100) null);");

            statement.execute("drop table if exists public.USER_ROLE;\n" +
                    "create table public.USER_ROLE (\n" +
                    "id int primary key, \n" +
                    "user_id integer references public.USER(id) not null, \n" +
                    "role_id integer references public.USER_ROLE(id) not null);");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertPrepared () {
        try {
            PreparedStatement preparedStatement = connect()
                    .prepareStatement("insert into public.user values (?, ?, ?, ?, ?, ?, ?");
            preparedStatement.setInt(1, 1); //name
            preparedStatement.setString(2, "Adam"); //name
            preparedStatement.setDate(3, java.sql.Date.valueOf("2010-02-01")); //birthday
            preparedStatement.setInt(4, 123); //login_ID
            preparedStatement.setString(5, "New York"); //city
            preparedStatement.setString(6, "adam@gmail.com"); //email
            preparedStatement.setString(7, "tech"); //description
            preparedStatement.execute();
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}