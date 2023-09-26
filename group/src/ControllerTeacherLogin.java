import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControllerTeacherLogin {
    @FXML
    private Button back;

    @FXML
    private Button login;

    @FXML
    private PasswordField pwt;

    @FXML
    private TextField unt;

    @FXML
    void loginbtn(ActionEvent event) {
        if (unt.getText().equals("parul") && pwt.getText().equals("kantaria"))
            try {
                Stage stageold = (Stage) login.getScene().getWindow();
                stageold.close();
                // Load the FXML file
                Parent root = FXMLLoader.load(getClass().getResource("2.fxml"));

                // Create a new scene
                Scene scene = new Scene(root);

                // Create a new stage
                Stage stage = new Stage();

                // set title for the window
                stage.setTitle("Student Gradebook");

                // Set the scene for the stage
                stage.setScene(scene);

                // Show the stage
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Details");
            alert.showAndWait();

        }
    }

    @FXML
    void backbtn(ActionEvent event) {
        try {
            Stage stageold = (Stage) back.getScene().getWindow();
            stageold.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginas.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Image icon = new Image("book.jpg");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Login As");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}