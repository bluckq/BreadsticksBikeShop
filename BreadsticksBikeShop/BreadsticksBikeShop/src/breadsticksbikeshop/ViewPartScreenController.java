/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopClasses.Part;
import bikeShopQueries.PartQueries;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class ViewPartScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
        TableView<Part> partTableView;

        @FXML
        TableColumn<Part, String> partNameColumn;

        @FXML
        TableColumn<Part, Double> partPriceColumn;

        @FXML
        TableColumn<Part, Integer> partIDColumn;

        @FXML
        TableColumn<Part, String> partDescriptionColumn;

        @FXML
        Button addPartButton;

        @FXML
        Button updatePartButton;

        @FXML
        Button removePartButton;

        @FXML
        Button searchPartButton;

        @FXML
        Button backButton;

        @FXML
        TextField searchPartTextField;
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       displayTableView();
    }    
    
    
    public void searchPart(ActionEvent event) throws IOException
    {
        String name = searchPartTextField.getText();
        
        PartQueries partQuery = new PartQueries();
        
        ObservableList<Part> data = FXCollections.observableArrayList(partQuery.getAllPartsByPartName(name));
        
        partTableView.setEditable(true);
        
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        partTableView.setItems(null);
        partTableView.setItems(data);
    }
    
    
    public void addPart(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
    
   
    public void removePart(ActionEvent event) throws IOException
    {
        PartQueries partQuery = new PartQueries();
        
        Integer partID = new Integer(searchPartTextField.getText());
        
        
        
        partQuery.deletePart(partID);
        
        displayTableView();
    }
    
    private void displayTableView()
    {
        PartQueries partQuery = new PartQueries();
        
        ObservableList<Part> data = FXCollections.observableArrayList(partQuery.getAllPart());
        
        partTableView.setEditable(true);
        
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        partTableView.setItems(null);
        partTableView.setItems(data);
    }
    
    
    public void updateButtonPressed(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("UpdatePartScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
    
    
    public void backButtonPressed(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("MainMenuScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
