import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button addstu;

    @FXML
    private Button editstu;

    @FXML
    private Button exit;

    @FXML
    private Label exmark;

    @FXML
    private Button logout;

    @FXML
    private Label midmark;

    @FXML
    private Button refresh;

    @FXML
    private Button removestu;

    @FXML
    private Label resultlbl;

    @FXML
    private Label stuid;

    @FXML
    private Label stuname;

    @FXML
    private TextArea textarea;

    @FXML
    private Label totalmarks;

    @FXML
    void initialize() {

        addstu.setOnAction(event -> {
            try {
                Stage stageold = (Stage) editstu.getScene().getWindow();
                stageold.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("3.fxml"));
                Parent root = loader.load();
                Image icon = new Image("book.jpg");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.getIcons().add(icon);
                stage.getIcons().add(icon);
                stage.setScene(scene);
                stage.setTitle("Add a Student");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        editstu.setOnAction(event -> {
            try {
                Stage stageold = (Stage) editstu.getScene().getWindow();
                stageold.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
                Parent root = loader.load();
                Image icon = new Image("book.jpg");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.getIcons().add(icon);
                stage.getIcons().add(icon);
                stage.setScene(scene);
                stage.setTitle("Edit Student");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        removestu.setOnAction(event -> {
            try {
                Stage stageold = (Stage) editstu.getScene().getWindow();
                stageold.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Image icon = new Image("book.jpg");
                Stage stage = new Stage();
                stage.getIcons().add(icon);
                stage.setScene(scene);

                stage.setTitle("Delete Student");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        logout.setOnAction(event -> {
            try {
                Alert log = new Alert(Alert.AlertType.CONFIRMATION);
                log.setTitle("Confirm Logout");
                log.setHeaderText(null);
                log.setContentText("Are you sure you want to Logout?");
                log.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {

                            Stage stageold = (Stage) logout.getScene().getWindow();
                            stageold.close();
                            // Load the FXML file
                            Parent root = FXMLLoader.load(getClass().getResource("teacherlogin.fxml"));

                            // Create a new scene
                            Scene scene = new Scene(root);
                            Image icon = new Image("book.jpg");

                            // Create a new stage
                            Stage stage = new Stage();
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
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        /*
         * refresh.setOnAction(event -> {
         * loadData();
         * });
         */
        exit.setOnAction(event -> {
            // adding alert for the confirmation
            Alert exi = new Alert(Alert.AlertType.CONFIRMATION);
            exi.setTitle("Confirm Exit");
            exi.setHeaderText(null);
            exi.setContentText("Are you sure you want to exit?");
            exi.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.exit(0);
                }
            });
        });

        refresh.setOnAction(event -> {
            loadData();
        });
        loadData();
    }

    private void loadData() {
        try {
            // Read file contents
            List<String> lines = Files.readAllLines(Paths.get("data.txt"));

            // Convert list of lines to a single string
            String content = String.join("\n", lines);

            // Set text area content
            textarea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
