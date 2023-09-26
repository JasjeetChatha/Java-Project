import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControllerLoginAs {

    @FXML
    private Button student;

    @FXML
    private Button teacher;

    @FXML
    void Back(ActionEvent event) {

    }

    @FXML
    void OpenStudent(ActionEvent event) {
        try {
            Stage stageold = (Stage) student.getScene().getWindow();
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
    void OpenTeacher(ActionEvent event) {
        try {
            Stage stageold = (Stage) teacher.getScene().getWindow();
            stageold.close();
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("teacherlogin.fxml"));

            // Create a new scene
            Scene scene = new Scene(root);

            // Create a new stage
            Stage stage = new Stage();
            Image icon = new Image("book.jpg");
            stage.getIcons().add(icon);
            // set title for the window
            stage.setTitle("Teacher Login");

            // Set the scene for the stage
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
