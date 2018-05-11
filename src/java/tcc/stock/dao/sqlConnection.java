package tcc.stock.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
    
/**
 *
 * @author mt11201
 */
public class sqlConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//    static final String DB_URL = "jdbc:mysql://localhost:3307/tccstock";
    static final String DB_URL = "jdbc:mysql://localhost:3306/tcc";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
//    static final String PASS = "usbw";

    private Connection conn = null;
    private PreparedStatement stmt = null;
    //
    private List<String> parameters = new ArrayList<>();
    
    public sqlConnection(){

    }
            
    public void openConnection()throws SQLException{
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            this.conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

            if(conn != null){
                System.out.println("Conexão esta aberta!");
            }
            else{
                System.out.println("não foi estabelecido a conexão!");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
  
    public void addParameters(String parameter){
        parameters.add(parameter);
    }
    public void cleanParameters(){
        parameters.clear();
    }
    public void executeNonQuery(String sqlCommand)throws SQLException{
        try {
            stmt = conn.prepareStatement(sqlCommand);
            
            for(String d : parameters){
                stmt.setString(parameters.indexOf(d)+1, d);
            }
            
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao executar comando SQL: " + e.getMessage());
        }
    }
    public ResultSet executeQuery(String sqlCommand)throws SQLException{
        try {            
            stmt = conn.prepareStatement(sqlCommand);

            for(String d : parameters){
                int i = (parameters.indexOf(d)+1);
                
                stmt.setString(i, d);
                System.out.println(d);
            }
//            stmt.setString(1, "Paulo");
//            stmt.setString(2, "123");
//            
            ResultSet rs =  stmt.executeQuery();

            return rs;
        }catch(Exception e){
            System.out.println("Erro ao executar comando SQL: " + e.getMessage());
            return null;
        }
    }
    public void closeConnection()throws SQLException{
        this.conn.close();
    }
}
