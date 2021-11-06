package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TodoController {

    @FXML
    private DatePicker addDate;

    @FXML
    private TextField addDescription;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonAll;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonCompleted;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonIncomplete;

    @FXML
    private TableColumn<?, ?> completedCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem open;

    @FXML
    private MenuItem save;

    @FXML
    private TableView<?> tableView;
    //Set property to store current editing task info
    //Initialize locations and resources
    //Start setup table column
    //Set up a Filter
    //Sort List to table


    @FXML
    void onAdd(ActionEvent event) {
        //Create new task from data
        //Clear them

    }

    @FXML
    void onAll(ActionEvent event) {
        //Use filterList set predicate true

    }

    @FXML
    void onClear(ActionEvent event) {
        //Use data.clear

    }

    @FXML
    void onCompleted(ActionEvent event) {
        //Use filterList set predicate to getCompleted to .get


    }

    @FXML
    void onDelete(ActionEvent event) {
        //Use data.remove to selection and index

    }

    @FXML
    void onEdit(ActionEvent event) {
        //If not in edit
        //Find selected task from table
        //If task is selected
        //Then set editIndex to selected row index
        //Change text of edit button to "Save"

        //If in edit mode
        //Find selected task from table
        //Update task details with form data

    }

    @FXML
    void onExit(ActionEvent event) {
        //Platform.exit

    }

    @FXML
    void onIncomplete(ActionEvent event) {
        //Use filterList set predicate to doesn't getCompleted to .get

    }

    @FXML
    void onOpen(ActionEvent event) {
        //Use FileChooser to open
        //Clear current tasks from data list
        //Scan File
        //Read date and parse
        //Read description
        //add task to data
        //refresh table
        //Catch Exceptions

    }

    @FXML
    void onSave(ActionEvent event) {
        //Use FileChooser to save
        //Use PrintWriter to write to file
        //Loop for each task in list
        //Write current task date to file and description
        //Catch Exceptions
    }

}
