package hw15Task1;

import java.sql.Connection;

public interface DBSQL {
    Connection getConnection();
    void renewAllTables(Connection cn);
    void insertPreparedToUser (Connection cn, int id, String name, String birthday, int loginID, String city, String email, String description);
    void insertBatchToUserRole (Connection cn, int userID, int roleSum);
    void selectFromUser (Connection cn, String name, int loginID);
}
