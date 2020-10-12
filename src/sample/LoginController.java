package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.*;
import java.util.ResourceBundle;

import java.net.URL;


public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        File brandingFile = new File("Images/Profile pic.gif");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File logoFile = new File("Images/Logo-01.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File lockFile = new File("Images/pendulum.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

    }

    public void loginButtonOnAction(ActionEvent event) throws ClassNotFoundException {

        if(!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            //loginMessageLabel.setText("You Try to login");
            verifyLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }

    }

    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
    private static String user = "admin", pass = "root";
    private static String usernameSys = "sabrilabra";
    private static String passwordSys = "root";

    public void verifyLogin() {
        String verifyCommand = "SELECT count(1) FROM users_table WHERE username = '" + usernameTextField.getText() + "' AND password ='" + enterPasswordField.getText() + "'";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pass);
            statement = connect.createStatement();
            resultSet = statement.executeQuery(verifyCommand);

            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    loginMessageLabel.setText("Congratulations!");
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.DECORATED);
            registerStage.setScene(new Scene(root, 520, 592));
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void signUpHypelinkOnAccion(ActionEvent event) {
        //loginMessageLabel.setText("You're In");
        createAccountForm();
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
