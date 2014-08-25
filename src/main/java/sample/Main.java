package sample;

import com.allegro.webapi.AllegroWebApiPortType;
import com.allegro.webapi.AllegroWebApiService;
import com.allegro.webapi.AllegroWebApiServiceLocator;
import com.allegro.webapi.SysStatusType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import javax.xml.rpc.holders.LongHolder;
import javax.xml.rpc.holders.StringHolder;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Sandbox WSDL: https://webapi.allegro.pl.webapisandbox.pl/service.php?wsdl
 * WebAPI WSDL: https://webapi.allegro.pl/service.php?wsdl
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = new Locale("pl", "PL");
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"), ResourceBundle.getBundle("bundles.Bundle", locale));
        Polyline polyline1 = new Polyline(new double[]{135, 10, 100, 80, 170, 80, 135, 10});
        polyline1.setStroke(Color.DODGERBLUE);
        polyline1.setStrokeWidth(2);
        polyline1.setFill(Color.DARKBLUE);
        Arc arc1 = new Arc(45, 60, 45, 45, 40, 100);
        arc1.setFill(Color.YELLOW);
        Circle circle1 = new Circle(45, 45, 40, Color.RED);
        Path path2 = new Path();
        path2.getElements().addAll(
                new MoveTo(100, 65),
                new CubicCurveTo(120, 20, 130, 80, 140, 45),
                new QuadCurveTo(150, 0, 160, 45),
                new ArcTo(20, 40, 0, 180, 45, true, true));
        path2.setFill(Color.WHEAT);
        path2.setStroke(Color.LIGHTBLUE);
        final String imageURL = new File("/home/beniamin/Downloads/space-wallpapers-1.jpg").toURI().toString();
        System.out.println(imageURL);
        ImageView imageView1 = new ImageView(new Image(imageURL, 200, 200, true, true, true));
        Label label1 = new Label("beginning of the world", imageView1);
        label1.setContentDisplay(ContentDisplay.TOP);
//        Group root = new Group();
//        root.getChildren().add(label1);
//        root.getChildren().add(circle1);
//        root.getChildren().add(path2);
//        root.getChildren().add(polyline1);
//        root.getChildren().add(arc1);
        Scene sample_scene = new Scene(root);
        primaryStage.setTitle("Welcome to JavaFX");
        primaryStage.setScene(sample_scene);


        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
//        launch(args);
        System.out.println("SOAP");

        AllegroWebApiService allegroWebApiService = new AllegroWebApiServiceLocator();
        AllegroWebApiPortType allegro = allegroWebApiService.getAllegroWebApiPort();

        String userLogin = "beniamin_86"; //tutaj login allegro
        String userPassword = "8e8e378e14ac5627"; //tutaj hasło allegro
        int countryCode = 1; //kod kraju - dostajemy go razem z kluczem webapi
        final String webapiKey = "s8e8e378"; //klucz webapi
        long localVersion = -1; //klucz wersji
        StringHolder sessionHandlePart = new StringHolder(); //tutaj znajdzie sie id sesji po zalogowaniu
        LongHolder userId = new LongHolder(); //tutaj znajdzie sie nasz identyfikator
        LongHolder serverTime = new LongHolder(); //czas
        SysStatusType[] sst = allegro.doQueryAllSysStatus(countryCode, webapiKey);
        for (SysStatusType sstype : sst) {
            if (sstype.getCountryId() == countryCode) {
                localVersion = sstype.getVerKey();
            }
        }
        allegro.doLogin(userLogin, userPassword, countryCode, webapiKey, localVersion, sessionHandlePart, userId, serverTime);
        //jesli nie wyskoczy zaden exception, to znaczy, że logowanie sie udalo, to wyświetlmy kilka danych
        System.out.println("Id: " + userId.value);
        System.out.println("identyfikator sesji: " + sessionHandlePart.value);
    }
}
