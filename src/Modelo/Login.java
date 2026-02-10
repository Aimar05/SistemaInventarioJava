
package Modelo;

public class Login {
    private int codUsu;
    private String nomUsu;
    private String contUsu;

    public Login() {
    }

    public Login(int codUsu, String nomUsu, String contUsu) {
        this.codUsu = codUsu;
        this.nomUsu = nomUsu;
        this.contUsu = contUsu;
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

    public String getContUsu() {
        return contUsu;
    }

    public void setContUsu(String contUsu) {
        this.contUsu = contUsu;
    }

    
}
