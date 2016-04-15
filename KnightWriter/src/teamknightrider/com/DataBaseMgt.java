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
import java.util.ArrayList;

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
        int recId = 1001;
        
        loadDataBase();
        
        getIngredients(1000);
        
        System.out.println("" + getRecId(recId) + " " + getRecName(recId) + " "
                + getDifficulty(recId) + " " + getPrepTime(recId) + " " + getCookTime(recId) + " "
                + getTotalTime(recId) + " " + getServings(recId) + " " + getPersonalRating(recId)
                + " " + getCourse(recId) + " " + getInstructions(recId) + " "
                + getContributor(recId) + " " + getSource(recId));
                
        connection.commit();
        connection.close();
        
    }
    
    /**
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void loadDataBase() throws ClassNotFoundException, SQLException {
        
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection(
                "jdbc:ucanaccess://" + System.getProperty("user.dir") + DATABASE_FILE);
                
        statement = connection.createStatement();
        
        // System.out.println(rs.getString("ing_ID"));
        
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getRecId(int recId) {
        String id = null;
        
        try {
            id = getResult(recId, "rec_ID");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return id;
    }
    
    /**
     * @param recId
     * @return
     * @throws SQLException
     */
    public static String getRecName(int recId) {
        String recName = null;
        
        try {
            recName = getResult(recId, "rec_name");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return recName;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getDifficulty(int recId) {
        String difficulty = null;
        try {
            difficulty = getResult(recId, "difficulty");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return difficulty;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getPrepTime(int recId) {
        String prepTime = null;
        try {
            prepTime = getResult(recId, "prep_time");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return prepTime;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getCookTime(int recId) {
        String cookTime = null;
        try {
            cookTime = getResult(recId, "COOK_time");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return cookTime;
        
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getTotalTime(int recId) {
        String totalTime = null;
        try {
            totalTime = getResult(recId, "total_time");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return totalTime;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getServings(int recId) {
        String servings = null;
        try {
            servings = getResult(recId, "servings");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return servings;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getPersonalRating(int recId) {
        String personalRating = null;
        try {
            personalRating = getResult(recId, "personal_rating");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return personalRating;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getCourse(int recId) {
        String course = null;
        try {
            course = getResult(recId, "course");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return course;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getInstructions(int recId) {
        String instructions = null;
        try {
            instructions = getResult(recId, "instructions");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return instructions;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getContributor(int recId) {
        String contributor = null;
        try {
            contributor = getResult(recId, "contributor");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return contributor;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getSource(int recId) {
        String source = null;
        try {
            source = getResult(recId, "source");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return source;
    }
    
    
    
    
    public static void getIngredients(int recId) throws SQLException
    {
    ArrayList ingredients = null;
    
    try {
        String statement1 = String.format("SELECT * FROM Ing_Line WHERE rec_ID = %d", 1001 ) ;//Creates sql statement
        ResultSet ingList = statement.executeQuery( statement1 );//Will return every associated ingredient row

        
        
        
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    while (result.next()) {
        System.out.println(result.getString("ine_unit"));
    }
    
    //return ingredients;
    }
    
    /* 
    *
    *
    *
    *
    */
    public static String getResult(int recId, String column) throws SQLException {
        String resultString = null;
        ResultSet result = null;
        
        try {
            // result = statement.executeQuery("SELECT * FROM Recipe WHERE
            // rec_ID = " + id);
            result = statement.executeQuery("SELECT * FROM Recipe WHERE rec_ID = " + recId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Execute Query Error");
        }
        
        // while (result.next()) {
        // id = result.getInt("rec_ID");
        // }
        try {
            // result = statement.executeQuery("SELECT * FROM Recipe WHERE
            // rec_ID = " + id);
            result = statement.executeQuery("SELECT * FROM Recipe WHERE rec_ID = " + recId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Execute Query Error");
        }
        
        while (result.next()) {
            resultString = result.getString(column);
        }
        return resultString;
    }
}
