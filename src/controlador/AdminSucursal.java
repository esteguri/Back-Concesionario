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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        }else {
            json = "{\"error\":500,\"mensaje\":\"Error en la peticion\"}";
        }
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


}
