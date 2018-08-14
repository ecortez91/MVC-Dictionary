/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Sniper
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    
    //Definición de atributos
    private Connection conn;
    private Statement  sta;
    private final String jdbc;
    private final String ruta;
    private final String usuario;
    private final String clave;

    public Conexion() 
    {
        this.conn=null;
        this.sta=null;
        this.jdbc="com.mysql.jdbc.Driver";
        this.ruta="jdbc:mysql://localhost:3306/cr103813_parcial2";
        this.usuario="root";
        this.clave=""; 
    }
    
    public void AbrirConexion()
    {
        try {
            Class.forName(this.jdbc);
            this.conn=DriverManager.getConnection(this.ruta,this.usuario,this.clave);
            this.sta=this.conn.createStatement();
        } catch (SQLException e) {
           e.printStackTrace();
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
    }
    
    public String ejecutar(String sentencia)
    {
        String mensaje="Operación exitosa!";
        try
        {
          this.AbrirConexion();
          this.sta.executeUpdate(sentencia);
        }catch(SQLException e)
        {
            mensaje=e.getMessage();
        }
        
        return mensaje;
    }
    
    public ResultSet consultar(String sentencia)
    {
      ResultSet resultado=null;
      try
      {
          this.AbrirConexion();
          resultado=this.sta.executeQuery(sentencia);     
      }catch(SQLException e)
      {
        e.printStackTrace();
      }
      return resultado;
    }
}
