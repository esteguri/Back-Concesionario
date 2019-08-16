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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
        } else {
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        out.println(json);
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
