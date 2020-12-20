
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
   public login log(String correo, String pss){
       login l1 = new login();
       String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, correo);
           ps.setString(2, pss);
           rs= ps.executeQuery();
           if(rs.next()){
               l1.setId(rs.getInt("id"));
               l1.setNombre(rs.getString("Nombre"));
               l1.setCorreo(rs.getString("correo"));
               l1.setPass(rs.getString("pass"));
               l1.setRol(rs.getString("rol"));

           }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        return l1;
   }
   
   public boolean Registrar(login reg){
       String sql = "INSERT INTO usuarios (nombre, correo, paa, rol) VALUES (?,?,?,?)";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, reg.getNombre());
           ps.setString(2, reg.getCorreo());
           ps.setString(3, reg.getPass());
           ps.setString(4, reg.getRol());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
   }
}
