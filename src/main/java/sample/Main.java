package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import obiekty.Pracownik;
import obiekty.Rola;
import org.hibernate.Session;

import java.net.URL;
import java.nio.file.Paths;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Rola lekarz = new Rola();
        Rola dyrektor = new Rola();
        Rola pielegniarka = new Rola();
        Rola rejestracja = new Rola();


        lekarz.setNazwa("Lekarz");
        dyrektor.setNazwa("Dyrektor");
        pielegniarka.setNazwa("Pielęgniarka");
        rejestracja.setNazwa("Rejestracja");

        Pracownik pracownik1 = new Pracownik("lekarz@email.com", "12345", lekarz, "Jan", "Kowalski");
        Pracownik pracownik2 = new Pracownik("dyrektor@email.com", "12345", dyrektor, "Andriy", "Adamovych");
        Pracownik pracownik3 = new Pracownik("pielegniarka@email.com", "12345", pielegniarka, "Paweł", "Kulpiński");
        Pracownik pracownik4 = new Pracownik("rejestracja@email.com", "12345", rejestracja, "Dominik", "Filip");


        session.save(lekarz);
        session.save(pracownik3);
        session.save(pracownik4);
        session.save(pielegniarka);
        session.save(dyrektor);
        session.save(rejestracja);
        session.save(pracownik1);
        session.save(pracownik2);
        session.getTransaction().commit();



        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        //grab your root here
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}