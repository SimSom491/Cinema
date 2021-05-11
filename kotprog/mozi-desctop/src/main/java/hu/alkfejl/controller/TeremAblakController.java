package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.dao.MoziDao;
import org.example.dao.TeremDao;
import org.example.model.Terem;

import java.net.URL;
import java.util.ResourceBundle;

public class TeremAblakController implements Initializable {

    MoziDao<Terem> dao = new TeremDao();

    @FXML
    private TableView<Terem> teremTable;
    @FXML
    private TableColumn<Terem, Integer> idColumn;
    @FXML
    private TableColumn<Terem, Integer> sorColumn;
    @FXML
    private TableColumn<Terem, Integer> oszlopColumn;
    @FXML
    private TableColumn<Terem, Void> muveletekCloumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        sorColumn.setCellValueFactory(new PropertyValueFactory<>("sorok"));
        oszlopColumn.setCellValueFactory(new PropertyValueFactory<>("oszlopok"));
        muveletekCloumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Törlés");
            private final Button editBtn = new Button("Frissít");

            {
                deleteBtn.setOnAction(event -> {
                    Terem t = getTableRow().getItem();
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törli a termet?", ButtonType.YES, ButtonType.NO);
                    al.setHeaderText("Megerősítés");
                    al.showAndWait().ifPresent(buttonType -> {
                        if (buttonType.equals(ButtonType.YES)) {
                            dao.delete(t.getId());
                        }
                    });

                    refreshTable();
                });
                editBtn.setOnAction(event -> {
                    Terem t = getTableRow().getItem();
                    editRow(t);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn, deleteBtn);
                    container.setSpacing(12.0);
                    setGraphic(container);
                }
            }
        });
    }

    private void editRow(Terem t) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_terem_from.fxml");
        AddEditTeremController controller = fxmlLoader.getController();
        controller.setTerem(t);
    }

    public void addRow() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_terem_from.fxml");
        AddEditTeremController controller = fxmlLoader.getController();
        controller.setTerem(new Terem());
    }

    private void refreshTable() {
        teremTable.getItems().setAll(dao.listaz());
    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    public void goToMenu() {
        App.loadFXML("/fxml/main_window.fxml");
    }
}
