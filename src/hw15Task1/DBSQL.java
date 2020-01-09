package hw15Task1;

import java.sql.Connection;
import java.util.Collection;

public interface DBSQL {
    Connection getConnection();
    void renewAllTables(Connection cn);
//    void insertPreparedToUser (Connection cn, int id, String name, String birthday, int loginID, String city, String email, String description);
    void insertPreparedToUser (Connection cn, User user);
    void insertBatchToUserRole (Connection cn, int userID, int roleSum);
    void selectFromUser (Connection cn, String name, int loginID);
    Collection<User> getAllUsers (Connection cn);
}
