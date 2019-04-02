package Controllers;


import Models.FileFormatException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainViewController {


    @FXML
    private Label descriptionLabel, titleLabel;
    private List<File> files;

    @FXML
    private Button importFilesButton , exportResultButton;



    @FXML
    public void initialize() {

        exportResultButton.setDisable(true);
        importFilesButton.setTooltip(new Tooltip("Import two CSV Files"));
        exportResultButton.setTooltip(new Tooltip("Export the competition result"));

    }


    @FXML
    public void importFilesButtonPressed(){

        List<File> files = fileChooser("Import Judges and Students info","", true);


        if(files != null && files.size() != 2)
                showError("You didn't choose two files. Please only choose judges info and student ino CSV files.");
        else {
            if(files!=null) {

                this.files = files;
                exportResultButton.setDisable(false);
            }
        }



    }


    @FXML
    public void exportResultButtonPressed(){

        CSVController csvController = new CSVController();
        String out = "test.csv";

        exportResultButton.setDisable(true);



        if(this.files != null && this.files.size()==2) {


            List<File> files = fileChooser("Export Result","Competition Result",
                    false);

            if(files.size() == 1)
            {

                try {
                    csvController.export(this.files.get(0), this.files.get(1), files.get(0));

                } catch (IOException e) {

                    showError(e.getMessage());

                } catch (FileFormatException e) {
                    showError(e.getMessage());
                }

            }

            else{

                showError("Nothing was given as the output. Please only choose one file.");
            }


        }
        else
            showError("Not enough input is given. Please import two files first.");

        this.files = null;

    }


    private List<File> fileChooser(String titleActionText, String initFileName, boolean allowMultipleFiles) {

        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = new ArrayList<>();

        fileChooser.setInitialFileName(initFileName);
        fileChooser.setTitle(titleActionText);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.CSV)", "*.csv");

        fileChooser.getExtensionFilters().addAll(extFilter);

        if(allowMultipleFiles)

          selectedFiles = fileChooser.showOpenMultipleDialog(importFilesButton.getScene().getWindow());

        else
            selectedFiles.add(fileChooser.showSaveDialog(null));


        return selectedFiles;
    }



    private void showError(String message){

        Alert error = new Alert(Alert.AlertType.ERROR);

        error.setTitle("Error");
        error.setHeaderText(message);
//        error.setContentText(message);

        error.showAndWait();

    }

    public void exitButtonPressed(ActionEvent actionEvent) {

        System.exit(0);
    }
}
