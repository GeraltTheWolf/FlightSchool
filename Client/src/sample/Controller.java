package sample;

import data.RestBlogPostManager;
import data.RestUserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import model.BlogPostManager;
import model.UserManager;

import java.awt.*;

public class Controller {

    @FXML
    private TextField tfTitle;

    @FXML
    private ComboBox<String> cmbAuthors;

    @FXML
    private TextArea taContent;

    @FXML
    private CheckBox cbEnabled;

    @FXML
    private DatePicker dpLastEditedOn;

    @FXML
    private Button btnSave;

    private BlogPostManager blogPostManager;
    private UserManager userManager;

    private ObservableList<String> authorsObservableList;



    @FXML
    public void initialize() {
        this.blogPostManager = new RestBlogPostManager();
        this.userManager = new RestUserManager();
        authorsObservableList = FXCollections.observableArrayList(this.userManager.GetAll());
        cmbAuthors.setItems(authorsObservableList);
    }


}
