/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopQueries.ClientQueries;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aaron
 * 
 */
public class AddClientScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML 
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;
    
    @FXML
    private TextField phoneNumberTextField;
    
    private ClientQueries clientQuery = new ClientQueries();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    public void submitButtonPressed(ActionEvent event) throws IOException
    {
       int result = clientQuery.addNewClient(firstNameTextField.getText(), 
               lastNameTextField.getText(), addressTextField.getText(), phoneNumberTextField.getText());
       
        if(result == 1)
        {
            displayAlert(Alert.AlertType.INFORMATION, "Entry Added", 
                    "New entry successfully added !");
        }
        else
        {
            displayAlert(Alert.AlertType.ERROR, "Entry not added",
                    "Unable to add entry !");
        }
        
        backButtonAction(event);
    }
    
      private void displayAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    public void backButtonAction(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("ViewClientScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
