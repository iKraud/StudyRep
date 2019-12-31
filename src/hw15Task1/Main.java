package hw15Task1;

import java.sql.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        DBPostgreSQL dbPostgreSQL = new DBPostgreSQL();
        Connection cn = dbPostgreSQL.getConnection();
        dbPostgreSQL.renewAllTables(cn);
        dbPostgreSQL.insertPreparedToUser(cn,1,"Adam","2010-02-01",123,"New York","adam@gmail.com","tech");
        dbPostgreSQL.insertPreparedToUser(cn,2,"Brian","2011-09-17",777,"Praha","byw@gmail.com","fin");
        dbPostgreSQL.insertPreparedToUser(cn,3,"Adam","2012-10-23",555,"London","none@gmail.com","lock");

        dbPostgreSQL.insertBatchToUserRole(cn,1, 3);
        dbPostgreSQL.insertBatchToUserRole(cn,2, 2 + 4);

        dbPostgreSQL.selectFromUser(cn, "Adam", 123);
        logger.info("После поиска пользователя...");
        try {
            logger.info("Попытка setAutoCommit = false...");
            cn.setAutoCommit(false);
            logger.info("Попытка создать точку сохранения...");
            Savepoint savepoint = cn.setSavepoint("A");
            logger.info("Создана точка сохранения...");
            dbPostgreSQL.insertBatchToUserRole(cn,1, 7);
            cn.rollback(savepoint);
            logger.info("Выполнен откат к точке сохранения...");
            dbPostgreSQL.insertBatchToUserRole(cn,2, 1);
            cn.commit();
        } catch (SQLException e) {
            logger.error("Ошибка при работе с точкой сохранения!", e);
        }
    }
}