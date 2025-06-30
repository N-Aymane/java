package dao;

import Model.Bibliothecaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BibliothecaireDao {
    public static Bibliothecaire verifierIdentifiants(String identifiant, String motDePasse) {
        String sql = "SELECT * FROM bibliothecaire WHERE identifiant = ? AND motDePasse = ?";

        try (Connection conn = connexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, identifiant);
            stmt.setString(2, motDePasse);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bibliothecaire b = new Bibliothecaire(
                        rs.getString("nom"),
                        rs.getString("identifiant"),
                        rs.getString("motDePasse")
                );
                b.setIdBibliothecaire(rs.getInt("idBibliothecaire"));
                return b;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
