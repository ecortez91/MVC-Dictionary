/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author Sniper
 */
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.Conexion;

public class Palabra {

    //identificador de cada palabra
    private int idpalabra;
    //palabra a guardar
    private String palabra;
    //definición de la palabra a guardar
    private String definicion;
    //ejemplos utilizando dicha palabra
    private String ejemplos;
    //string que almacena la query para realizar un insert
    private String sqlInsert = "insert into palabra(idpalabra,palabra,definicion,ejemplos) values ";
    //string que almacena la query para realizar un update
    private String sqlUpdate = "update palabra set ";
    //string que almacena la query para realizar una selección por id
    private String sqlSelectId="select * from palabra where idpalabra=";
    //string que almacena la query para realizar un select con condiciones 
    private String sqlSelect="select * from palabra where ";
    //string que almacena la query para eliminar una palabra mediante su id
    private String sqlDelete="delete from palabra where idpalabra=";

    //Constructor que recibe múltiples parámetros
    public Palabra(int idpalabra, String palabra, String definicion, String ejemplos) {
        this.idpalabra = idpalabra;
        this.palabra = palabra;
        this.definicion = definicion;
        this.ejemplos = ejemplos;
    }

    //Constructor que recibe un parámetro
    public Palabra(int idpalabra) {
        this.idpalabra = idpalabra;
    }

    //Constructor que no recibe parametros
    public Palabra() {
    }
    
    
    //Método para agregar palabras al diccionario
    public String Agregar()
    {
      String mensaje;
      this.sqlInsert+="("+this.idpalabra+",'"+this.palabra+"','"+this.definicion+"','"+this.ejemplos+"')";
      Conexion conn = new Conexion();
      mensaje =conn.ejecutar(this.sqlInsert);
      return mensaje;
    }
    
    //Método para actualizar palabras del diccionario
    public String Actualizar()
    {
      String mensaje;
      this.sqlUpdate+="palabra='"+this.palabra+"', definicion='"+this.definicion+"', ejemplos='"+this.ejemplos+"' where idpalabra="+this.idpalabra;
      Conexion conn = new Conexion();
      mensaje =conn.ejecutar(this.sqlUpdate);
      return mensaje;
    }
    
    //Método para eliminar palabras del diccionario
    public String Eliminar()
    {
      String mensaje;
      this.sqlDelete+=this.idpalabra;
      Conexion conn = new Conexion();
      mensaje =conn.ejecutar(this.sqlDelete);
      return mensaje;
    }
    
    
    //Método que permite consultar y buscar palabras del diccionario
    public String[][] Buscar(String filtro)
    {
      String[][] datos=null;
      try
      {
          ResultSet resultado = null;
          if(!filtro.equals("")) {
              this.sqlSelect+="palabra like '%"+filtro+"%'";
          } else {
              this.sqlSelect ="select * from palabra";
          }
          Conexion conn = new Conexion();
          resultado = conn.consultar(this.sqlSelect);
          resultado.last(); 
          datos = new String[resultado.getRow()][4];
          resultado.beforeFirst();
          int i =0;
          while(resultado.next())
          {
           datos[i][0]=resultado.getString("idpalabra");
           datos[i][1]=resultado.getString("palabra");
           datos[i][2]=resultado.getString("definicion");
           datos[i][3]=resultado.getString("ejemplos");
           i++;
          }
      }catch(SQLException e)
      {
        e.printStackTrace();
      }
      return datos;
    }
}
