package pkg_modelo;

//ximena

public class Photocard extends ItemColeccionable {
    private String detalles;

    public Photocard() {
        super();
    }

    public Photocard(String idString, String rareza, double precioEstimado, String detalles) {
        this.rareza = rareza;
        this.precioEstimado = precioEstimado;
        this.detalles = detalles;
        
       
        try {
            this.id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            this.id = 0;
        }
    }

    @Override
    public String obtenerDetalles() {
        return detalles;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}