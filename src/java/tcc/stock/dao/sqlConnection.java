package tcc.stock.dao;

import java.sql.*;
/**
 *
 * @author mt11201
 */
public class sqlConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/stockPRO";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
     //
    Connection conn = null;
    Statement stmt = null;
    //
    String[] parameters;
    
    private void open()throws SQLException{
        this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }
    private void addParameters(String parameter){
        
    }
    private void cleanParameters(){
    }
    private void executeNonQuery(String sqlCommand)throws SQLException{
        stmt = conn.prepareStatement(sqlCommand);
        for(String d : parameters){
            stmt.setString(1,d);
        }
        stmt.execute("");
    }
    private ResultSet executeQuery()throws SQLException{
        
    }
    private void close()throws SQLException{
        this.conn.close();
    }
}
