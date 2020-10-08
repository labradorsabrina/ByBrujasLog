package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class LoginController {

    @FXML
    private Button cancelButton;

    public void cancelButtonOnAction(ActionEvent Event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
