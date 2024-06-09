package database;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Quan Bai and Weihua Li
 * ****************************************************************** @Note
Database code from class
 */
public final class MySQLDatabase {

    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    //You need to start the Java DB, 
    //The database will be created at C:\Users\YOUR_ID\.netbeans-derby
    //If you right click Java DB and create a database manually, the location will be the same
    //The database cannot be deleted unless the Java DB is stopped.
    //If you only observe "other schemas", you need to find USER_NAME from it, and set it as default schema
    //If the database does not appear in IDE after creating through codes, please restart Netbeans (this is a bug) 

//    private static final String URL = "jdbc:derby://localhost:1527/CarDB;create=true";
    //Embedded database: 
    //You do NOT need to start the javaDB, the database will be created at the root of the project folder
    private static final String URL = "jdbc:derby:CardCollectionDB;create=true";  //derby.jar

    Connection conn;

    public MySQLDatabase() {
        establishConnection();
    }



    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                //System.out.println(URL + "   CONNECTED....");

            } catch (SQLException ex) {
                Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet myQuery(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void myUpdate(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
