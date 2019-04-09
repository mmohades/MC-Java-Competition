package Views;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 

public class JavaFXCSVTableView {
 
    public class Record {
        //Assume each record have 6 elements, all String
 
        private SimpleStringProperty f1, f2, f3, f4, f5, f6;
 
        public String getF1() {
            return f1.get();
        }
 
        public String getF2() {
            return f2.get();
        }
 
        public String getF3() {
            return f3.get();
        }
 
        public String getF4() {
            return f4.get();
        }
 
        public String getF5() {
            return f5.get();
        }
 
        public String getF6() {
            return f6.get();
        }
 
        Record(String f1, String f2, String f3, String f4,
                String f5, String f6) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
            this.f3 = new SimpleStringProperty(f3);
            this.f4 = new SimpleStringProperty(f4);
            this.f5 = new SimpleStringProperty(f5);
            this.f6 = new SimpleStringProperty(f6);
        }
 
    }
 
    private final TableView<Record> tableView = new TableView<>();
 
    private final ObservableList<Record> dataList
            = FXCollections.observableArrayList();
 
    public void start(Stage primaryStage, String csvFile) {
        primaryStage.setTitle("Competition result");


        TableColumn columnF1 = new TableColumn("Team Number");
        columnF1.setCellValueFactory(
                new PropertyValueFactory<>("f1"));
 
        TableColumn columnF2 = new TableColumn("Student Name");
        columnF2.setCellValueFactory(
                new PropertyValueFactory<>("f2"));
 
        TableColumn columnF3 = new TableColumn("School");
        columnF3.setCellValueFactory(
                new PropertyValueFactory<>("f3"));
 
        TableColumn columnF4 = new TableColumn("Level");
        columnF4.setCellValueFactory(
                new PropertyValueFactory<>("f4"));
 
        TableColumn columnF5 = new TableColumn("Score");
        columnF5.setCellValueFactory(
                new PropertyValueFactory<>("f5"));
 
        TableColumn columnF6 = new TableColumn("Place");
        columnF6.setCellValueFactory(
                new PropertyValueFactory<>("f6"));
 
        tableView.setItems(dataList);
        tableView.getColumns().addAll(
                columnF1, columnF2, columnF3, columnF4, columnF5, columnF6);



        tableView.prefHeightProperty().bind(primaryStage.heightProperty());
        tableView.prefWidthProperty().bind(primaryStage.widthProperty());

        primaryStage.setScene(new Scene(tableView, 600, 400));
        primaryStage.show();
        readCSV(csvFile);

// using vbox
//        Group root = new Group();
//        VBox vBox = new VBox();
//        vBox.setSpacing(10);
//        vBox.getChildren().add(tableView);
//        vBox.setFillWidth(true);
        //layout
//        tableView.setPadding(new Insets(5, 8, 5, 8));
//        vBox.setPadding(new Insets(5, 8, 5, 8));

//        root.getChildren().add(vBox);
//
//        primaryStage.setScene(new Scene(root, 800, 400));
//        primaryStage.show();

    }
 
    private void readCSV(String CsvFile) {
 
        String FieldDelimiter = ",";
 
        BufferedReader br;
 
        try {
            br = new BufferedReader(new FileReader(CsvFile));
 
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                if(firstLine)
                    firstLine = false;

                else {
                    String[] fields = line.split(FieldDelimiter, -1);

                    Record record = new Record(fields[0], fields[1], fields[2],
                            fields[3], fields[4], fields[5]);
                    dataList.add(record);
                }
 
            }
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaFXCSVTableView.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaFXCSVTableView.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
 
    }

 
}