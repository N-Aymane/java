
package Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.Sanction;
import Model.Etudiant;

import java.time.LocalDate;

public class SanctionController {

    @FXML
    private ComboBox<Etudiant> etudiantComboBox;
    @FXML
    private TextField motifField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Sanction> sanctionTable;
    @FXML
    private TableColumn<Sanction, String> etudiantColumn;
    @FXML
    private TableColumn<Sanction, String> motifColumn;
    @FXML
    private TableColumn<Sanction, LocalDate> dateColumn;

    private ObservableList<Sanction> sanctions = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        etudiantColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEtudiant().getNomComplet())
        );
        motifColumn.setCellValueFactory(cellData -> cellData.getValue().motifProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        sanctionTable.setItems(sanctions);

    }

    @FXML
    private void ajouterSanction() {
        Etudiant etudiant = etudiantComboBox.getValue();
        String motif = motifField.getText();
        LocalDate date = datePicker.getValue();

        if (etudiant != null && motif != null && !motif.isEmpty() && date != null) {
            Sanction sanction = new Sanction(etudiant, motif, date);
            sanctions.add(sanction);
            clearFields();
        } else {
            showAlert("Champs manquants", "Veuillez remplir tous les champs.");
        }
    }

    private void clearFields() {
        etudiantComboBox.setValue(null);
        motifField.clear();
        datePicker.setValue(null);
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
