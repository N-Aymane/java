import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlLocation = getClass().getResource("/view/LoginView.fxml");
            if (fxmlLocation == null) {
                showError("Le fichier LoginView.fxml est introuvable !");
                return;
            }

            Parent root = FXMLLoader.load(fxmlLocation);
            primaryStage.setTitle("Connexion");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Erreur lors du chargement de l'application : " + e.getMessage());
        }
    }

    private void showError(String message) {
        System.err.println(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
