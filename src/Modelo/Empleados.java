
package Modelo;

import java.sql.Date;

public class Empleados {
    
    private int codEmp;
    private String nomEmp;
    private String apeEmp;
    private int edadEmp;
    private String dniEmp;
    private Date nacEmp;
    private String celEmp;
    private String corrEmp;
    private String dirEmp;

    public Empleados() {
    }

    public Empleados(int codEmp, String nomEmp, String apeEmp, int edadEmp, String dniEmp, Date nacEmp, String celEmp, String corrEmp, String dirEmp) {
        this.codEmp = codEmp;
        this.nomEmp = nomEmp;
        this.apeEmp = apeEmp;
        this.edadEmp = edadEmp;
        this.dniEmp = dniEmp;
        this.nacEmp = nacEmp;
        this.celEmp = celEmp;
        this.corrEmp = corrEmp;
        this.dirEmp = dirEmp;
    }

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public String getApeEmp() {
        return apeEmp;
    }

    public void setApeEmp(String apeEmp) {
        this.apeEmp = apeEmp;
    }

    public int getEdadEmp() {
        return edadEmp;
    }

    public void setEdadEmp(int edadEmp) {
        this.edadEmp = edadEmp;
    }

    public String getDniEmp() {
        return dniEmp;
    }

    public void setDniEmp(String dniEmp) {
        this.dniEmp = dniEmp;
    }

    public Date getNacEmp() {
        return nacEmp;
    }

    public void setNacEmp(Date nacEmp) {
        this.nacEmp = nacEmp;
    }

    public String getCelEmp() {
        return celEmp;
    }

    public void setCelEmp(String celEmp) {
        this.celEmp = celEmp;
    }

    public String getCorrEmp() {
        return corrEmp;
    }

    public void setCorrEmp(String corrEmp) {
        this.corrEmp = corrEmp;
    }

    public String getDirEmp() {
        return dirEmp;
    }

    public void setDirEmp(String dirEmp) {
        this.dirEmp = dirEmp;
    }
    
    
    
}
