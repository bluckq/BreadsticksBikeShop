/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopQueries.PartQueries;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bluck
 */
public class UpdatePartScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField partNameTextField;
    
    @FXML
    TextField partPriceTextField;
    
    @FXML
    TextField partDescriptionTextField;
    
    @FXML
    TextField partIDTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    
    public void submitButton(ActionEvent event) throws IOException
    {
        PartQueries partQuery = new PartQueries();
        
        Integer partID = new Integer(partIDTextField.getText());
        Double partPrice = new Double(partPriceTextField.getText());
        
        partQuery.updatePart(partID, partNameTextField.getText(),
                partPrice, partDescriptionTextField.getText());
        
        backButtonPressed(event);
    }
    
    public void backButtonPressed(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("ViewPartScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
