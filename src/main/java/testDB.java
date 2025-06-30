import dao.connexionBD;
import java.sql.Connection;

public class testDB {
    public static void main(String[] args) {
        try (Connection conn = connexionBD.getConnection()) {
            System.out.println("✅ Connexion réussie à la base de données !");
        } catch (Exception e) {
            System.out.println("❌ Erreur de connexion : " + e.getMessage());
        }
    }
}
