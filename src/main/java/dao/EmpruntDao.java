package dao;

import Model.Emprunt;
import Model.Etudiant;
import Model.Livre;
import dao.connexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDao {

    public static boolean enregistrerEmprunt(int idEtudiant, int idLivre) {
        String sql = "INSERT INTO emprunt (idEtudiant, idLivre, dateEmprunt, dateRetourPrevu) VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY))";

        try (Connection conn = connexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtudiant);
            stmt.setInt(2, idLivre);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Emprunt> getAllEmprunts() {
        List<Emprunt> emprunts = new ArrayList<>();

        String sql = "SELECT e.idEmprunt, e.dateEmprunt, e.dateRetourPrevu, e.dateRetourEffectif, " +
                "l.idLivre, l.titre, l.auteur, l.editeur, l.annee, l.nombreExemplaires, " +
                "et.idEtudiant, et.nom, et.prenom, et.matricule, et.filiere, et.niveau " +
                "FROM emprunt e " +
                "JOIN livre l ON e.idLivre = l.idLivre " +
                "JOIN etudiant et ON e.idEtudiant = et.idEtudiant";

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

                Etudiant etudiant = new Etudiant(
                        rs.getInt("idEtudiant"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("matricule"),
                        rs.getString("filiere"),
                        rs.getString("niveau")
                );

                Emprunt emprunt = new Emprunt(
                        livre,
                        etudiant,
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetourPrevu").toLocalDate()
                );

                if (rs.getDate("dateRetourEffectif") != null) {
                    emprunt.setDateRetourEffectif(rs.getDate("dateRetourEffectif").toLocalDate());
                }

                emprunt.setIdEmprunt(rs.getInt("idEmprunt"));
                emprunts.add(emprunt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emprunts;
    }


    public static List<Emprunt> getEmpruntsParEtudiant(int idEtudiant) {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = """
                SELECT e.*, l.*
                FROM emprunt e
                JOIN livre l ON e.idLivre = l.idLivre
                WHERE e.idEtudiant = ?
                """;

        try (Connection conn = connexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtudiant);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("editeur"),
                        rs.getInt("annee"),
                        rs.getInt("nombreExemplaires")
                );

                Emprunt emprunt = new Emprunt(
                        livre,
                        null, // si tu n'as pas besoin de l'objet Etudiant ici
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetourPrevu").toLocalDate()
                );
                emprunt.setIdEmprunt(rs.getInt("idEmprunt"));

                emprunts.add(emprunt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emprunts;
    }
}
