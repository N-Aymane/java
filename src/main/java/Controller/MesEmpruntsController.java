package Controller;

import Model.Emprunt;
import Model.Etudiant;
import dao.EmpruntDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class MesEmpruntsController {



    @FXML private TableView<Emprunt> empruntTable;
    @FXML private TableColumn<Emprunt, String> titreColumn;
    @FXML private TableColumn<Emprunt, String> auteurColumn;
    @FXML private TableColumn<Emprunt, String> dateEmpruntColumn;
    @FXML
    private TableColumn<Emprunt, String> dateRetourPrevuColumn;

    @FXML private TableColumn<Emprunt, String> statutColumn;

    private ObservableList<Emprunt> empruntList = FXCollections.observableArrayList();


    private Etudiant etudiant;

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        chargerEmprunts();
    }

    private void chargerEmprunts() {
        List<Emprunt> emprunts = EmpruntDao.getEmpruntsParEtudiant(etudiant.getIdEtudiant());
        empruntTable.setItems(FXCollections.observableArrayList(emprunts));
    }

    @FXML
    private void initialize() {
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().getLivre().titreProperty());
        auteurColumn.setCellValueFactory(cellData -> cellData.getValue().getLivre().auteurProperty());
        dateEmpruntColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateEmprunt().toString()));
        dateRetourPrevuColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getDateRetourPrevu() != null ?
                                cellData.getValue().getDateRetourPrevu().toString() :
                                ""
                )
        );

        statutColumn.setCellValueFactory(cellData -> {
            boolean enRetard = cellData.getValue().getDateRetourEffectif() == null &&
                    cellData.getValue().getDateRetourPrevu().isBefore(LocalDate.now());
            return new SimpleStringProperty(enRetard ? "❌ En retard" : "✅ En cours");
        });

        empruntTable.setItems(empruntList);
    }


    @FXML
    private void handleRetour() {
        Stage stage = (Stage) empruntTable.getScene().getWindow();
        stage.close();
    }
}
