package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {

    private Statement oStatement;
    private Connection oConnection;
    private ResultSet oResultSet;
    private String SQL, Error, driver, bd, user, pass;

    public Conexion() {
        oResultSet = null;
        oStatement = null;
        oConnection = null;
        driver = "com.mysql.jdbc.Driver";
        bd = "jdbc:mysql://localhost/concesionario";
        user = "root";
        pass = "";
    }

    public String getSQL() {
        return SQL;
    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
    }

    public String getError() {
        return Error;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ResultSet getoResultSet() {
        return oResultSet;
    }

    public boolean conectar(){
        try{
            Class.forName(driver);
            this.oConnection = DriverManager.getConnection(bd,user,pass);
            return true;
        }catch(Exception ex){
            this.Error = ex.getMessage();
            return false;
        }
    }

    public boolean desconectar(){
        try{
            this.oConnection.close();
            return true;
        }catch(Exception ex){
            this.Error = ex.getMessage();
            return false;
        }
    }

    public boolean ejecutarSentencia(){
        try{
            conectar();
            this.oStatement = oConnection.createStatement();
            oStatement.execute(this.SQL);
            desconectar();
            return true;
        }catch(Exception ex){
            this.Error = ex.getMessage();
            return false;
        }
    }

    public boolean ejecutarConsulta(){
        try{
            conectar();
            this.oStatement = oConnection.createStatement();
            oResultSet =  oStatement.executeQuery(this.SQL);
            return true;
        }catch(Exception ex){
            this.Error = ex.getMessage();
            return false;
        }
    }


}
