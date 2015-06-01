package my.workmonitor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static org.eclipse.persistence.config.TargetDatabase.Derby;
 
/**
 * This program demonstrates how to connect to Apache Derby (Java DB) database
 * for the embedded driver and network client driver.
 * @author www.codejava.net
 *
 */
public class JdbcDerbyConnection {
 
    public static void connect() {
         
        try {
            // connect method #3 - network client driver
            String dbURL = "jdbc:derby:trabajo";
            Properties properties = new Properties();
            properties.put("create", "true");
            properties.put("user", "sigges");
            properties.put("password", "sigges");            
             
            Connection conn = DriverManager.getConnection(dbURL, properties);
            
            if (conn != null) {
                System.out.println("Connected to database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}