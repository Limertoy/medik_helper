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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import obiekty.Pacjent;
import obiekty.Pracownik;
import obiekty.Recepty;
import obiekty.Wyposazenie;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.PacjentService;
import services.ReceptyService;
import services.WyposazenieService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import static pielegniarka.ControllerPielegniarkaPacjenci.id_zmien;

public class ControllerWydawanieLekow implements Initializable {
    public TableView<Wyposazenie> tablePacjenci;
    public TableView<Recepty> tableRecepty;
    public TableColumn nazwiskoPacjent, imiePacjent, peselPacjent, nzawaRecepta, iloscRecepta;
    private ObservableList<Wyposazenie> obsListP;
    private ObservableList<Recepty> obsListR;
    private List<Recepty> ListR2;
    Wyposazenie obiekt1, obiekt2, obiekt3, obiekt4, obiekt5;

    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    ReceptyService service;
    WyposazenieService service2;


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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
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

    public void setService(ReceptyService service) {
        this.service = service;
    }
    public void setService2(WyposazenieService service2) {
        this.service2 = service2;
    }

    public void wybierz(MouseEvent mouseEvent) {
    }


    public void wydajLeki(ActionEvent actionEvent) {

        this.setService(new ReceptyService());
        this.setService2(new WyposazenieService());
        Recepty all = tableRecepty.getSelectionModel().getSelectedItem();


        ListR2 = service.findNiewydanePacjenta(id_zmien);

        obiekt1 = all.getWyp1();
        obiekt2 = all.getWyp2();
        obiekt3 = all.getWyp3();
        obiekt4 = all.getWyp4();
        obiekt5 = all.getWyp5();

        if ((obiekt1.getIlosc_pozostalych()-all.getIlosc1())<0 	|| ((obiekt2.getIlosc_pozostalych()-all.getIlosc2())<0)
                || ((obiekt3.getIlosc_pozostalych()-all.getIlosc3())<0) ||
                ((obiekt4.getIlosc_pozostalych()-all.getIlosc4())<0) ||
                ((obiekt4.getIlosc_pozostalych()-all.getIlosc4())<0))

        {
            System.out.println("za malo na stanie");
        }
        else

        obiekt1.setIlosc_pozostalych(obiekt1.getIlosc_pozostalych()-all.getIlosc1());
        obiekt2.setIlosc_pozostalych(obiekt2.getIlosc_pozostalych()-all.getIlosc2());
        obiekt3.setIlosc_pozostalych(obiekt3.getIlosc_pozostalych()-all.getIlosc3());
        obiekt4.setIlosc_pozostalych(obiekt4.getIlosc_pozostalych()-all.getIlosc4());
        obiekt5.setIlosc_pozostalych(obiekt5.getIlosc_pozostalych()-all.getIlosc5());

        obiekt1.setIlosc_wydanych(obiekt1.getIlosc_wydanych()+all.getIlosc1());
        obiekt2.setIlosc_wydanych(obiekt2.getIlosc_wydanych()+all.getIlosc2());
        obiekt3.setIlosc_wydanych(obiekt3.getIlosc_wydanych()+all.getIlosc3());
        obiekt4.setIlosc_wydanych(obiekt4.getIlosc_wydanych()+all.getIlosc4());
        obiekt5.setIlosc_wydanych(obiekt5.getIlosc_wydanych()+all.getIlosc5());

        service2.saveOrUpdate(obiekt1);
        service2.saveOrUpdate(obiekt2);
        service2.saveOrUpdate(obiekt3);
        service2.saveOrUpdate(obiekt4);
        service2.saveOrUpdate(obiekt5);
        all.setWydane(true);
        service.saveOrUpdate(all);

        reloadDate();
        reoladRecepty();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        reoladRecepty();

    }

    public void reoladRecepty()
    {
        this.setService(new ReceptyService());
        this.setService2(new WyposazenieService());


        obsListR = FXCollections.observableArrayList(service.findNiewydanePacjenta(id_zmien));


        tableRecepty.setItems(obsListR);

        nzawaRecepta.setCellValueFactory(new PropertyValueFactory<>("opis"));
        iloscRecepta.setCellValueFactory(new PropertyValueFactory<>("id_recepty"));
    }


    public void reloadDate(){


        Recepty all = tableRecepty.getSelectionModel().getSelectedItem();
        Wyposazenie wyp =  all.getWyp1();
        Wyposazenie wyp2 = all.getWyp2();
        Wyposazenie wyp3 =  all.getWyp3();
        Wyposazenie wyp4 = all.getWyp4();
        Wyposazenie wyp5 =  all.getWyp5();
        System.out.println(all);

        obsListP = FXCollections.observableArrayList();
        obsListP.add(wyp);
        obsListP.add(wyp2);
        obsListP.add(wyp3);
        obsListP.add(wyp4);
        obsListP.add(wyp5);

        tablePacjenci.setItems(obsListP);
        nazwiskoPacjent.setCellValueFactory(new PropertyValueFactory<>("nazwa_wyposazenia"));
        imiePacjent.setCellValueFactory(new PropertyValueFactory<>("ilosc_pozostalych"));
        peselPacjent.setCellValueFactory(new PropertyValueFactory<>("typ_wyposazenia"));


    }

    public void wybierzRecepte(MouseEvent mouseEvent) {

        reloadDate();

    }
}
