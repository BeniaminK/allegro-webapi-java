package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    public Controller()
    {
        System.out.println("Controller()");
    }

    @FXML
    protected void doSomething()
    {
        System.out.println("doSomething.");
    }
}

