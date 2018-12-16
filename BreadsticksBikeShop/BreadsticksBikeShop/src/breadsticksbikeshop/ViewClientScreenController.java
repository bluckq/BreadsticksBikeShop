/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopClasses.Client;
import bikeShopQueries.ClientQueries;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

/**
 * FXML Controller class
 *
 * @author bluck
 */
public class ViewClientScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML
        TableView<Client> clientTableView;

        @FXML
        TableColumn<Client, String> clientFirstNameColumn;
        
        @FXML
        TableColumn<Client, String> clientLastNameColumn;

        @FXML
        TableColumn<Client, String> clientAddressColumn;

        @FXML
        TableColumn<Client, Integer> clientIDColumn;

        @FXML
        TableColumn<Client, String> clientPhoneNumberColumn;

        @FXML
        TextField removeTextField;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayTableView();
    }    
    
     public void addClient(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("AddClientScreen.fxml"));
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
      
       public void updateButtonPressed(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("UpdateClientScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
       
       public void removeClient(ActionEvent event) throws IOException
       {
           ClientQueries clientQuery = new ClientQueries();
           
           Integer clientID = new Integer(removeTextField.getText());
           
           clientQuery.deleteClient(clientID);
           
           displayTableView();
           
       }
       
       private void displayTableView()
       {
           ClientQueries clientQuery = new ClientQueries();
        
        ObservableList<Client> data = FXCollections.observableArrayList(clientQuery.getAllClients());
        
        
        
        
        clientTableView.setEditable(true);
        
      
        clientIDColumn.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        clientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        clientPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        clientTableView.setItems(null);
        clientTableView.setItems(data);
       }
}
