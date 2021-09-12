package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView closeImage;

    @FXML
    private ImageView userImage;

    @FXML
    private ImageView passwordImage;

    @FXML
    private Button closeButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    public Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;


    public void validateLogin() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + userNameField.getText() + "' AND password ='" + passwordField.getText() + "'";

        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Congratulations!");
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event) {

        if (userNameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File branding = new File("/Users/denisdanailov/Downloads/LogIn/img/logo-BIG.png");
        Image brandingImage = new Image(branding.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File closeImg = new File("/Users/denisdanailov/Downloads/LogIn/img/close-icon-white.png");
        Image closeImage1 = new Image(closeImg.toURI().toString());
        closeImage.setImage(closeImage1);

        File userImg = new File("/Users/denisdanailov/Downloads/LogIn/img/pngegg.png");
        Image userImg1 = new Image(userImg.toURI().toString());
        userImage.setImage(userImg1);

        File passImg = new File("/Users/denisdanailov/Downloads/LogIn/img/pngegg (1).png");
        Image passwordImg = new Image(passImg.toURI().toString());
        passwordImage.setImage(passwordImg);



    }
}

