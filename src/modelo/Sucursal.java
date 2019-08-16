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
}
