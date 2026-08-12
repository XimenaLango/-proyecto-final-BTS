package pkg_modelo;


public class Photocard extends ItemColeccionable {
    private String nombreMiembro;
    private String versionAlbum;
    private boolean disponible;

    public Photocard() {
        super();
    }

    public Photocard(int id, String nombreMiembro, String versionAlbum, 
            String rareza, double precioEstimado, boolean disponible) {
        super(id, rareza, precioEstimado);
        this.nombreMiembro = nombreMiembro;
        this.versionAlbum = versionAlbum;
        this.disponible = disponible;
    }

    @Override
    public String obtenerDetalles() {
        return "Photocard de " + nombreMiembro + " - " + versionAlbum + " (" + rareza + ")";
    }

    // Getters y Setters
    public String getNombreMiembro()
    { return nombreMiembro; 
    }
    public void setNombreMiembro(String nombreMiembro){ 
        this.nombreMiembro = nombreMiembro; 
    }

    public String getVersionAlbum(){ 
        return versionAlbum; 
    }
    public void setVersionAlbum(String versionAlbum){ 
        this.versionAlbum = versionAlbum; 
    }

    public boolean isDisponible(){ 
        return disponible; 
    }
    public void setDisponible(boolean disponible){ 
        this.disponible = disponible; 
    }
}
