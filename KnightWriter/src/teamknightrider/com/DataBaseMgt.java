/**
 * 
 */
package teamknightrider.com;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Eric
 *         
 */

public class DataBaseMgt {
    
    private static final String DATABASE_FILE = "//src/Recipes4.9.16.accdb";
    
    static Connection connection;
    static Statement statement;
    static ResultSet result;
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        loadDataBase();
        int example = getRecId(1001);
        
        System.out.println(example);
        
        connection.commit();
        connection.close();
        
    }
    
    /*
     * public DataBaseMgt() { loadDataBase(); }
     */
    
    public static void loadDataBase() throws ClassNotFoundException, SQLException {
        
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection(
                "jdbc:ucanaccess://" + System.getProperty("user.dir") + DATABASE_FILE);
                
        statement = connection.createStatement();
        
        // System.out.println(rs.getString("ing_ID"));
        
    }
    
    public static int getRecId(int recId) throws SQLException {
        int id = recId;
        
        result = statement.executeQuery("SELECT * FROM Recipe WHERE rec_ID = " + id);
        
        while (result.next()) {
            id = result.getInt("rec_ID");
        }
        
        return id;
    }
    
}
