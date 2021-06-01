package pielegniarka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import obiekty.*;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.PacjentService;
import services.PracownikService;
import services.ReceptyService;
import services.WyposazenieService;

import static pielegniarka.ControllerPielegniarkaPacjenci.id_zmien;
import static sample.ControllerLogin.id_sesji;

public class ControllerGenerujRecepte implements Initializable {
    public TextArea ile1, ile2, ile3, ile4, ile5, opis;
    public ChoiceBox selectList1, selectList2, selectList3, selectList4, selectList5;
    public Text text_Pacjent, text_Lekarz;
    public WyposazenieService service;
    public ReceptyService serviceR;
    public PracownikService pracownik;
    public PacjentService pacjentService;
    private ObservableList<Wyposazenie> obsList;
    int id = id_zmien;
    public Pacjent pacjent;
    int ile11,ile22,ile33,ile44,ile55;


    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }


    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("pielegniarka/pielegniarkaPacjenci.fxml");
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }



    public void setService(WyposazenieService service) {
        this.service = service;
    }

    public void setService2(ReceptyService serviceR) {
        this.serviceR = serviceR;
    }

    public void setPracownik(PracownikService pracownikService){this.pracownik = pracownikService;}

    public void setService3(PacjentService pacjentService) {
        this.pacjentService = pacjentService;
    }

    public void generujRecepte(ActionEvent actionEvent) throws IOException {

        this.setService2(new ReceptyService());



        Wyposazenie obiekt1 = (Wyposazenie) selectList1.getValue();
        Wyposazenie obiekt2 = (Wyposazenie) selectList2.getValue();
        Wyposazenie obiekt3 = (Wyposazenie) selectList3.getValue();
        Wyposazenie obiekt4 = (Wyposazenie) selectList4.getValue();
        Wyposazenie obiekt5 = (Wyposazenie) selectList5.getValue();
        String text = opis.getText();

        setPracownik(new PracownikService());
        setService3(new PacjentService());
        Pracownik pr = pracownik.findById(id_sesji);
        pacjent = pacjentService.findById(id_zmien);

        ile11 = Integer.parseInt(ile1.getText());
        ile22 = Integer.parseInt(ile2.getText());
        ile33 = Integer.parseInt(ile3.getText());
        ile44 = Integer.parseInt(ile4.getText());
        ile55 = Integer.parseInt(ile5.getText());



        Recepty recpta = new Recepty(text,obiekt1,obiekt2,obiekt3,obiekt4,obiekt5,ile11,ile22,ile33,ile44,ile55,pr,pacjent);
        serviceR.saveOrUpdate(recpta);

        pacjenci(actionEvent);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setService(new WyposazenieService());
        setPracownik(new PracownikService());
        setService3(new PacjentService());
        Pracownik pr = pracownik.findById(id_sesji);
        pacjent = pacjentService.findById(id_zmien);

        text_Lekarz.setText(pr.getImie_pracownika()+pr.getNazwisko_pracownika());
        text_Pacjent.setText(pacjent.getImie_pacjenta()+pacjent.getNazwisko_pacjenta());


        obsList = FXCollections.observableArrayList(service.findLeki());
        selectList1.setItems(obsList);
        selectList2.setItems(obsList);
        selectList3.setItems(obsList);
        selectList4.setItems(obsList);
        selectList5.setItems(obsList);



    }
}
