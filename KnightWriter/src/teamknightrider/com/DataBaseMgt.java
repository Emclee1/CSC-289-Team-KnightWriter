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
    
    private static final String DATABASE_FILE = "//Database/Recipes4.9.16.accdb";
    
    static Connection connection;
    static Statement statement;
    static ResultSet result;
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        int recId = 1086;
        
        loadDataBase();
        
        System.out.println(getInstructions(1000));
        
        connection.commit();
        connection.close();
        
    }
    
    public DataBaseMgt(){
        try {
            loadDataBase();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void loadDataBase() throws ClassNotFoundException, SQLException {
        
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection = DriverManager.getConnection(
                "jdbc:ucanaccess:" + DATABASE_FILE);
                
        statement = connection.createStatement();
        
        // System.out.println(rs.getString("ing_ID"));
        
    }
    
    /**
     * @param recId
     * @return
     */
    public static int getRecId(int recId) {

      
        return recId;
    }
    
    /**
     * @param recId
     * @return
     * @throws SQLException
     */
    public static String getRecipe(int recId) {
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
    
    public static String getCusine(int recId) {
        String cuisine = null;
        try {
            cuisine = getResult(recId, "cuisine");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cuisine;
    }
    
    /**
     * @param recId
     * @return
     */
    public static String getInstructions(int recId) {
        StringBuilder instructions = new StringBuilder();
        int replaceIndex = 0;
        int instructionNumber=1;
        try {
            instructions.append(getResult(recId, "instructions"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        
        while (replaceIndex >= 0)
        {
            replaceIndex = instructions.indexOf("<div>");
            if(replaceIndex >= 0)
            instructions.replace(replaceIndex, replaceIndex+5 , instructionNumber + ".");
            
            replaceIndex = instructions.indexOf("</div>");
            if(replaceIndex >=0)
            instructions.replace(replaceIndex, replaceIndex+6 , "");
            
            
            replaceIndex = instructions.indexOf("<div>&nbsp;</div>");
            if(replaceIndex >=0)
            instructions.replace(replaceIndex, replaceIndex+21 , "");
            
            
            
            instructionNumber = instructionNumber+1;
        }
            
        
        
        return instructions.toString();
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
    
    public static ArrayList<String> getIngredients(int recId) {
        
        ResultSet result = null;
        ResultSet ingredientResult = null;
        ResultSet ing_lineResult = null;
        
        ArrayList<String> ingredientString = new ArrayList<String>();
        ArrayList<Integer> ingredientID = new ArrayList<Integer>();
        
        // while (result.next()) {
        // id = result.getInt("rec_ID");
        // }
        try {
            // result = statement.executeQuery("SELECT * FROM Recipe WHERE
            // rec_ID = " + id);
            result = statement.executeQuery("SELECT * FROM ing_Line WHERE rec_ID = " + recId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Execute Query Error");
        }
        
        try {
            while (result.next()) {
                ingredientID.add(result.getInt("ing_ID"));
                
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        for (int index = 0; index < ingredientID.size(); index++) {
            try {
                ing_lineResult = statement.executeQuery(
                        "SELECT * FROM ing_Line WHERE (rec_ID = " + recId + " AND ing_ID = " + ingredientID.get(index) + ")");
                ingredientResult = statement.executeQuery(
                        "SELECT * FROM Ingredient WHERE ing_ID = " + ingredientID.get(index));
                while (ingredientResult.next() && ing_lineResult.next()) {
                    ingredientString.add(ing_lineResult.getString("line_quantity") 
                            + " "
                            + ing_lineResult.getString("line_unit") + " "
                            + ingredientResult.getString("ing_name"));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return ingredientString;
        
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
