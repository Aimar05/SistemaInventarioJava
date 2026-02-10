
package Modelo;

public class CargarComboBox {
    
    private int id;
    private String nombre;

    public CargarComboBox() {
    }

    public CargarComboBox(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre; // lo que se muestra en el combo
    }
    
}
