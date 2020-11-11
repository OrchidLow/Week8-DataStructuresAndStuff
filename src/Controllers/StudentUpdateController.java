package Controllers;

import Models.Course;
import Models.Student;
import Utilities.MagicData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentUpdateController implements Initializable {

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> avgGradeCol;

    @FXML
    private TableColumn<Student, Integer> numOfCoursesCol;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<Course> coursesComboBox;

    @FXML
    private Spinner<Integer> gradeSpinner;

    @FXML
    private Label rowsReturnedLabel;

    @FXML
    private Label studentSelectedLabel;

    @FXML
    private Button addCourseButton;

    private ArrayList<Student> allStudents;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allStudents = MagicData.getStudents();
        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("studNum"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<>("avgGradeString"));
        numOfCoursesCol.setCellValueFactory(new PropertyValueFactory<>("numOfCourses"));
        tableView.getItems().addAll(MagicData.getStudents());

        //configure the selection model (how many rows you can select)
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldStudent, newStudent)-> {
                    studentSelectedLabel.setText(newStudent.toString());
                });

        //Configure the combobox
        coursesComboBox.setPromptText("Select a course");
        coursesComboBox.getItems().addAll(MagicData.getCourseCodes());

        //configure the spinner object
        SpinnerValueFactory<Integer> gradeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 75);
        gradeSpinner.setValueFactory(gradeFactory);
        gradeSpinner.setEditable(true);
        TextField spinnerEditor = gradeSpinner.getEditor();
        spinnerEditor.textProperty().addListener((observableValue, oldValue, newValue)->
        {
            try {
                Integer.parseInt(newValue);
            } catch (NumberFormatException e)
            {
                spinnerEditor.setText(oldValue);
            }
        });

        // Search bar
        searchTextField.textProperty().addListener((obs, oldValue, newValue)->{

            // Create new Array list to hold objects which match the filtered string
            ArrayList<Student> filtered =  new ArrayList<>();
            for (Student student : allStudents)
            {
                /*
                 * The "contains" method is the name of the method we defined in the class
                 * It can have any name
                 */
                if (student.contains(searchTextField.getText()))    // only add students that match the searched text
                    filtered.add(student);
            }

            tableView.getItems().clear();           // clears the table view
            tableView.getItems().addAll(filtered);  // repopulates tableview with the filtered ArrayList
        });

        updateLabels();
    }

    private void updateLabels()
    {
        rowsReturnedLabel.setText("Rows returned: " + tableView.getItems().size());
    }

    @FXML
    private void addGrade()
    {
        Student student = tableView.getSelectionModel().getSelectedItem();
        Course course = coursesComboBox.getValue();
        int grade = gradeSpinner.getValue();

        if (student != null && course != null && grade>=0 && grade <= 100)
        {
            student.addCourse(course, grade);
        }
        updateLabels();
    }
}
