package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import obiekty.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class ControllerLogin {
    public Stage primaryStage;
    @FXML
    public Button buttonLogin, exit_button, minimalize_button;
    public TextField textFieldLogin;

    @FXML
    public AnchorPane dynamicPane;
    public TextField textFieldPassword;
    public Text textNiepoprawny;
    public Text textLadowanie;
    public static int id_sesji;


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

    public void przejdz(ActionEvent a, String s1) throws IOException {
        URL url = getClass().getClassLoader().getResource(s1);
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)a.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void zaloguj(ActionEvent actionEvent) throws IOException {
        textNiepoprawny.setVisible(false);
        textLadowanie.setVisible(true);
        Session session = HibernateUtil.getSessionFactory().openSession();

        String login, haslo;
        login = textFieldLogin.getText();
        haslo = textFieldPassword.getText();
        System.out.println(login);

        Query pr = session.createQuery("from Pracownik  WHERE email=:email AND haslo=:haslo", Pracownik.class);
        pr.setParameter("email", login);
        pr.setParameter("haslo", haslo);
        List<Pracownik> resultList = pr.list();

        for (Pracownik s : resultList) {
            if (s.getRola().getId_rola() == 1) {
                id_sesji = s.getId_pracownika();
                textLadowanie.setVisible(true);
                przejdz(actionEvent, "lekarz/wizyty.fxml");
            } else if (s.getRola().getId_rola() == 2) {
                id_sesji = s.getId_pracownika();
                textLadowanie.setVisible(true);
                przejdz(actionEvent, "pielegniarka/pielegniarkaPacjenci.fxml");
            } else if (s.getRola().getId_rola() == 3) {
                id_sesji = s.getId_pracownika();
                textLadowanie.setVisible(true);
                przejdz(actionEvent, "dyrektor/dyrektorZamawianie.fxml");
            } else if (s.getRola().getId_rola() == 4) {
                id_sesji = s.getId_pracownika();
                textLadowanie.setVisible(true);
                przejdz(actionEvent, "rejestracja/rejestracja.fxml");
            }
        }
        textLadowanie.setVisible(false);
        textNiepoprawny.setVisible(true);
    }

}
