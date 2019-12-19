package hw15Task1;

import java.sql.*;

/**
 * @author "Timohin Igor"
 * 1)    Спроектировать базу
 *
 * - Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 * - Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 * - Таблица USER_ROLE содержит поля id, user_id, role_id
 *
 * Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
 *
 * 2) Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 * a) Используя параметризированный запрос
 * b) Используя batch процесс
 * 3) Сделать параметризированную выборку по login_ID и name одновременно
 * 4) Перевести connection в ручное управление транзакциями
 * a) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql операциями установить логическую точку сохранения(SAVEPOINT)
 * б) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql операциями установить точку сохранения (SAVEPOINT A),
 * намеренно ввести некорректные данные на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
 */
public class Main {
    public static void main(String[] args) {
        DBSQLite dbsqLite = new DBSQLite();
        Connection cn = dbsqLite.connect();
        dbsqLite.renewAllTables(cn);
        dbsqLite.insertPreparedToUser(cn,1,"Adam","2010-02-01",123,"New York","adam@gmail.com","tech");
        dbsqLite.insertPreparedToUser(cn,2,"Brian","2011-09-17",777,"Praha","byw@gmail.com","fin");
        dbsqLite.insertPreparedToUser(cn,3,"Adam","2012-10-23",555,"London","none@gmail.com","lock");

        dbsqLite.insertBatchToUserRole(cn,1, 3);
        dbsqLite.insertBatchToUserRole(cn,2, 2 + 4);
        dbsqLite.selectFromUser(cn, 123, "Adam");
        try {
            cn.setAutoCommit(false);
            Savepoint savepoint = cn.setSavepoint("A");
            dbsqLite.insertBatchToUserRole(cn,1, 7);
            cn.rollback(savepoint);
            dbsqLite.insertBatchToUserRole(cn,2, 1);
            cn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
