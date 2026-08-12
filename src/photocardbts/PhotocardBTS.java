package photocardbts;

import pkg_controlador.ControladorPhotocard;
import pkg_modelo.ModeloPhotocard;
import pkg_vista.PhotocardVista;


/**
 *
 * @author MELI
 */
public class PhotocardBTS {

    public static void main(String[] args) {
        ModeloPhotocard modelo = new ModeloPhotocard();
        PhotocardVista vista = new PhotocardVista();
        ControladorPhotocard controlador = new ControladorPhotocard(vista, modelo);
        
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
