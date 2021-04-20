package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerLogin {
    public Stage primaryStage;
    @FXML
    public Button buttonLogin, exit_button, minimalize_button;
    public TextField textFieldLogin;

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

        String login;
        login = textFieldLogin.getText();
        System.out.println(login);

        if (login.equals("lekarz"))
        {
            URL url = Paths.get("./src/main/java/lekarz/wizyty.fxml").toUri().toURL();
            Parent Parent = FXMLLoader.load(url);
            Scene Scene = new Scene(Parent);

            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

            window.setScene(Scene);
            window.show();
        }
        else if (login.equals("p"))
        {
            URL url = Paths.get("./src/main/java/pielegniarka/wydawanieLekow.fxml").toUri().toURL();
            Parent Parent = FXMLLoader.load(url);
            Scene Scene = new Scene(Parent);

            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

            window.setScene(Scene);
            window.show();
        }
        else {
            URL url = Paths.get("./src/main/java/rejestracja/rejestracja.fxml").toUri().toURL();
            Parent Parent = FXMLLoader.load(url);
            Scene Scene = new Scene(Parent);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(Scene);
            window.show();
        }
    }

}
