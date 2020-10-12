package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button cancelButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;


    public void initialize(URL url, ResourceBundle resourceBundle){

        File shieldFile = new File("Images/ruby.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);

    }

    public void registerButtonOnAccion(ActionEvent event){

        if(passwordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            //confirmPasswordLabel.setText("You are set!");
        }else{
            confirmPasswordLabel.setText("Password does not match!");
        }

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }



    public void registerUser() {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
        String user = "admin", pass = "root";

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        String insertFields = "INSERT INTO users_table(firstname, lastname, username, password) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;


        //String verifyCommand = "SELECT count(1) FROM users_table WHERE username = '" + usernameTextField.getText() + "' AND password ='" + enterPasswordField.getText() + "'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pass);
            statement = connect.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel.setText("User has been registered successfully!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }


}
