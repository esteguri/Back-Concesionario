package controlador;

import com.google.gson.Gson;
import modelo.Empleado;
import modelo.Sucursal;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "sucursal", urlPatterns = {"/api/sucursal"})
public class AdminSucursal extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String nombre, descripcion;
        try{
            nombre = request.getParameter("nombre");
            descripcion = request.getParameter("descripcion");
        }catch(Exception e){
            nombre = null;
            descripcion = null;
        }

        String json = "null";
        response.setContentType("application/json");
        if (nombre!=null && descripcion!=null){
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(nombre);
            sucursal.setDescripcion(descripcion);
            if (sucursal.insertar()){
                json = "{\"error\":200,\"mensaje\":\"ok\",\"id\":"+sucursal.getId()+"}";
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
        String nombre, descripcion;
        int id;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            nombre = request.getParameter("nombre");
            descripcion = request.getParameter("descripcion");

        }catch(Exception e){
            id=-1;
            nombre = null;
            descripcion = null;
        }

        String json = "null";
        response.setContentType("application/json");
        if (id!=-1 && nombre!=null && descripcion!=null){
            Sucursal sucursal = new Sucursal();
            sucursal.setId(id);
            sucursal.setNombre(nombre);
            sucursal.setDescripcion(descripcion);
            if (sucursal.actualizar()){
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
        if (search!=null && search.equals("nombre")){

            String sucursal;
            try{
                sucursal = request.getParameter("nombre");
            }catch(Exception e){
                sucursal = null;
            }

            json = consultarNombre(sucursal);

        }else if(search!=null && search.equals("sucursales")) {
                json = consultarSucursales();
        }else if(search!=null && search.equals("id")) {
            int id;
            try{
                id= Integer.parseInt(request.getParameter("id"));
            }catch(Exception e){
                id = -1;
            }

            json = consultarSucursal(id);
        }else {
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
            Sucursal sucursal = new Sucursal();
            sucursal.setId(id);
            if (sucursal.eliminar()){
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

    private String consultarNombre(String nombre){
        if (nombre!=null){
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(nombre);
            List<Sucursal> sucursales = sucursal.consultarXNombre();
            if (sucursales!=null && sucursales.size()>0){
                return new Gson().toJson(sucursales);
            }else{
                return "{\"error\":500,\"mensaje\":\"No existe\"}";
            }
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarSucursal(int id){
        Sucursal sucursal = new Sucursal();
        sucursal.setId(id);
        if (sucursal.consultar()){
            return new Gson().toJson(sucursal);
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarSucursales(){
        Sucursal sucursal = new Sucursal();
        List<Sucursal> sucursales = sucursal.consultarSucursales();
        if (sucursales!=null && sucursales.size()>0){
            return new Gson().toJson(sucursales);
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }


}
