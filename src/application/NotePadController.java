package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import application.NewNoteController;


import DBConnection.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.*;

	

public class NotePadController implements Initializable{
	private DbConnection database = new DbConnection();
	private Connection connection;
	private PreparedStatement pst;
	
        @FXML
        private JFXTextField author = new JFXTextField();

        @FXML
        private TextArea note;
        @FXML
	private Scene secondScene;
	@FXML
	private ResultSet rs;
	

	
	@FXML
	private JFXButton addBtn = new JFXButton();
	
        @FXML
        private JFXButton delete;

        @FXML
        private JFXButton update;
        
	
    @FXML
    private TableView<ModelNoteTable> tableView;
    

    @FXML
    private TableColumn<ModelNoteTable, String> col_id;

    @FXML
    private TableColumn<ModelNoteTable, String> col_author;

    @FXML
    private TableColumn<ModelNoteTable, String> col_note;

    @FXML
    private TableColumn<ModelNoteTable, String> col_date;
    
     final ObservableList<ModelNoteTable> oblist = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        author.setStyle("-fx-text-inner-color:white;");
        DbConnection database = new DbConnection();
         try {  
             connection = database.getConnection();
         } catch (SQLException ex) {
             Logger.getLogger(NotePadController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
         try{
                        

		
			rs = connection.createStatement().executeQuery("SELECT * FROM notepad");
                        
                        while(rs.next()){
                            oblist.add(new ModelNoteTable(rs.getString("id"),rs.getString("Author"),rs.getString("Note"),rs.getString("Date")));
                        }
                }
                catch (SQLException e1) {
                    Logger.getLogger(NotePadController.class.getName()).log(Level.SEVERE, null, e1);
                }
                
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_id.setCellFactory(TextFieldTableCell.<ModelNoteTable>forTableColumn());
                col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
                col_author.setCellFactory(TextFieldTableCell.<ModelNoteTable>forTableColumn());
                col_note.setCellValueFactory(new PropertyValueFactory<>("note"));
                col_note.setCellFactory(TextFieldTableCell.<ModelNoteTable>forTableColumn());
                col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                col_date.setCellFactory(TextFieldTableCell.<ModelNoteTable>forTableColumn());
                tableView.setItems(oblist);
                tableView.refresh();

                tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal,newVal)->{
                        if(newVal != null){
                            //id.setText(newVal.getId());
                            author.setText(newVal.getAuthor());
                            note.setText(newVal.getNote());  
                        }
                        });
//        try {
//            rs.close();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
	public void clearFields(){
            author.clear();
            note.clear();
        }

	private List<String> getData(){
	List<String> options = new ArrayList<>();
			try {
				DbConnection database = new DbConnection();
                                connection = database.getConnection();
				String sq = "SELECT Author FROM notepad";
				PreparedStatement pst = connection.prepareStatement(sq);
				ResultSet rs1 = pst.executeQuery();
				
				while(rs1.next()) {
					options.add(rs1.getString("Author"));
//					options.add(rs1.getString("Note"));
				}
				pst.close();
				rs1.close();
				return options;
                        }
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
			return options;
	}
	
        public void refreshTable() throws SQLException{
        oblist.clear();
        try{
            String query = "SELECT * FROM notepad";
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                   oblist.add(new ModelNoteTable(
                           rs.getString("id"),
                           rs.getString("Author"),
                           rs.getString("Note"),
                           rs.getString("Date")
                           
                   ));
                   tableView.setItems(oblist);
            }
            
            
        }catch(SQLException e2){
            System.err.print(e2);
        }
    }
	
	
	public void btnAdd(ActionEvent e) {
	addBtn.getScene().getWindow().hide();
	Stage home = new Stage();
	try {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/NewNote.fxml"));
		Scene scene = new Scene(root);
		home.setScene(scene);
		home.setTitle("NotePad");
		home.show();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}}
	
	public void btnSearch(ActionEvent e1) {

			
		
	}
        
        @FXML
    void deleteBtn(ActionEvent event) throws SQLException {

                                        
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setHeaderText(null);
					alert.setContentText("Are you sure you want to delete this note?");
					 Optional<ButtonType> result = alert.showAndWait();
                                        if(result.get() == ButtonType.OK){
                String Delete = "DELETE FROM notepad WHERE Author=?";
		
                connection = database.getConnection();
//		
				try {
					
					pst = connection.prepareStatement(Delete);
//                                        pst = connection.prepareStatement(insert1);
//					java.util.Date date = new java.util.Date();
//					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//					java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
                                        pst.setString(1,author.getText());      
//					pst.setString(2,name.getText());
//					pst.setString(3,location.getText());
//                                        pst.setString(4,gender.getText());
                                        
//                                        pst.setString(4,id.getText()); 
//					pst.setDate(3, sqlDate);
//					pst.setTimestamp(3, sqlTime);
					
					pst.execute();
                                        
//					
                                        
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					clearFields();
					
                                refreshTable();
                               
    }}

    @FXML
    void updateBtn(ActionEvent event) throws SQLException {
         String insert = "UPDATE notepad SET Author=?, Note=? WHERE Author=?"; 
         DbConnection database = new DbConnection();
                connection = database.getConnection();
				try {
					
					pst = connection.prepareStatement(insert);
//                                      
                                          
                                        pst.setString(1,author.getText());
					pst.setString(2,note.getText());
                                        pst.setString(3,author.getText());
					pst.execute();
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Note Updated successfully");
					alert.show();
                                        clearFields();
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                                refreshTable();
                                
    }
    
//	switch scenes
	public void setSecondScene(Scene scene) {
		// TODO Auto-generated method stub
		secondScene = scene;
	}

}
