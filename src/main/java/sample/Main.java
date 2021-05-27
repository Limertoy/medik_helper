package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import obiekty.*;
import org.dom4j.DocumentException;
import org.hibernate.Session;
//import com.mycompany.PDFGenerator.PDF;

import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

//import static com.mycompany.PDFGenerator.PDF.generateChoroby;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    Seeder seeder = new Seeder();

    public static final String PDF_DEST = System.getProperty("user.home") + "/Desktop/PDFy";

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getClassLoader().getResource("sample.fxml");
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
            public void handle(MouseEvent event){
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