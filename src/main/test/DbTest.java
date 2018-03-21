import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTest {
    @Test
    public void connectTest() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection("jdbc:mysql://localhost:3306/community","root","root");
    }
}
