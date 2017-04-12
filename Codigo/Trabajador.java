package BD;

	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import Clases.*;
	import ExcepcionesCustom.*;

public class TrabajadorBD {
    private GenericoBD bd;
    
    public TrabajadorBD(){
        bd = new GenericoBD();
    }
    
    public void alta(Trabajador t)throws Exception{
   	String consulta="insert into trabajadores values(?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps=bd.abrirConexion().prepareStatement(consulta);
        ps.setString(1,t.getDNI());
        ps.setString(2,t.getFNAC());
        ps.setFloat(3,t.getSalario());
        ps.setString(4,t.getNombre());
        ps.setString(5,t.getApellido1());
        ps.setString(6,t.getApellido2());
        ps.setString(7,t.getDireccion());
        ps.setString(8,t.getTelefono_Personal());
        ps.setString(9,t.getTelefono_Empresa());
        ps.setInt(10,t.getCentro().getCentroNO());
        ps.setInt(11,t.getCategoria());
        int n = ps.executeUpdate();
        bd.cerrarConexion();
        
    }
    
    public void baja(String dni)throws Exception{
        String consulta="delete from Trabajador where DNI = ?;";
        PreparedStatement ps=bd.abrirConexion().prepareStatement(consulta);
        ps.setString(1,dni);
        int n = ps.executeUpdate();
        bd.cerrarConexion();
        if (n == 0)
            throw new TrabajadorNoEncontradoException(1);
        
    }
    
    public void modificar(Trabajador trabajadorAnt, Trabajador trabajadorNue)throws Exception{
        String consulta="update Trabajador set ,FECHA_NAC = ?,SALARIO = ?,NOMBRE = ?,APELLIDO1 = ?,APELLIDO2 = ?,DIRECC=?,TELEFONO_PER = ?,TELEFONO_EMP = ?,CATEGORIA = ? where DNI = ?;";
        PreparedStatement ps=bd.abrirConexion().prepareStatement(consulta);
        ps.setString(1,trabajadorNue.getFNAC());
        ps.setFloat(2,trabajadorNue.getSalario());
        ps.setString(3,trabajadorNue.getNombre());
        ps.setString(4,trabajadorNue.getApellido1());
        ps.setString(5,trabajadorNue.getApellido2());
        ps.setString(6,trabajadorNue.getDireccion());
        ps.setString(7,trabajadorNue.getTelefono_Personal());
        ps.setString(8,trabajadorNue.getTelefono_Empresa());
        ps.setInt(9,trabajadorNue.getCategoria());
        ps.setString(10,trabajadorAnt.getDNI());
        int n = ps.executeUpdate();
        bd.cerrarConexion();
        if (n == 0)
            throw new TrabajadorNoEncontradoException(2);
        
    }
}
    
     
    
    
    
    
   
    
    