package hu.alkfejl.controller;

import hu.alkfejl.App;

public class MainController {
    public void goToTermek() {
        App.loadFXML("/fxml/terem_window.fxml");
    }

    public void goToFilmek() {
        App.loadFXML("/fxml/filmek_window.fxml");
    }

    public void goToVetitesek() {
        App.loadFXML("/fxml/Vetitesek_window.fxml");
    }

    public void goToFoglalasok() {
        App.loadFXML("/fxml/foglalas_window.fxml");
    }
}
