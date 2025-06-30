package dao;

import Model.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDao {

    public static List<Livre> getAllLivres() {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre";

        try (Connection conn = connexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("editeur"),
                        rs.getInt("annee"),
                        rs.getInt("nombreExemplaires")
                );
                livre.setDisponible(rs.getInt("nombreExemplaires") > 0); // dispo si au moins un exemplaire
                livres.add(livre);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return livres;
    }

    public static boolean ajouterLivre(Livre livre) {
        String sql = "INSERT INTO livre (titre, auteur, editeur, annee, nombreExemplaires) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getEditeur());
            stmt.setInt(4, livre.getAnnee());
            stmt.setInt(5, livre.getNombreExemplaires());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modifierLivre(Livre livre) {
        String sql = "UPDATE livre SET titre = ?, auteur = ?, editeur = ?, annee = ?, nombreExemplaires = ? WHERE idLivre = ?";

        try (Connection conn = connexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getEditeur());
            stmt.setInt(4, livre.getAnnee());
            stmt.setInt(5, livre.getNombreExemplaires());
            stmt.setInt(6, livre.getIdLivre());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supprimerLivre(int idLivre) {
        String sql = "DELETE FROM livre WHERE idLivre = ?";

        try (Connection conn = connexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivre);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
