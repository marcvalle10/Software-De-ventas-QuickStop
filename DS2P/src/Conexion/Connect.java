package Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    Connection conn =null;
    public Connection conectar(){
        try{
            //Class.forName("org.gjt.mm.mysql.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3305/conexion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root1101");
        if (conn!=null){
            System.out.println("Conexion Correcta");
        }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion fallida: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
            return conn;
    }
    }
