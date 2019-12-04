package VisualPart.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import CodePart.Matrix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MatrixWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea TextArea;

    @FXML
    private Button BackB;

    @FXML
    private Button OutB;

    @FXML
    private TextArea TextArea1;

    @FXML
    private TextArea TextArea2;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private Button FinalB;

    Matrix matrix = new Matrix(1,true);
    Matrix matrix1 = new Matrix(1,true);
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    void FileFinder(MouseEvent event) {
        File file = fileChooser.showOpenDialog(TextArea.getScene().getWindow());
        if (file != null){
            try {
                TextArea.clear();
                matrix.setMatrixFile(file.getAbsolutePath());
                TextArea.appendText(matrix.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void FileFinder1(MouseEvent event) {
        File file = fileChooser.showOpenDialog(TextArea1.getScene().getWindow());
        if (file != null){
            try {
                TextArea1.clear();
                matrix1.setMatrixFile(file.getAbsolutePath());
                TextArea1.appendText(matrix1.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        LSet();
        final boolean[] act = {false};
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
        OutB.setOnAction(actionEvent -> {
            File file = fileChooser.showOpenDialog(OutB.getScene().getWindow());
            if ((file != null) && (act[0])){
                TextArea2.clear();
                matrix.writeMatrixFile(file.getAbsolutePath());
                TextArea2.appendText("Done!");
            }
        });
        FinalB.setOnAction(actionEvent -> {
            if(ComboBox.getSelectionModel().isSelected(0)){
                TextArea.clear();
                TextArea.appendText(matrix.toString());
                matrix.sumMatrix(matrix1);
                TextArea2.clear();
                TextArea2.appendText(matrix.toString());
                act[0] = true;
            } else if (ComboBox.getSelectionModel().isSelected(1)){
                TextArea.clear();
                TextArea.appendText(matrix.toString());
                matrix.subMatrix(matrix1);
                TextArea2.clear();
                TextArea2.appendText(matrix.toString());
                act[0] = true;
            } else if (ComboBox.getSelectionModel().isSelected(2)){
                TextArea.clear();
                TextArea.appendText(matrix.toString());
                matrix.multiMatrixMat(matrix1);
                TextArea2.clear();
                TextArea2.appendText(matrix.toString());
                act[0] = true;
            } else if (ComboBox.getSelectionModel().isSelected(3)){
                TextArea.clear();
                TextArea.appendText(matrix.toString());
                matrix1.setM(matrix);
                TextArea1.clear();
                TextArea1.appendText(matrix1.toString());
                TextArea2.clear();
                TextArea2.appendText(matrix1.toString());
                act[0] = true;
            }
        });
    }
    public void LSet(){
        ObservableList<String> list = FXCollections.observableArrayList("+","-","*","=");
        ComboBox.setItems(list);
    }
}
