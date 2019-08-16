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

@WebServlet(name = "auto", urlPatterns = {"/api/auto"})
public class AdminAuto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String nombre, descripcion, placa, fecha_ingreso, marca ;
        int precio, id_empleado;
        try{
            nombre = request.getParameter("nombre");
            descripcion = request.getParameter("descripcion");
            precio = Integer.parseInt(request.getParameter("precio"));
            placa = request.getParameter("placa");
            fecha_ingreso = request.getParameter("fecha_ingreso");
            marca = request.getParameter("marca");
            id_empleado = Integer.parseInt(request.getParameter("id_empleado"));

        }catch(Exception e){
            nombre = null;
            descripcion = null;
            precio = -1;
            placa = null;
            fecha_ingreso = null;
            marca = null;
            id_empleado = -1;
        }
        System.out.println(nombre);
        System.out.println(descripcion);
        System.out.println(precio);
        System.out.println(placa);
        System.out.println(fecha_ingreso);
        System.out.println(marca);
        System.out.println(id_empleado);

        String json = "null";
        response.setContentType("application/json");
        if (nombre!=null && descripcion!=null && precio!=-1 && placa!=null && fecha_ingreso!=null && marca!=null && id_empleado!=-1){
            Auto auto = new Auto();
            auto.setNombre(nombre);
            auto.setDescripcion(descripcion);
            auto.setPrecio(precio);
            auto.setPlaca(placa);
            auto.setFecha_ingreso(fecha_ingreso);
            auto.setMarca(marca);
            auto.setId_empleado(id_empleado);
            if (auto.insertar()){
                json = "{\"error\":200,\"mensaje\":\"ok\",\"id\":"+auto.getId()+"}";
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
        String nombre, descripcion, placa, fecha_ingreso, marca ;
        int precio, id_empleado, id;
        try{
            id = Integer.parseInt(request.getParameter("id"));
            nombre = request.getParameter("nombre");
            descripcion = request.getParameter("descripcion");
            precio = Integer.parseInt(request.getParameter("precio"));
            placa = request.getParameter("placa");
            fecha_ingreso = request.getParameter("fecha_ingreso");
            marca = request.getParameter("marca");
            id_empleado = Integer.parseInt(request.getParameter("id_empleado"));

        }catch(Exception e){
            id=-1;
            nombre = null;
            descripcion = null;
            precio = -1;
            placa = null;
            fecha_ingreso = null;
            marca = null;
            id_empleado = -1;
        }
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(descripcion);
        System.out.println(precio);
        System.out.println(placa);
        System.out.println(fecha_ingreso);
        System.out.println(marca);
        System.out.println(id_empleado);

        String json = "null";
        response.setContentType("application/json");
        if (id!=-1 && nombre!=null && descripcion!=null && precio!=-1 && placa!=null && fecha_ingreso!=null && marca!=null && id_empleado!=-1){
            Auto auto = new Auto();
            auto.setId(id);
            auto.setNombre(nombre);
            auto.setDescripcion(descripcion);
            auto.setPrecio(precio);
            auto.setPlaca(placa);
            auto.setFecha_ingreso(fecha_ingreso);
            auto.setMarca(marca);
            auto.setId_empleado(id_empleado);
            if (auto.insertar()){
                json = "{\"error\":200,\"mensaje\":\"ok\"}";
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String search;
        try{
            search = request.getParameter("search");
        }catch(Exception e){
            search = null;
        }

        String json;
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (search!=null && search.equals("marca")){

            String marca;
            try{
                marca = request.getParameter("marca");
            }catch(Exception e){
                marca = null;
            }

            json = consultarMarca(marca);

        }else if (search!=null && search.equals("ingreso")){
            String ingreso;
            try{
                ingreso = request.getParameter("ingreso");
            }catch(Exception e){
                ingreso = null;
            }
            json = consultarFechaIngreso(ingreso);

        }else if (search!=null && search.equals("precio")){
            int precio;
            try{
                precio = Integer.parseInt(request.getParameter("precio"));
            }catch(Exception e){
                precio = -1;
            }
            json = consultarPrecio(precio);
        }else if(search!=null && search.equals("empleado")){
            int empleado;
            try{
                empleado = Integer.parseInt(request.getParameter("empleado"));
            }catch(Exception e){
                empleado = -1;
            }
            json = consultarEmpleado(empleado);
        }else if(search!=null && search.equals("id")){
            int id;
            try{
                id = Integer.parseInt(request.getParameter("id"));
            }catch(Exception e){
                id = -1;
            }
            json = consultarAuto(id);
        }else if(search!=null && search.equals("autos")){
            json = consultarAutos();
        } else {

            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
        out.println(json);

    }

    private String consultarAuto(int id){
        Auto auto = new Auto();
        auto.setId(id);
        if (auto.consultar()){
            return new Gson().toJson(auto);
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarAutos(){
        Auto auto = new Auto();
        List<Auto> autos = auto.consultarAutos();
        if (autos!=null && autos.size()>0){
            return new Gson().toJson(autos);
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarEmpleado(int empleado){
        if (empleado!=-1){
            Auto auto = new Auto();
            auto.setId_empleado(empleado);
            List<Auto> autos = auto.consultarXIdEmpleado();
            if (autos!=null && autos.size()>0){
                return new Gson().toJson(autos);
            }else{
                return "{\"error\":500,\"mensaje\":\"No existe\"}";
            }
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarPrecio(int precio){
        if (precio!=-1){
            Auto auto = new Auto();
            auto.setPrecio(precio);
            List<Auto> autos = auto.consultarXPrecio();
            if (autos!=null && autos.size()>0){
                return new Gson().toJson(autos);
            }else{
                return "{\"error\":500,\"mensaje\":\"No existe\"}";
            }
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarMarca(String marca){
        if (marca!=null){
            Auto auto = new Auto();
            auto.setMarca(marca);
            List<Auto> autos = auto.consultarXMarca();
            if (autos!=null && autos.size()>0){
                return new Gson().toJson(autos);
            }else{
                return "{\"error\":500,\"mensaje\":\"No existe\"}";
            }
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }

    private String consultarFechaIngreso(String fecha){
        if (fecha!=null){
            Auto auto = new Auto();
            auto.setFecha_ingreso(fecha);
            List<Auto> autos = auto.consultarXFechaIngreso();
            if (autos!=null && autos.size()>0){
                return new Gson().toJson(autos);
            }else{
                return "{\"error\":500,\"mensaje\":\"No existe\"}";
            }
        }else{
            return "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
    }


}