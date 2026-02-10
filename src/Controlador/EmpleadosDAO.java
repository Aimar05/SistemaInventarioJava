package Controlador;

import java.sql.Connection;
import Modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Modelo.Empleados;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EmpleadosDAO {

    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public boolean RegistrarEmpleado(Empleados em) {
        String sql = "insert into Empleados(nomEmp, apeEmp, edadEmp, dniEmp, nacEmp, celEmp, corrEmp, dirEmp)values(?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNomEmp());
            ps.setString(2, em.getApeEmp());
            ps.setInt(3, em.getEdadEmp());
            ps.setString(4, em.getDniEmp());
            ps.setDate(5, em.getNacEmp());
            ps.setString(6, em.getCelEmp());
            ps.setString(7, em.getCorrEmp());
            ps.setString(8, em.getDirEmp());
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

    public List Listar() {
        List<Empleados> lista = new ArrayList<>();
        String sql = "select * from Empleados";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleados em = new Empleados();
                em.setCodEmp(rs.getInt(1));
                em.setNomEmp(rs.getString(2));
                em.setApeEmp(rs.getString(3));
                em.setEdadEmp(rs.getInt(4));
                em.setDniEmp(rs.getString(5));
                em.setNacEmp(rs.getDate(6));
                em.setCelEmp(rs.getString(7));
                em.setCorrEmp(rs.getString(8));
                em.setDirEmp(rs.getString(9));
                lista.add(em);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());

        }
        return lista;
    }

    public boolean ActualizarEmpleado(Empleados em) {
        String sql = "update Empleados set nomEmp =?, apeEmp =?, edadEmp =?, dniEmp =?, nacEmp =?, celEmp =?, corrEmp =?, dirEmp =? where codEmp =?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, em.getNomEmp());
            ps.setString(2, em.getApeEmp());
            ps.setInt(3, em.getEdadEmp());
            ps.setString(4, em.getDniEmp());
            ps.setDate(5, em.getNacEmp());
            ps.setString(6, em.getCelEmp());
            ps.setString(7, em.getCorrEmp());
            ps.setString(8, em.getDirEmp());
            ps.setInt(9, em.getCodEmp());
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

    public boolean eliminarEmpleado(Empleados em) {
        String sql = "delete from Empleados where codEmp = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, em.getCodEmp());
            ps.execute();
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            return false;
        }
    }

    public List<Empleados> buscarEmpleados(Integer codEmp, String nomEmp, String dniEmp) {
        List<Empleados> lista = new ArrayList<>();
        String sql = "select * from Empleados where 1=1";

        if (codEmp != null) {
            sql += " AND codEmp = ? ";
        }
        if (nomEmp != null && !nomEmp.isBlank()) {
            sql += " AND nomEmp LIKE ? ";
        }
        if (dniEmp != null && !dniEmp.isBlank()) {
            sql += " AND dniEmp LIKE ? ";
        }

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            int index = 1;
            if (codEmp != null) {
                ps.setInt(index++, codEmp);
            }
            if (nomEmp != null && !nomEmp.isBlank()) {
                ps.setString(index++, "%" + nomEmp + "%");
            }
            if (dniEmp != null && !dniEmp.isBlank()) {
                ps.setString(index++, "%" + dniEmp + "%");
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Empleados em = new Empleados();
                em.setCodEmp(rs.getInt(1));
                em.setNomEmp(rs.getString(2));
                em.setApeEmp(rs.getString(3));
                em.setEdadEmp(rs.getInt(4));
                em.setDniEmp(rs.getString(5));
                em.setNacEmp(rs.getDate(6));
                em.setCelEmp(rs.getString(7));
                em.setCorrEmp(rs.getString(8));
                em.setDirEmp(rs.getString(9));
                lista.add(em);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return lista;
    }
}
