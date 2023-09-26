import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControllerDelete {

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private Label stuidlbl;

    @FXML
    private TextField stuidt;

    private String filename = "data.txt";

    @FXML
    public void handleDeleteButton() {

        String studentID = stuidt.getText().trim();
        if (studentID.isEmpty()) {
            showAlert("Please enter a student ID");
            return;
        }

        // show a confirmation dialog
        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this student record?");
        confirmation.setHeaderText(null);
        confirmation.setTitle("Confirmation");
        confirmation.showAndWait();

        // check if the user clicked "OK"
        if (confirmation.getResult() == ButtonType.OK) {
            try {
                // Read the existing file and copy all the records to a new file except the one
                // to be deleted
                File oldFile = new File(filename);
                File newFile = new File("temp.txt"); // use a temporary file name
                BufferedReader reader = new BufferedReader(new FileReader(oldFile));
                FileWriter writer = new FileWriter(newFile);
                String line = reader.readLine();
                boolean found = false;
                while (line != null) {
                    String[] fields = line.split("\t");
                    if (fields[0].equals(studentID)) {
                        found = true;
                    } else {
                        writer.write(line + System.lineSeparator());
                    }
                    line = reader.readLine();
                }
                reader.close();
                writer.close();

                if (found) {
                    // Rename the new file to the original file name and delete the old file
                    if (oldFile.delete()) {
                        if (!newFile.renameTo(oldFile)) {
                            showAlert("Error: Could not rename the new file");
                        }
                    } else {
                        showAlert("Error: Could not delete the old file");
                    }
                    showAlert("Student record deleted successfully");
                    stuidt.setText("");
                } else {
                    showAlert("Error: Student record not found");
                }
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error: " + e.getMessage());
            }
        }
    }

    @FXML
    public void handleBackButton() {
        // Code to switch to the previous scene or close the current scene
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

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
