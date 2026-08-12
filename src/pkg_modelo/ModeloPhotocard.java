package pkg_modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pkg_excepciones.PrecioInvalidoException;

//Dafne
public class ModeloPhotocard {
    private final String URL = "jdbc:mysql://localhost:3306/bd_bts_collection";
    private final String USER = "root";
    private final String PASSWORD = "Lango0304!";
    
    private void validarPrecio(double precio) throws PrecioInvalidoException {
        if (precio <= 0) {
            throw new PrecioInvalidoException("El precio estimado debe "
                    + "ser mayor a $0.00 MXN.");
        }
    }
    public boolean insertar(Photocard p) throws PrecioInvalidoException {
        validarPrecio(p.getPrecioEstimado());
        String sql = "INSERT INTO photocard (id_photocard, rareza, "
                + "precio_estimado, detalles) VALUES (?,?,?,?)";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(p.getId()));
            ps.setString(2, p.getRareza());
            ps.setDouble(3, p.getPrecioEstimado());
            ps.setString(4, p.getDetalles());

            ps.execute();
            con.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

  
    public Photocard buscarPorId(String id) {
        String sql = "SELECT * FROM photocard WHERE id_photocard = ?";
        Photocard p = null;

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Photocard(
                    rs.getString("id_photocard"),
                    rs.getString("rareza"),
                    rs.getDouble("precio_estimado"),
                    rs.getString("detalles")
                );
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al buscar por ID: " + e.getMessage());
        }
        return p;
    }

    
    public boolean actualizar(Photocard p) throws PrecioInvalidoException {
        validarPrecio(p.getPrecioEstimado());
        String sql = "UPDATE photocard SET rareza=?, precio_estimado=?, "
                + "detalles=? WHERE id_photocard=?";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getRareza());
            ps.setDouble(2, p.getPrecioEstimado());
            ps.setString(3, p.getDetalles());
            ps.setString(4, String.valueOf(p.getId()));

            ps.execute();
            con.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    
    public boolean eliminar(String id) {
        String sql = "DELETE FROM photocard WHERE id_photocard=?";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);

            ps.execute();
            con.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

   
    public List<Photocard> obtenerTodos() {
        List<Photocard> lista = new ArrayList<>();
        String sql = "SELECT * FROM photocard";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Photocard p = new Photocard(
                    rs.getString("id_photocard"),
                    rs.getString("rareza"),
                    rs.getDouble("precio_estimado"),
                    rs.getString("detalles")
                );
                lista.add(p);
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener lista: " + e.getMessage());
        }
        return lista;
    }
}
