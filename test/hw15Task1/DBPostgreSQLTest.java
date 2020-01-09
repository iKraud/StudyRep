package hw15Task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


class DBPostgreSQLTest {
    private DBSQL dbSQL;
    private Connection connection;
    private User user;
    @Mock
    private Statement statement;
    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    void init () {
        MockitoAnnotations.initMocks(this);
        connection = Mockito.mock(Connection.class);
        dbSQL = Mockito.spy(DBPostgreSQL.class);
    }

    @Test
    void getConnection() {
    }

    @Test
    void renewAllTables() {
    }

    @Test
    void insertPreparedToUser() throws SQLException {
//        Mockito.when(dbSQL.getConnection()).thenReturn(connection); //либо #1?
        Mockito.doReturn(preparedStatement).when(connection).prepareStatement(DBPostgreSQL.INSERT_PREPARED_TO_USER); //либо #2?

        int id = 1;
        String name = "Adam";
        String birthday = "2010-02-01";
        int loginID = 123;
        String city = "New York";
        String email = "adam@gmail.com";
        String description = "tech";

        dbSQL.insertPreparedToUser(connection, user);

        Mockito.verify(connection, Mockito.times(1)).prepareStatement(DBPostgreSQL.INSERT_PREPARED_TO_USER);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, user.getId());
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, user.getName());
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, user.getBirthday());
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(4, user.getLoginID());
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(5, user.getCity());
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, user.getEmail());
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(7, user.getDescription());
        Mockito.verify(preparedStatement, Mockito.times(1)).execute();
    }

    @Test
    void insertBatchToUserRole() {
    }

    @Test
    void selectFromUser() {
    }
}