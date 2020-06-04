package application;

import DBConnection.DbConnection;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;


import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class NewNoteController implements Initializable{

	private Connection connection;
	private PreparedStatement pst;
	
	private Scene firstScene;
	
	public SimpleStringProperty auth = new SimpleStringProperty();
	public SimpleStringProperty not = new SimpleStringProperty();
	
	
	@FXML 
	private Button close = new Button();
	@FXML 
	private Button save = new Button();
	@FXML
	private TextArea textArea;
	@FXML
	private JFXTextField author = new JFXTextField();
	@FXML
	private JFXDatePicker datePicker = new JFXDatePicker();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		author.setStyle("-fx-text-inner-color:white;");
                datePicker.setStyle("-fx-text-inner-color:white;");
	}
	
	public String getAuthor() {
		return auth.get();
	}
	public void setAuthor(String author1) {
		this.auth.set(author1);
	}
	public String getNote() {
		return not.get();
	}
	public void setNote(String note1) {
		this.not.set(note1);
	}
	public void clearFields(){
            author.clear();
            textArea.clear();
            datePicker.setValue(null);
        
        }
	
	
	
	@FXML
	public void closeNewNote(ActionEvent e1) {
		close.getScene().getWindow().hide();
		Stage home = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/NotePad.fxml"));
			Scene scene = new Scene(root);
			home.setScene(scene);
			home.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void saveNewNote(ActionEvent e) throws SQLException {
		
		String insert = "INSERT INTO notepad(Author,Note,Date)"+ "VALUES(?,?,?)"; 
		DbConnection database = new DbConnection();
                connection = database.getConnection();
		
				try {
					
					pst = connection.prepareStatement(insert);
//					java.util.Date date = java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//					java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
//                                        Date dpcurrent = Date.valueOf(datePicker);
					pst.setString(1,author.getText());
					pst.setString(2,textArea.getText());
                                        pst.setString(3,((TextField)datePicker.getEditor()).getText());
//					pst.setDate(3, sqlDate);
//					pst.setTimestamp(3, sqlTime);
					
					pst.executeUpdate();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("The note by "+author.getText() + " is saved successfully");
					alert.show();
					clearFields();
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	}
		
	

	
//SWICTCH SCENE	
	@FXML
	public void close(ActionEvent e1) throws IOException{

		Stage primaryStage = (Stage)((Node)e1.getSource()).getScene().getWindow();
		primaryStage.setScene(firstScene);
		primaryStage.show();
	}
//Switch scene
	public void setFirstScene(Scene scene) {
		// TODO Auto-generated method stub
		firstScene = scene;
	}

}

