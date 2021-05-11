package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import org.example.dao.TeremDao;
import org.example.model.Terem;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class AddEditTeremController implements Initializable {
    @FXML
    public Button go;
    private final TeremDao dao = new TeremDao();

    @FXML
    private TextField oszlopszam;
    @FXML
    private TextField sorszam;

    private Terem terem;


    public void setTerem(Terem t) {
        terem = t;
        sorszam.textProperty().bindBidirectional(terem.sorokProperty(), new NumberStringConverter());
        oszlopszam.textProperty().bindBidirectional(terem.oszlopokProperty(), new NumberStringConverter());
    }

    public void cancel() {
        App.loadFXML("/fxml/terem_window.fxml");

    }

    public void save() {
        terem = dao.hozzaad(terem);
        App.loadFXML("/fxml/terem_window.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        go.disableProperty().bind(Bindings.createBooleanBinding(
                () -> !(Pattern.matches("[0-9]+", sorszam.textProperty().get())) || !(Pattern.matches("[0-9]+", oszlopszam.textProperty().get())),
                sorszam.textProperty(), oszlopszam.textProperty()));


    }
}
