/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_alumnos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

public class Gestion_Alumnos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean terminar = false;
        do{
            switch(Integer.parseInt(JOptionPane.showInputDialog("Elija una opcion: \n 1-Insertar un registro \n"
                                                                +"2-listar los registros \n" +
                                                                "3-eliminar los registros \n" +
                                                                "4-terminar el programa"))){
                case 1: insertar_registro();
                break;

                case 2: listar_registros();
                break;

                case 3: eliminar_registros();
                break;    

                case 4: terminar = true;
                break;
            }
        }while(terminar == true);
        // En el método main pondremos las llamadas a los diferentes métodos que implementaremos a lo largo del ejercicio
        //insertar_registro();
        //listar_registros();
        //eliminar_registros();
    }
    
     public static Connection conexion(){
         Connection conexion = null;
         
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:./sqlite/registro.sqlite");           
            
        
        
        return conexion;
        /* Este método lo utilizaremos para conectarnos a la BBDD.
        Para realizar la conexiión tendremos que tener en cuenta los siguientes puntos:
            - El método devuelve un objeto Connection
            - Deberemos indicar en algún momento la ruta de nuestra BBDD
            - Tendremos que cargar el driver que nos conecte a nuestra BBDD SQLite
            - Es muy importante importar los paquetes que son necesarios para poder instanciar objetos de las clases
              que necesitamos para establecer la conexión con la BBDD
            - Apóyate en la teoría vista en clase para realizar la conexión a BBDD
            - Utiliza los try-catch que consideres necesarios*/
      
    }
    
     public static void desconexion (Connection conn) throws SQLException
    {
        conn.close();
        // Este método nos servirá para desconectarnos de la BBDD
        // Tened en cuenta que el método desconexión recibe por parámetros un objeto de la clase Connection
    } 
     public static void insertar_registro() throws ClassNotFoundException, SQLException
     {
        String DNI, nombre, apellidos, nacionalidad, fecha_naci, tutor, centro;
        int tele, nota_media;
        
        DNI = JOptionPane.showInputDialog("Introduce el DNI");
        nombre = JOptionPane.showInputDialog("Introduce el nombre");
        apellidos = JOptionPane.showInputDialog("Introduce el apellido");
        nacionalidad = JOptionPane.showInputDialog("Introduce la Nacionalidad");
        fecha_naci = JOptionPane.showInputDialog("Introduce la fecha de nacimiento (yyyy-mm-dd)");       
        tutor = JOptionPane.showInputDialog("Introduce el nombre del tutor");
        centro = JOptionPane.showInputDialog("Introduce el nombre del centro");
        tele = Integer.parseInt(JOptionPane.showInputDialog("Introduce el Telefono"));
        nota_media = Integer.parseInt(JOptionPane.showInputDialog("Introduce la nota media"));
        
        Connection conn = conexion();
        Statement st = conn.createStatement();
        st.executeUpdate("insert into Alumnos (DNI, Nombre , Apellidos, Nacionalidad, Fec_Nac, Num_Tel, Tutor, Centro_Proc, Nota_media)"
                 + "values('"+ DNI +"', '"+ nombre +"' , '"+ apellidos +"', '"+ nacionalidad +"', (date('"+ fecha_naci +"')), "+ tele +", '"+ tutor +"', '"+ centro+ "', "+ nota_media +")");
        
        st.close(); /
        conn.close();
         /* En este método insertaremos los registros en nuestra BBDD.
            Para ello, además de indicarle los parámetros del registro a insertar, tendremos que realizar lo siguiente:
                - Abrir una conexión con la BBDD
                - Preparar la sentencia de insert
                - Ejecutar la sentencia
                - Cerrar la sentencia
                - Cerrar la conexión a BBDD
         
         */
         
     }
     public static void listar_registros() throws ClassNotFoundException, SQLException
     {
        Connection conn = conexion();
        Statement sentencia = conn.createStatement();
        ResultSet resultado = sentencia.executeQuery ("SELECT * FROM Alumnos"); 
        
        
        
        /* En este método listaremos los registros de la BBDD
         Para ello tendremos que:
            - Abrir una conexión a BBDD
            - Preparar una sentencia de consulta
            - Procesar los resultados que recojamos de dicha consulta
            - Cerrar la sentencia
            - Cerrar la conexión
       */
     }
     public static void eliminar_registros() throws ClassNotFoundException, SQLException
     { 
        String DNI = showInputDialog(null, "Introduca el DNI que desa borrar").toUpperCase();         
        Connection conn = conexion();
        Statement sentencia = conn.createStatement();  
        sentencia.execute ("DELETE FROM Alumnos WHERE DNI = '"+ DNI +"'");
       
        sentencia.close(); 
         /* Utilizaremos este métodos para eliminar un registro.
         Para ello:
            - Abrir una conexión a BBDD
            - Preparar una sentencia para eliminar uno o varios registros
            - Procesar los resultados que recojamos de dicha consulta
            - Cerrar la sentencia
            - Cerrar la conexión
       */
     }
}
