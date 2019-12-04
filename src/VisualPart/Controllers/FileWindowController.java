package VisualPart.Controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CodePart.Matrix;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class FileWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea TextArea;

    @FXML
    private Button WriteB;

    @FXML
    private Button ReadB;

    @FXML
    private Button BackB;

    private Desktop desktop = Desktop.getDesktop();

    @FXML
    void initialize() {
        final FileChooser fileChooser = new FileChooser();
        Matrix matrix = new Matrix(1,true);
        TextArea.appendText("Basic " + matrix.toString());
        BackB.setOnAction(actionEvent -> {
            BackB.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/MainWindow.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Course work 17");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ReadB.setOnAction(actionEvent -> {
            File file = fileChooser.showOpenDialog(ReadB.getScene().getWindow());
            if (file != null){
                try {
                    TextArea.clear();
                    matrix.setMatrixFile(file.getAbsolutePath());
                    TextArea.appendText(matrix.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        WriteB.setOnAction(actionEvent -> {
            File file = fileChooser.showOpenDialog(ReadB.getScene().getWindow());
            if (file != null){
                TextArea.clear();
                matrix.writeMatrixFile(file.getAbsolutePath());
                TextArea.appendText("Done!");
            }
        });

    }
    private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
