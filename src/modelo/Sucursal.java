package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Sucursal {

    private int id;
    private String nombre;
    private String descripcion;

    public Sucursal() {
    }

    public Sucursal(int id) {
        this.id = id;
    }

    public Sucursal(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Sucursal> consultarXNombre(){
        String SELECT = "SELECT * FROM sucursal WHERE nombre ='"+this.nombre+"'" ;
        return consultasAll(SELECT);
    }

    public List<Sucursal> consultarSucursales(){
        String SELECT = "SELECT * FROM sucursal";
        return consultasAll(SELECT);
    }

    public boolean consultar(){
        String SELECT = "SELECT * FROM `sucursal` WHERE `id`= "+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.nombre = rs.getString(2);
                    this.descripcion = rs.getString(3);
                    oConexion.desconectar();
                    return true;
                }else{
                    oConexion.desconectar();
                    return false;
                }
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    private List<Sucursal> consultasAll(String SELECT){
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                List<Sucursal> sucursales = new ArrayList<Sucursal>();
                while (rs.next()){
                    Sucursal sucursal = new Sucursal(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3)
                    );
                    sucursales.add(sucursal);
                }
                oConexion.desconectar();
                return sucursales;
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean insertar(){
        String INSERT = "INSERT INTO `sucursal`(`nombre`, `descripcion`) VALUES ('"+this.nombre+"','"+this.descripcion+"')";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(INSERT);
        return oConexion.ejecutarSentencia();
    }

    public boolean actualizar(){
        String UPDATE= "UPDATE `sucursal` SET `nombre`= '"+this.nombre+"',`descripcion`= '"+this.descripcion+"' WHERE `id`= " + this.id ;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(UPDATE);
        return oConexion.ejecutarSentencia();
    }

    public boolean eliminar(){
        String DELETE= "DELETE FROM sucursal WHERE `id`="+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(DELETE);
        return oConexion.ejecutarSentencia();
    }
}
