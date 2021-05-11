package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import org.example.dao.FoglalasDao;
import org.example.dao.TeremDao;
import org.example.model.Foglalas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class EditFoglalasController implements Initializable {
    @FXML
    public Button go;
    private final FoglalasDao dao = new FoglalasDao();

    @FXML
    private TextField foglalo;
    @FXML
    private TextField vetitesid;
    @FXML
    private TextField szekid;

    private Foglalas foglalas;


    public void setFoglalas(Foglalas t) {
        foglalas = t;
        foglalo.textProperty().bindBidirectional(foglalas.foglaloNevProperty());
        vetitesid.textProperty().bindBidirectional(foglalas.vetitesIdProperty(), new NumberStringConverter());
        szekid.textProperty().bindBidirectional(foglalas.szekIdProperty(), new NumberStringConverter());
    }

    public void cancel() {
        App.loadFXML("/fxml/foglalas_window.fxml");

    }

    public void save() {
        foglalas = dao.descfoglalasFrissit(foglalas);
        App.loadFXML("/fxml/foglalas_window.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        go.disableProperty().bind(Bindings.createBooleanBinding(
                () -> !(Pattern.matches("[0-9]+", vetitesid.textProperty().get())) || !(Pattern.matches("[0-9]+", szekid.textProperty().get())),
                vetitesid.textProperty(), szekid.textProperty()));


    }
}
