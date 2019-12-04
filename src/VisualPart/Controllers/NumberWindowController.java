package VisualPart.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class NumberWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea TextArea;

    @FXML
    private Button BackB;

    @FXML
    private TextArea TextArea1;

    @FXML
    private TextArea TextArea2;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private Button FinalB;

    @FXML
    private Button OutB;

    Matrix matrix = new Matrix(1,true);
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
                matrix.multiMatrixNum(Integer.parseInt(TextArea1.getText().trim()));
                TextArea2.clear();
                TextArea2.appendText(matrix.toString());
                act[0] = true;
            } else if (ComboBox.getSelectionModel().isSelected(1)){
                TextArea.clear();
                TextArea.appendText(matrix.toString());
                String tempS = TextArea1.getText().trim();
                String[] temps = tempS.split(" ");
                int [] tempI = new int[temps.length];
                for (int i = 0; i < temps.length;i++){
                    tempI[i] = Integer.parseInt(temps[i].trim());
                }
                matrix.multiMatrixVec(tempI);
                TextArea2.clear();
                TextArea2.appendText(matrix.toString());
                act[0] = true;
            }
        });
    }

    private void LSet(){
        ObservableList<String> list = FXCollections.observableArrayList("Число","Вектор");
        ComboBox.setItems(list);
    }
}