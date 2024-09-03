
package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/studentsdb";
    private String user = "root";
    private String password = "";
    
    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected!");
            
        } catch (Exception e) {
            System.out.println("No connection established!");
        }
    } 
    
    public Connection getConnection(){
        return connection;
    }
}
