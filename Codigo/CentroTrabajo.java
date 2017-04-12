
package BD;

	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import Clases.*;
	import ExcepcionesCustom.*;

public class CentroTrabajoBD 
{
    private GenericoBD bd;
    
    public CentroTrabajoBD(){
        bd = new GenericoBD();
    }
    
    public void alta(CentroTrabajo ct)throws Exception{
        String consulta="insert into Centro_de_trabajo values(?,?,?,?,?,?,?)";
        PreparedStatement ps=bd.abrirConexion().prepareStatement(consulta);
        ps.setInt(1,ct.getCentroNO());
        ps.setString(2,ct.getNombre());
        ps.setString(3,ct.getDireccion());
        ps.setString(4,ct.getTelefono());
        ps.setString(5,ct.getCodigo_Postal());
        ps.setString(6,ct.getCiudad());
        ps.setString(7,ct.getProvincia());
        int n = ps.executeUpdate();
        bd.cerrarConexion();
        
    }
    
    public void baja(int CENTRO_NO)throws Exception{
        String consulta="delete from Centro_de_trabajo where CENTRO_NO = ?;";
        PreparedStatement ps=bd.abrirConexion().prepareStatement(consulta);
        ps.setString(1,String.valueOf(CENTRO_NO));
        int n = ps.executeUpdate();
        bd.cerrarConexion();
        if (n == 0)
            throw new CentroNoEncontradoException(1);
        
    }
    
    public void modificar(CentroTrabajo c)throws Exception{
        String consulta="update Centro_de_trabajo set NOMBRE  = ?,CALLE  = ?,TELEFONO  = ?,COD_POSTAL = ?,CIUDAD  = ?,PROVINCIA =? where CENTRO_NO = ?;";
        PreparedStatement ps=bd.abrirConexion().prepareStatement(consulta);
        ps.setString(1,c.getNombre());
        ps.setString(2,c.getDireccion());
        ps.setString(3,c.getTelefono());
        ps.setString(4,c.getCodigo_Postal());
        ps.setString(5,c.getCiudad());
        ps.setString(6,c.getProvincia());
        ps.setInt(7,c.getCentroNO());
    
        int n = ps.executeUpdate();
        bd.cerrarConexion();
        if (n == 0)
            throw new TrabajadorNoEncontradoException(2);
        
    }
}
