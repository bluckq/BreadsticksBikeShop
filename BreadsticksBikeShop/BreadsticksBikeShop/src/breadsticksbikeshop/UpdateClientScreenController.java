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
 */
public class UpdateClientScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField clientIDTextField;
    
    @FXML
    TextField firstNameTextField;
    
    @FXML
    TextField lastNameTextField;
    
    @FXML
    TextField addressTextField;
    
    @FXML
    TextField phoneTextField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void submitButton(ActionEvent event) throws IOException
    {
        ClientQueries clientQuery = new ClientQueries();
        
        Integer clientID = new Integer(clientIDTextField.getText());
        
        int result = clientQuery.updateClient(clientID, firstNameTextField.getText(), 
                lastNameTextField.getText(), addressTextField.getText(), phoneTextField.getText());
         
        if(result == 1)
        {
            displayAlert(Alert.AlertType.INFORMATION, "Entry Updated", 
                    "New entry successfully updated !");
        }
        else
        {
            displayAlert(Alert.AlertType.ERROR, "Entry not Updated",
                    "Unable to update entry !");
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
