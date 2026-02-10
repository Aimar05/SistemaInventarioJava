
package Modelo;

public class Usuario {
    
    private int codUsu;
    private String nomUsu;
    private String conUsu;

    public Usuario() {
    }

    public Usuario(int codUsu, String nomUsu, String conUsu) {
        this.codUsu = codUsu;
        this.nomUsu = nomUsu;
        this.conUsu = conUsu;
    }

    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getConUsu() {
        return conUsu;
    }

    public void setConUsu(String conUsu) {
        this.conUsu = conUsu;
    }
    
    
    
}
