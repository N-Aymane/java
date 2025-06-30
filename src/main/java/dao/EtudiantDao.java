package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Etudiant;

public class EtudiantDao {

    public static Etudiant verifierIdentifiants(String nom, String motDePasse) {
        String sql = "SELECT * FROM etudiant WHERE nom = ? AND mot_de_passe = ?";

        try (Connection conn = connexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            stmt.setString(2, motDePasse);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Etudiant(
                        rs.getInt("idEtudiant"),  // int
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("matricule"),
                        rs.getString("filiere"),
                        rs.getString("niveau")
                );



            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Aucun étudiant trouvé
    }
}
