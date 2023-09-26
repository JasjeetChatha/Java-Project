import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controlleradd {

    @FXML
    private Button addstu;

    @FXML
    private Button clear;

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

    private ArrayList<Student> studentList = new ArrayList<>();

    @FXML
    void initialize() {
        addstu.setOnAction(event -> {
            try {
                int stuid = Integer.parseInt(tstuid.getText());
                String stuname = tstuname.getText();
                String stulastname = tstuname1.getText();
                int midtermMarks = Integer.parseInt(tmidmarks.getText());
                int finalMarks = Integer.parseInt(tfinalmarks.getText());

                // create a new Student object using the collected data
                Student student = new Student(stuid, stuname, stulastname, midtermMarks, finalMarks);
                studentList.add(student);
                student.writeToFile("data.txt");
                // alternatively, you can add the student object to a static list in the Student
                // class
                // Student.addStudent(student);
                // show a confirmation dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Added Student");
                alert.setHeaderText(null);
                alert.setContentText("Student has been added");
                alert.showAndWait();

                // clear the input fields
                tstuid.setText("");
                tstuname.setText("");
                tstuname1.setText("");
                tmidmarks.setText("");
                tfinalmarks.setText("");
            } catch (NumberFormatException e) {
                // show an error message if any of the input fields are not integers
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Invalid value entered.");
                alert.showAndWait();
            }
        });

        clear.setOnAction(event -> {
            tstuid.setText("");
            tstuname.setText("");
            tstuname1.setText("");
            tfinalmarks.setText("");
            tmidmarks.setText("");
        });

        exit.setOnAction(event -> {
            // adding alert for the confirmation
            Alert exi = new Alert(Alert.AlertType.CONFIRMATION);
            exi.setTitle("Confirm Exit");
            exi.setHeaderText(null);
            exi.setContentText("Are you sure you want to exit?");
            exi.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        Stage stageold = (Stage) exit.getScene().getWindow();
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
            });
        });
    }
}