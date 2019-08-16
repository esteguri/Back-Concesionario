package modelo;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Auto {

    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private String placa;
    private String fecha_ingreso;
    private String marca;
    private int id_empleado;

    public Auto() {
    }

    public Auto(int id) {
        this.id = id;
    }

    public Auto(int id, String nombre, String descripcion, int precio, String placa, String fecha_ingreso, String marca, int id_empleado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.placa = placa;
        this.fecha_ingreso = fecha_ingreso;
        this.marca = marca;
        this.id_empleado = id_empleado;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public List<Auto> consultarXMarca(){
        String SELECT = "SELECT * FROM auto WHERE marca = '"+this.marca+"'" ;
        return consultasAll(SELECT);
    }

    public List<Auto> consultarXFechaIngreso(){
        String SELECT = "SELECT * FROM auto WHERE fecha_ingreso = '"+this.fecha_ingreso+"'" ;
        return consultasAll(SELECT);
    }

    public List<Auto> consultarXPrecio(){
        String SELECT = "SELECT * FROM auto WHERE precio ="+this.precio ;
        return consultasAll(SELECT);
    }

    public List<Auto> consultarXIdEmpleado(){
        String SELECT = "SELECT * FROM auto WHERE id_empleado ="+this.id_empleado ;
        return consultasAll(SELECT);
    }

    public List<Auto> consultarAutos(){
        String SELECT = "SELECT * FROM auto";
        return consultasAll(SELECT);
    }

    private List<Auto> consultasAll(String SELECT){
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                List<Auto> autos = new ArrayList<Auto>();
                while (rs.next()){
                    Auto auto = new Auto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8)
                    );
                    autos.add(auto);
                }
                oConexion.desconectar();
                return autos;
            }catch(Exception e){
                oConexion.desconectar();
                System.out.println("ERROR " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean consultarXPlaca(){
        String SELECT = "SELECT * FROM `auto` WHERE `placa`= '"+this.placa+"'";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.id = rs.getInt(1);
                    this.nombre = rs.getString(2);
                    this.descripcion = rs.getString(3);
                    this.precio = rs.getInt(4);
                    this.placa = rs.getString(5);
                    this.fecha_ingreso = rs.getString(6);
                    this.marca = rs.getString(7);
                    this.id_empleado = rs.getInt(8);
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
        String SELECT = "SELECT * FROM `auto` WHERE `id`= "+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(SELECT);
        if (oConexion.ejecutarConsulta()){
            try{
                ResultSet rs = oConexion.getoResultSet();
                if (rs.next()){
                    this.nombre = rs.getString(2);
                    this.descripcion = rs.getString(3);
                    this.precio = rs.getInt(4);
                    this.placa = rs.getString(5);
                    this.fecha_ingreso = rs.getString(6);
                    this.marca = rs.getString(7);
                    this.id_empleado = rs.getInt(8);
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

    public boolean insertar(){
        String INSERT = "INSERT INTO auto(`nombre`,`descripcion`,`precio`, `placa`,`fecha_ingreso`,`marca`,`id_empleado`) values('"+this.nombre+"','"+this.descripcion+"', "+this.precio+",'"+this.placa+"', '"+this.fecha_ingreso+"','"+this.marca+"',"+this.id_empleado+")";
        Conexion oConexion = new Conexion();
        oConexion.setSQL(INSERT);
        return oConexion.ejecutarSentencia();
    }

    public boolean actualizar(){
        String UPDATE= "UPDATE auto SET `nombre`='"+this.nombre+"',`descripcion`='"+this.descripcion+"',`precio`= "+this.precio+", `placa` = '"+this.placa+"', `fecha_ingreso`='"+this.fecha_ingreso+"', `marca`='"+this.marca+"', `id_empleado`="+this.id_empleado+" WHERE `id`="+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(UPDATE);
        return oConexion.ejecutarSentencia();
    }

    public boolean eliminar(){
        String DELETE= "DELETE FROM auto WHERE `id`="+this.id;
        Conexion oConexion = new Conexion();
        oConexion.setSQL(DELETE);
        return oConexion.ejecutarSentencia();
    }

}
