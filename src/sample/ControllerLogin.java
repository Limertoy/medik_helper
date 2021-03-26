package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerLogin {
    public Stage primaryStage;
    @FXML
    public Button buttonLogin, exit_button, minimalize_button;

    @FXML
    public AnchorPane dynamicPane;

    public ControllerLogin(){}


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    private void setDynamicPane(AnchorPane dynamicPane){
        this.dynamicPane.getChildren().clear();
        this.dynamicPane.getChildren().add(dynamicPane);
    }

    public void zaloguj(ActionEvent actionEvent) throws IOException {
        Parent wizytyParent = FXMLLoader.load(getClass().getResource("wizyty.fxml"));
        Scene wizytyScene = new Scene(wizytyParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(wizytyScene);
        window.show();
    }

}
