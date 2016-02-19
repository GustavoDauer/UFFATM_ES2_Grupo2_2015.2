package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gustavo
 */
public class Conexao {
        
    public static Connection conectar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BD_ES2?zeroDateTimeBehavior=convertToNull", "root", "admin");
        return con;
    }
}
