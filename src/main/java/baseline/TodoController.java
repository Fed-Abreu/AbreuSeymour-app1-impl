package baseline;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoController implements Initializable {

    @FXML
    private DatePicker addDate;

    @FXML
    private TextField addDescription;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private TableColumn<Task, Boolean> completedCol;

    @FXML
    private TableColumn<Task, String> dateCol;

    @FXML
    private TableColumn<Task, String> descriptionCol;

    @FXML
    private TableView<Task> tableView;

    @FXML
    private MenuItem open;

    @FXML
    private MenuItem save;

    @FXML
    private MenuItem exit;

    @FXML
    private Button buttonAll;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonCompleted;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonIncomplete;


    ObservableList<Task> data;
    FilteredList<Task> filteredList;


    //Set property to store current editing task info
    private int editIndex = -1;
    FileChooser fileChooser;

    //Initialize locations and resources
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        data = FXCollections.observableArrayList();
        tableView.setEditable(true);

    //Start setup table column
        completedCol.setEditable(true);
        dateCol.setEditable(false);
        descriptionCol.setEditable(false);

        completedCol.setCellValueFactory(
                param -> param.getValue().getCompleted());
        completedCol.setCellFactory( CheckBoxTableCell.forTableColumn(completedCol) );
        completedCol.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setCompleted(t.getNewValue())
        );

        dateCol.setCellValueFactory(new PropertyValueFactory<>("Deadline"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());


        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(t -> (t.getTableView().getItems().get(t.getTablePosition().getRow())
                ).setDescription(t.getNewValue())
        );

    //Set up a Filter
        filteredList = new FilteredList<>(data, task -> true);

        //Sort List to table
        SortedList<Task> sortableData = new SortedList<>(filteredList);
        tableView.setItems(sortableData);
        sortableData.comparatorProperty().bind(tableView.comparatorProperty());
    }


    @FXML
    void onAdd() {
        //Create new task from data
        if(addDescription.getText().length() < 1 || addDescription.getText().length() > 256){
            Alert al = new Alert(Alert.AlertType.ERROR, "Description should be 1 to 256 characters long.");
            al.show();
        }
        else {
            data.add(new Task(addDescription.getText(), addDate.getValue()));
        //Clear them
            addDate.setValue(null);
            addDescription.clear();
        }

    }

    @FXML
    void onAll() {
        //Use filterList set predicate true
        filteredList.setPredicate(t -> true);

    }

    @FXML
    void onClear() {
        //Use data.clear
        data.clear();
    }

    @FXML
    void onCompleted() {
        //Use filterList set predicate to getCompleted to .get
        filteredList.setPredicate(t ->  t.getCompleted().get() );

    }

    @FXML
    void onDelete() {
        //Use data.remove to selection and index
        data.remove(tableView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    void onEdit() {
        //If not in edit
        if( editIndex == -1) {
            //Find selected task from table
            Task t = tableView.getSelectionModel().getSelectedItem();

            //If task is selected
            if (t != null) {
                //Then set editIndex to selected row index
                editIndex = tableView.getSelectionModel().getSelectedIndex();

                //Disable add
                buttonAdd.setDisable(true);
                buttonEdit.setText("Edit");
                addDescription.setText(t.getDescription());
                addDate.setValue(t.getDeadlineDate());
            }
        }

        //If in edit mode
        else {
            //Find selected task from table
            Task et = tableView.getItems().get(editIndex);

            //Update task details with form data
            et.setDeadlineDate(addDate.getValue());
            et.setDescription(addDescription.getText());

            //Clear fields
            addDate.setValue(null);
            addDescription.clear();

            //Refresh
            tableView.refresh();
            editIndex = -1;

            //Add button enables
            buttonAdd.setDisable(false);
        }
    }

    @FXML
    void onExit() {
        //Platform.exit
        Platform.exit();

    }

    @FXML
    void onIncomplete() {
        //Use filterList set predicate to do not getCompleted to .get
        filteredList.setPredicate(t ->  !t.getCompleted().get() );
    }

    @FXML
    void onOpen() {
        //Use FileChooser to open
        File opensFile = fileChooser.showOpenDialog(null);
        if (opensFile != null) {
            try {
                //Clear current tasks from data list
                data.clear();
                //Scan File
                Scanner sc = new Scanner(opensFile);
                while (sc.hasNextLine()) {
                    //Read date and parse
                    LocalDate dt = LocalDate.parse(sc.nextLine());

                    //Read description
                    String description = sc.nextLine();

                    //add task to data
                    Task t = new Task(description, dt);
                    data.add(t);
                }
                //refresh table
                tableView.refresh();
                sc.close();
                //Catch Exceptions
            } catch (IOException ex) {
                //Print if any error occurs
                Logger.getLogger(TodoListApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void onSave() {
        //Use FileChooser to save
        File savesFile = fileChooser.showSaveDialog(null);
        if (savesFile != null) {
            try {
        //Use PrintWriter to write to file
                PrintWriter pw = new PrintWriter(savesFile);
                //Loop for each task in list
                for (Task t: data){

        //Write current task date to file and description
                    pw.write(t.getDeadline() + '\n');
                    pw.write(t.getDescription() + '\n');
                }
                pw.close();
                //Catch Exceptions
            } catch (IOException ex) {

                Logger.getLogger(TodoListApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
