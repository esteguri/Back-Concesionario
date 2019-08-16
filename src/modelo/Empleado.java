package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Empleado {

    private int id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String fecha_ingreso;
    private int id_sucursal;

    public Empleado() {
    }

    public Empleado(int id) {
        this.id = id;
    }

    public Empleado(int id, String nombre, String apellido, String cedula, String fecha_ingreso, int id_sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fecha_ingreso = fecha_ingreso;
        this.id_sucursal = id_sucursal;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public List<Empleado> consultarXIdSucursal(){
        String SELECT = "SELECT * FROM empleado WHERE id_sucursal ="+this.id_sucursal ;
        return consultasAll(SELECT);
    }

    public List<Empleado> consultarXFechaIngreso(){
        String SELECT = "SELECT * FROM empleado WHERE fecha_ingreso ='"+this.fecha_ingreso+"'" ;
        return consultasAll(SELECT);
    }


    public List<Empleado> consultarEmpleados(){
        String SELECT = "SELECT * FROM empleado";
        return consultasAll(SELECT);
    }

    public boolean consultarCedula(){
        String SELECT = "SELECT * FROM `empleado` WHERE `cedula`= '"+this.cedula+"'";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.id = rs.getInt(1);
                    this.nombre = rs.getString(2);
                    this.apellido = rs.getString(3);
                    this.cedula = rs.getString(4);
                    this.fecha_ingreso = rs.getString(5);
                    this.id_sucursal = rs.getInt(6);
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

    public boolean consultar(){
        String SELECT = "SELECT * FROM `empleado` WHERE `id`= "+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.nombre = rs.getString(2);
                    this.apellido = rs.getString(3);
                    this.cedula = rs.getString(4);
                    this.fecha_ingreso = rs.getString(5);
                    this.id_sucursal = rs.getInt(6);
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

    private List<Empleado> consultasAll(String SELECT){
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                List<Empleado> empleados = new ArrayList<Empleado>();
                while (rs.next()){
                    Empleado empleado = new Empleado(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6)
                    );
                    empleados.add(empleado);
                }
                oConexion.desconectar();
                return empleados;
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean insertar(){
        String INSERT = "INSERT INTO `empleado`(`nombre`, `apellido`, `cedula`, `fecha_ingreso`, `id_sucursal`) VALUES ('"+this.nombre+"','"+this.apellido+"','"+this.cedula+"','"+this.fecha_ingreso+"',"+this.id_sucursal+")";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(INSERT);
        return oConexion.ejecutarSentencia();
    }

    public boolean actualizar(){
        String UPDATE= "UPDATE `empleado` SET `nombre`= '"+this.nombre+"',`apellido`= '"+this.apellido+"',`cedula`= '"+this.cedula+"',`fecha_ingreso`= '"+this.fecha_ingreso+"' ,`id_sucursal`= "+this.id_sucursal+" WHERE `id`= " + this.id ;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(UPDATE);
        return oConexion.ejecutarSentencia();
    }

    public boolean eliminar(){
        String DELETE= "DELETE FROM empleado WHERE `id`="+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(DELETE);
        return oConexion.ejecutarSentencia();
    }
}
