package pkg_modelo;

//ximena

public class Photocard extends ItemColeccionable {
    private String detalles;

    public Photocard() {
        super();
    }

    public Photocard(String idString, String rareza, double precioEstimado, String detalles) {
        // Asignamos rareza y precio a la clase padre ItemColeccionable
        this.rareza = rareza;
        this.precioEstimado = precioEstimado;
        this.detalles = detalles;
        
        // Convertimos el ID a int para la clase base si es numérico
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