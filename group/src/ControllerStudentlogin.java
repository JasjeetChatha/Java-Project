import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControllerStudentlogin {

    @FXML
    private Button back;

    @FXML
    private Button loginS;

    @FXML
    private TextField stuid;

    @FXML
    private Label stuidlbl;

    @FXML
    void Back(ActionEvent event) {
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

    @FXML
    void LoginToStudent(ActionEvent event) {
        String studentID = stuid.getText().trim();
        if (studentID.isEmpty()) {
            showAlert("Please enter a student ID");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split("\\s*\t\\s*");
                if (fields[0].equals(studentID)) {
                    openStudentDetailsWindow(fields);
                    return;
                }
                line = reader.readLine();
            }
            showAlert("Error: Student record not found");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openStudentDetailsWindow(String[] fields) {
        try {
            Stage stageold = (Stage) loginS.getScene().getWindow();
            stageold.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("studentdetail.fxml"));
            Parent root = loader.load();
            ControllerStudentDetails controller = loader.getController();
            controller.setStudentDetails(fields);
            Stage stage = new Stage();
            Image icon = new Image("book.jpg");
            stage.getIcons().add(icon);
            stage.setTitle("Student Info");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

}