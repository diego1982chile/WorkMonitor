package my.workmonitor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
    
    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";    
 
    public static void connect() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        Class.forName(driver).newInstance();
         
        try {
            // connect method #3 - network client driver
            
            String dbURL = "jdbc:derby:trabajo";
            Properties properties = new Properties();
            //properties.put("shutdown", "true");
            //properties.put("create", "true");
            properties.put("user", "sigges");
            properties.put("password", "sigges");            
             
            Connection conn = DriverManager.getConnection(dbURL, properties);                        
            
            if (conn != null) {
                //System.out.println("Connected to database");                
                /*
                ResultSet rs=conn.prepareStatement("select * from app.hh").executeQuery();
                try{
                   while(rs.next())
                       System.out.println(rs.getString(1) + " " + rs.getString(2)); // Exception Here
               }
               catch(SQLException error){
                   System.err.println("Unable to query for getUserName.");
                   error.printStackTrace(System.err);
                   System.exit(0);
               }
               */
            }
        } catch (SQLException ex) {
            ex.printStackTrace();                        
        }
    }
}