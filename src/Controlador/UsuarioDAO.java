package Controlador;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.Usuario;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import Config.Hashed;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public boolean RegistrarUsuario(Usuario usu) {
        String sql = "insert into Usuario(nomUsu, contUsu) values (?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, usu.getNomUsu());
            ps.setString(2, usu.getConUsu());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public boolean actualizarUsuario(Usuario usu) {
        String hash = Hashed.hashPassword(usu.getConUsu());
        String sql = "update Usuario set nomUsu = ?, contUsu = ? where codUsu = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, usu.getNomUsu());
            ps.setString(2, hash);
            ps.setInt(3, usu.getCodUsu());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean eliminarUsuario(Usuario usu){
        String sql = "delete from Usuario where codUsu = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, usu.getCodUsu());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            return false;
        }
    }

    public List<Usuario> buscarUsuario(Integer codUsu, String nomUsu) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from Usuario where 1=1";

        if (codUsu != null) {
            sql += " AND codUsu = ?";
        }
        if (nomUsu != null && !nomUsu.isBlank()) {
            sql += " AND nomUsu LIKE ?";
        }
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            int index = 1;
            if (codUsu != null) {
                ps.setInt(index++, codUsu);
            }
            if (nomUsu != null && !nomUsu.isBlank()) {
                ps.setString(index++, "%" + nomUsu + "%");
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCodUsu(rs.getInt(1));
                usu.setNomUsu(rs.getString(2));
                lista.add(usu);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return lista;
    }

    public List Listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from Usuario";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCodUsu(rs.getInt(1));
                usu.setNomUsu(rs.getString(2));
                usu.setConUsu(rs.getString(3));
                lista.add(usu);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return lista;
    }

    public boolean usuarioExiste(String nomUsu) {
        String sql = " select 1 from Usuario where nomUsu = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, nomUsu);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
