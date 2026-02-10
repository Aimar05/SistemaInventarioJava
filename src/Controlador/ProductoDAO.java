package Controlador;

import Modelo.CargarComboBox;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Modelo.Producto;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProductoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public List Listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "{call ListarProducto()}";
        try {
            con = cn.getConnection();
            CallableStatement cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {

                Producto p = new Producto();
                p.setIdProd(rs.getInt("idProd"));
                p.setNomProd(rs.getString("Producto"));
                p.setNomCat(rs.getString("Categoria"));
                p.setCanProd(rs.getInt("canProd"));
                p.setDesProd(rs.getString("desProd"));
                p.setNomProv(rs.getString("Proveedor"));
                lista.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }
    
    public List<Producto> buscarProducto(String producto, String categoria, String proveedor){
        List<Producto> lista = new ArrayList<>();
        String sql = "{call buscarProducto(?,?,?)}";
        
        try {
            con = cn.getConnection();
            CallableStatement cs = con.prepareCall(sql);
            
            cs.setString(1, producto);
            cs.setString(2, categoria);
            cs.setString(3, proveedor);
            rs = cs.executeQuery();
            
            while (rs.next()) {                
                Producto p = new Producto();
                p.setIdProd(rs.getInt("idProd"));
                p.setNomProd(rs.getString("Producto"));
                p.setNomCat(rs.getString("Categoria"));
                p.setCanProd(rs.getInt("canProd"));
                p.setDesProd(rs.getString("desProd"));
                p.setNomProv(rs.getString("Proveedor"));
                lista.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }
    
    public boolean RegistroProducto(Producto pr){
        String sql = "{call AgregarProducto(?,?,?,?,?)}";
        
        try {
            con = cn.getConnection();
            CallableStatement cs = con.prepareCall(sql);
            
            cs.setString(1, pr.getNomProd());
            cs.setInt(2, pr.getCanProd());
            cs.setString(3, pr.getDesProd());
            cs.setInt(4, pr.getIdCat());
            cs.setInt(5, pr.getIdProv());
            cs.execute();
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
    
    public boolean ActualizarProducto(Producto pr){
        String sql = "{call ActualizarProducto(?,?,?,?,?,?)}";
        
        try {
            con = cn.getConnection();
            CallableStatement cs = con.prepareCall(sql);
            
            cs.setInt(1, pr.getIdProd());
            cs.setString(2, pr.getNomProd());
            cs.setInt(3, pr.getIdCat());
            cs.setInt(4, pr.getCanProd());
            cs.setString(5, pr.getDesProd());
            cs.setInt(6, pr.getIdProv());
            cs.execute();
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
    
    public boolean EliminarProducto(Producto pr){
        String sql = "{call EliminarProducto(?)}";
        try {
            con = cn.getConnection();
            CallableStatement cs = con.prepareCall(sql);
            
            cs.setInt(1, pr.getIdProd());
            cs.execute();
            
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
    
    public void CargarCategoria(JComboBox cmbCat){
        String sql = "select * from Categoria";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            cmbCat.removeAllItems();// limpia el cmbbox
            cmbCat.addItem("----Seleccione----");
            while(rs.next()){
                cmbCat.addItem(
                        new CargarComboBox(
                                rs.getInt("idCat"),
                                rs.getString("nomCat")
                        ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void CargarProveedor(JComboBox cmbProv){
        String sql = "select * from Proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            cmbProv.removeAllItems();
            cmbProv.addItem("----Seleccione----");
            while(rs.next()){
                cmbProv.addItem(
                        new CargarComboBox(
                        rs.getInt("idProv"),
                        rs.getString("nomProv")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
