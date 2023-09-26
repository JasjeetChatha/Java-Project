import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControllerStudentDetails {

    @FXML
    private Button exit;

    @FXML
    private Label fexam;

    @FXML
    private Label ffname;

    @FXML
    private Label finalmarks;

    @FXML
    private Label firstname;

    @FXML
    private Label fname;

    @FXML
    private Label fullname;

    @FXML
    private Label lastname;

    @FXML
    private Label lname;

    @FXML
    private Label mexam;

    @FXML
    private Label midtermmarks;

    @FXML
    private Label overallg;

    @FXML
    private Label g;

    @FXML
    private Button result;

    @FXML
    void Exit(ActionEvent event) {
        try {
            Stage stageold = (Stage) exit.getScene().getWindow();
        stageold.close();
        
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("loginstudent.fxml"));

            // Create a new scene
            Scene scene = new Scene(root);

            // Create a new stage
            Stage stage = new Stage();
            Image icon = new Image("book.jpg");
            stage.getIcons().add(icon);
            // set title for the window
            stage.setTitle("Student Login");

            // Set the scene for the stage
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ShowResult(ActionEvent event) {
        if (Double.parseDouble(g.getText()) >= 50) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Congratulations!");
            alert.setContentText("You passed");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("You FAILED!");
            alert.setContentText("");
            alert.showAndWait();
        }
    }

    public void setStudentDetails(String[] fields) {
        String[] nameParts = fields[1].split("\\s+");
        ffname.setText(fields[1]);
        fname.setText(nameParts[0]);
        lname.setText(nameParts[nameParts.length - 1]);
        mexam.setText(fields[2]);
        fexam.setText(fields[3]);
        g.setText(fields[4]);

    }

}