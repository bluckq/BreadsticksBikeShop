/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopClasses.Part;
import bikeShopQueries.PartQueries;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Aaron
 */
public class AddPartScreenController {
    
    @FXML
    private Label labelName;
    
    @FXML 
    private Label labelPrice;
    
    @FXML 
    private Label labelDescription;
    
    @FXML
    private TextField nameTextField;
    
    @FXML
    private TextField priceTextField;
    
    @FXML
    private TextField descriptionTextField;
    
    @FXML
    private Button submitButton;
    
    private PartQueries partQuery = new PartQueries();
    
    
    
     @FXML
    public void backButtonAction(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("ViewPartScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
    
    @FXML
    public void submitButtonPressed(ActionEvent event) throws IOException
    {
        Double price = new Double(priceTextField.getText());
        
        int result = partQuery.addInParts(nameTextField.getText(), price, 
                descriptionTextField.getText());
        
        if(result == 1)
        {
            displayAlert(AlertType.INFORMATION, "Entry Added", 
                    "New entry successfully added!");
        }
        else
        {
            displayAlert(AlertType.ERROR, "Entry not added",
                    "Unable to add entry!");
        }
        
        backButtonAction(event);
    }
    
    
    @FXML
    public void updateButtonPressed(ActionEvent event)
    {
        
    }
    
    
    private void displayAlert(AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
