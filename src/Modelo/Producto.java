package Modelo;

public class Producto {

    private int idProd;
    private String nomProd;
    private int canProd;
    private String desProd;
    private String nomCat;
    private int idCat;
    private String nomProv;
    private int idProv;

    public Producto() {
    }

    public Producto(int idProd, String nomProd, int canProd, String desProd, String nomCat, int idCat, String nomProv, int idProv) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.canProd = canProd;
        this.desProd = desProd;
        this.nomCat = nomCat;
        this.idCat = idCat;
        this.nomProv = nomProv;
        this.idProv = idProv;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public int getCanProd() {
        return canProd;
    }

    public void setCanProd(int canProd) {
        this.canProd = canProd;
    }

    public String getDesProd() {
        return desProd;
    }

    public void setDesProd(String desProd) {
        this.desProd = desProd;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getNomProv() {
        return nomProv;
    }

    public void setNomProv(String nomProv) {
        this.nomProv = nomProv;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    
}
