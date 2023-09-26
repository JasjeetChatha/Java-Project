import java.io.*;
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
import javafx.event.ActionEvent;

public class ControllerE {

    @FXML
    private Button back;

    @FXML
    private Button edit;

    @FXML
    private Label stuidlbl;

    @FXML
    private TextField stuidt;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        try {
            Stage stageold = (Stage) back.getScene().getWindow();
            stageold.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("2.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Image icon = new Image("book.jpg");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Student Gradebook");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleEditButtonAction(ActionEvent event) {
        String studentID = stuidt.getText().trim();
        if (studentID.isEmpty()) {
            showAlert("Please enter a student ID");
            return;
        }
        stuidt.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split("\\s*\t\\s*");
                if (fields[0].equals(studentID)) {
                    openStudentEditWindow(fields);
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

    private void openStudentEditWindow(String[] fields) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editstudent.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Image icon = new Image("book.jpg");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Edit Student");
            ControllerEdit controller = loader.getController();
            controller.setStudentDetails(fields);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}