import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;

public class ControllerEdit {

    @FXML
    private Button clear;

    @FXML
    private Button editstu;

    @FXML
    private Button exit;

    @FXML
    private Label lfinalmarks;

    @FXML
    private Label lmidmarks;

    @FXML
    private Label lstuid;

    @FXML
    private Label lstuname;

    @FXML
    private Label lstuname1;

    @FXML
    private TextField tfinalmarks;

    @FXML
    private TextField tmidmarks;

    @FXML
    private TextField tstuid;

    @FXML
    private TextField tstuname;

    @FXML
    private TextField tstuname1;

    @FXML
    void clearFields(ActionEvent event) {
        tstuid.setText("");
        tstuname.setText("");
        tstuname1.setText("");
        tmidmarks.setText("");
        tfinalmarks.setText("");
    }

    @FXML
    void editStudent(ActionEvent event) {
        String studentID = tstuid.getText().trim();
        String firstName = tstuname.getText().trim();
        String lastName = tstuname1.getText().trim();
        String midtermMarks = tmidmarks.getText().trim();
        String finalMarks = tfinalmarks.getText().trim();

        try {
            File file = new File("data.txt");
            File tempFile = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\s*\t\\s*");
                if (fields[0].equals(studentID)) {
                    line = String.format("%d\t\t\t\t%-1s %s\t\t\t%d\t\t\t\t%d\t\t\t\t%d\t\t\t%s",
                            Integer.parseInt(studentID), firstName, lastName, Integer.parseInt(midtermMarks),
                            Integer.parseInt(finalMarks), Integer.parseInt(midtermMarks) + Integer.parseInt(finalMarks),
                            (Integer.parseInt(finalMarks) + Integer.parseInt(midtermMarks))/2 >= 50 ? "PASS" : "FAIL");

                }
                writer.write(line + System.getProperty("line.separator"));
            }
            reader.close();
            writer.close();
            file.delete();
            tempFile.renameTo(file);
            showAlert("Student record updated successfully");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    @FXML
    void exitApplication(ActionEvent event) {
        try {
            Stage stageold = (Stage) editstu.getScene().getWindow();
            stageold.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Image icon = new Image("book.jpg");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Edit Student");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load student data from the file when the controller is initialized
    public void setStudentDetails(String[] fields) {
        String[] nameParts = fields[1].split("\\s+");
        tstuid.setText(fields[0]);
        tstuname.setText(nameParts[0]);
        tstuname1.setText(nameParts[nameParts.length - 1]);
        tmidmarks.setText(fields[2]);
        tfinalmarks.setText(fields[3]);
        tstuid.setDisable(true); // disable stid TextField
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}