package controlador;

import com.google.gson.Gson;
import modelo.Auto;
import modelo.Empleado;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "empleado", urlPatterns = {"/api/empleado"})
public class AdminEmpleado extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String nombre, apellido, cedula, fecha_ingreso;
        int id_sucursal;
        try{
            nombre = request.getParameter("nombre");
            apellido = request.getParameter("apellido");
            cedula = request.getParameter("cedula");
            fecha_ingreso = request.getParameter("fecha_ingreso");
            id_sucursal = Integer.parseInt(request.getParameter("id_sucursal"));

        }catch(Exception e){
            nombre = null;
            apellido = null;
            cedula = null;
            fecha_ingreso = null;
            id_sucursal = -1;
        }

        String json = "null";
        response.setContentType("application/json");
        if (nombre!=null && apellido!=null && cedula!=null && fecha_ingreso!=null && id_sucursal!=-1){
            Empleado empleado = new Empleado();
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setCedula(cedula);
            empleado.setFecha_ingreso(fecha_ingreso);
            empleado.setId_sucursal(id_sucursal);
            if (empleado.insertar()){
                json = "{\"error\":200,\"mensaje\":\"ok\",\"id\":"+empleado.getId()+"}";
            }else{
                json = "{\"error\":500,\"mensaje\":\"Error en la insercion\"}";
            }
        }else{
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        PrintWriter out = response.getWriter();
        out.println(json);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String nombre, apellido, cedula, fecha_ingreso;
        int id_sucursal, id;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            nombre = request.getParameter("nombre");
            apellido = request.getParameter("apellido");
            cedula = request.getParameter("cedula");
            fecha_ingreso = request.getParameter("fecha_ingreso");
            id_sucursal = Integer.parseInt(request.getParameter("id_sucursal"));

        }catch(Exception e){
            id=-1;
            nombre = null;
            apellido = null;
            cedula = null;
            fecha_ingreso = null;
            id_sucursal = -1;
        }

        String json = "null";
        response.setContentType("application/json");
        if (id!=-1 && nombre!=null && apellido!=null && cedula!=null && fecha_ingreso!=null && id_sucursal!=-1){
            Empleado empleado = new Empleado();
            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setCedula(cedula);
            empleado.setFecha_ingreso(fecha_ingreso);
            empleado.setId_sucursal(id_sucursal);
            if (empleado.actualizar()){
                json = "{\"error\":200,\"mensaje\":\"ok\"}";
            }else{
                json = "{\"error\":500,\"mensaje\":\"Error en la actualizacion\"}";
            }
        }else{
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String search;
        try{
            search = request.getParameter("search");
        }catch(Exception e){
            search = null;
        }

        String json;
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (search!=null && search.equals("sucursal")){

            int sucursal;
            try{
                sucursal = Integer.parseInt(request.getParameter("sucursal"));
            }catch(Exception e){
                sucursal = -1;
            }

            json = consultarSucursal(sucursal);

        }else if (search!=null && search.equals("ingreso")){
            String ingreso;
            try{
                ingreso = request.getParameter("ingreso");
            }catch(Exception e){
                ingreso = null;
            }
            json = consultarFechaIngreso(ingreso);

        }else if(search!=null && search.equals("empleados")){
            json = consultarEmpleados();
        }else if(search!=null && search.equals("id")){
            int id;
            try{
                id = Integer.parseInt(request.getParameter("id"));
            }catch(Exception e){
                id = -1;
            }
            json = consultarEmpleado(id);
        }else if(search!=null && search.equals("cedula")){
            String cedula;
            try{
                cedula = request.getParameter("cedula");
            }catch(Exception e){
                cedula = null;
            }
            json = consultarCedula(cedula);
        } else {
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        out.println(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id;
        try{
            id = Integer.parseInt(request.getParameter("id"));

        }catch(Exception e){
            id=-1;
        }

        String json = "null";
        response.setContentType("application/json");
        if (id!=-1){
            Empleado empleado = new Empleado();
            empleado.setId(id);
            if (empleado.eliminar()){
                json = "{\"error\":200,\"mensaje\":\"ok\"}";
            }else{
                json = "{\"error\":500,\"mensaje\":\"Error en la eliminacion\"}";
            }
        }else{
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    private String consultarCedula(String cedula){
        Empleado empleado = new Empleado();
        empleado.setCedula(cedula);
        if (empleado.consultarCedula()){
            return new Gson().toJson(empleado);
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }


    private String consultarEmpleado(int id){
        Empleado empleado = new Empleado();
        empleado.setId(id);
        if (empleado.consultar()){
            return new Gson().toJson(empleado);
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }


    private String consultarFechaIngreso(String fecha){
        if (fecha!=null){
            Empleado empleado = new Empleado();
            empleado.setFecha_ingreso(fecha);
            List<Empleado> empleados = empleado.consultarXFechaIngreso();
            if (empleados!=null && empleados.size()>0){
                return new Gson().toJson(empleados);
            }else{
                return "{\"error\":500,\"mensaje\":\"No existe\"}";
            }
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarEmpleados(){
        Empleado empleado = new Empleado();
        List<Empleado> empleados = empleado.consultarEmpleados();
        if (empleados!=null && empleados.size()>0){
            return new Gson().toJson(empleados);
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarSucursal(int sucursal){
        if (sucursal!=-1){
            Empleado empleado = new Empleado();
            empleado.setId_sucursal(sucursal);
            List<Empleado> empleados = empleado.consultarXIdSucursal();
            if (empleados!=null && empleados.size()>0){
                return new Gson().toJson(empleados);
            }else{
                return "{\"error\":500,\"mensaje\":\"No existe\"}";
            }
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

}
