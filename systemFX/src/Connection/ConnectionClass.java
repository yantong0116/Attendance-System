package Connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
public Connection connection;
    public  Connection getConnection(){

        String dbName="systemfx";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(
            		"jdbc:mysql://127.0.0.1:3306/"+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return connection;
    }
}
