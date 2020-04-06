package sample;

import data.RestBlogPostManager;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import model.BlogPostManager;

import java.awt.*;

public class Controller {

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAuthor;

    @FXML
    private TextArea taContent;

    @FXML
    private CheckBox cbEnabled;

    @FXML
    private DatePicker dpLastEditedOn;

    @FXML
    private Button btnSave;

    private BlogPostManager blogPostManager;

    public Controller() {
        this.blogPostManager = new RestBlogPostManager();
    }



}
