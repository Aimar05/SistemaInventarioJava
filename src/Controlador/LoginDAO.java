package Modelo;

import Config.Hashed;
import Vista.frmLogin;
import Vista.frmRecuperarClave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Login lg(String nomUsu, String conUsu) {
        Login l = new Login();
        String sql = "select * from Usuario where nomUsu = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nomUsu);
            //ps.setString(2, contraseña);
            rs = ps.executeQuery();
            if (rs.next()) {
                String hashBDS = rs.getString("contUsu");
                //    System.out.println("Usuario encontrado en BD");
                if (BCrypt.checkpw(conUsu, hashBDS)) {
                    l.setCodUsu(rs.getInt("codUsu"));
                    l.setNomUsu(rs.getString("nomUsu"));
                    l.setContUsu(rs.getString("contUsu"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }

    public String resetPassword(String nomUsu) {
        String nuevaPass = Hashed.generarPasswordTemporal();
        String hash = Hashed.hashPassword(nuevaPass);

        String sql = "UPDATE Usuario SET contUsu = ? WHERE nomUsu = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, hash);
            ps.setString(2, nomUsu);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return nuevaPass;
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    public boolean actualizarPassword(String nomUsu, String actConUsu) {

        String hash = Hashed.hashPassword(actConUsu);
        String sql = "UPDATE Usuario SET contUsu = ? WHERE nomUsu = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, hash);
            ps.setString(2, nomUsu);

            return ps.executeUpdate() > 0; // true = actualizado

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean usuarioExistente(String nomUsu){
        String sql = "SELECT 1 FROM Usuario WHERE nomUsu = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, nomUsu);
            rs = ps.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
