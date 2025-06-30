import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionBDD {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/libraryy?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connexion réussie à la base de données !");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Connexion échouée.");
            e.printStackTrace();
        }
    }
}

