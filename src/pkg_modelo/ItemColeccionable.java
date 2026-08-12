package pkg_modelo;

//ximena
public abstract class ItemColeccionable {
    protected int id;
    protected String rareza;
    protected double precioEstimado;

    public ItemColeccionable() {}

    public ItemColeccionable(int id, String rareza, double precioEstimado) {
        this.id = id;
        this.rareza = rareza;
        this.precioEstimado = precioEstimado;
    }

    // Método abstracto para Polimorfismo
    public abstract String obtenerDetalles();

    // Getters y Setters (Encapsulamiento)
    public int getId(){ 
        return id; }
    public void setId(int id) 
    { this.id = id; 
    }

    public String getRareza(){ 
        return rareza; 
    }
    public void setRareza(String rareza){ 
        this.rareza = rareza; 
    }

    public double getPrecioEstimado(){ 
        return precioEstimado; 
    }
    public void setPrecioEstimado(double precioEstimado){ 
        this.precioEstimado = precioEstimado; 
    }
}


