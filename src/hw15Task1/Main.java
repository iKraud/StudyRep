package hw15Task1;

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
        dbsqLite.renewAllTables();
        dbsqLite.insertPreparedToUser(1,"Adam","2010-02-01",123,"New York","adam@gmail.com","tech");
        dbsqLite.insertBatchToRole(5);
    }
}
