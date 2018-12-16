/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopClasses.Client;
import bikeShopClasses.Invoice;
import bikeShopClasses.Part;
import bikeShopQueries.ClientQueries;
import bikeShopQueries.InvoiceLineQueries;
import bikeShopQueries.InvoiceQueries;
import bikeShopQueries.PartQueries;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bluck
 */
public class AddInvoiceScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    ComboBox clientComboBox;
    
    @FXML
    ComboBox partComboBox;
    
    @FXML
    TextField dateInTextField;
    
    @FXML
    TextField dateOutTextField;
    
    @FXML
    TextField partQtyTextField;
    
    @FXML
    TextArea invoiceTextArea;
    
    @FXML
    TextField totalPriceTextField;
    
    ArrayList<Part> partList;
    ArrayList<Integer> quantitiesList;
    double totalPrice;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClientQueries clientQuery = new ClientQueries();
        totalPrice = 0;
        totalPriceTextField.setText("0.00");
        
        PartQueries partQuery = new PartQueries();
        quantitiesList = new ArrayList();
        partList = new ArrayList();
        
        invoiceTextArea.setText(String.format("%-10s %30s%n", "Part","Quantity"));
        
        
        ObservableList<Client> data = FXCollections.observableArrayList(clientQuery.getAllClients());
        ObservableList<Part> partData = FXCollections.observableArrayList(partQuery.getAllPart());
        
        
        for(int i =0; i < data.size();i++)
        {
            clientComboBox.getItems().addAll(data.get(i).getClientID()+ " " + 
                    data.get(i).getLastName() + ", " + data.get(i).getFirstName());
        }
        
        for(int i =0; i < partData.size();i++)
        {
            partComboBox.getItems().addAll(partData.get(i).getPartID() + " " + partData.get(i).getName());
        }
    }    
    
    
    public void backButtonPressed(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("ViewInvoiceScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
    
    @FXML
    public void addPartAction(ActionEvent event)
    {
        DecimalFormat currencyFormat = new DecimalFormat("$#.##");
        PartQueries query = new PartQueries();
        
        invoiceTextArea.appendText(String.format("%-10s %30s%n",
                partComboBox.getValue(),partQtyTextField.getText()));
        
        String value =  partComboBox.getValue().toString();
        
        String idString = value.substring(0, value.indexOf(" "));
        
       
      ArrayList<Part> selectedPart = new ArrayList(query.getPartByID(Integer.parseInt(idString)));
        
        partList.add(selectedPart.get(0));
        quantitiesList.add(Integer.parseInt(partQtyTextField.getText()));
        
        totalPrice += selectedPart.get(0).getPrice() * Integer.parseInt(partQtyTextField.getText());
        
        //totalPriceTextField.setText(currencyFormat.format(Integer.parseInt(partQtyTextField.getText())));
        totalPriceTextField.setText(currencyFormat.format(totalPrice));
    }
    
    @FXML
    public void submitAction(ActionEvent event)
    {
        ClientQueries clientQuery = new ClientQueries();
        InvoiceQueries invoiceQuery = new InvoiceQueries();
        InvoiceLineQueries invoiceLineQuery = new InvoiceLineQueries();
        List<Invoice> allInvoices = new ArrayList();
        String value =  clientComboBox.getValue().toString();       
        String idString = value.substring(0, value.indexOf(" "));        
        ArrayList<Client> selectedClient = new ArrayList(clientQuery.getClientByID(Integer.parseInt(idString)));
        
        if(invoiceQuery.addInvoice(selectedClient.get(0).getClientID(), dateInTextField.getText(), dateOutTextField.getText()) !=0 )
        {
            displayAlert(Alert.AlertType.INFORMATION, "Invoice Added", 
                    "New invoice successfully added.");
            allInvoices = invoiceQuery.getAllInvoices();
        
            for(int i = 0; i < partList.size(); i++)
            {
                invoiceLineQuery.addNewLineItem(allInvoices.get(allInvoices.size()-1).getInvoiceID(), 
                        partList.get(i).getPartID(), quantitiesList.get(i));
            }
        }
        else
        {
            displayAlert(Alert.AlertType.ERROR, "Entry not added",
                    "Unable to add invoice.");
        }
        
        
        
        
    }
    
    private void displayAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    public void pickClientChoice(ActionEvent event)
    {
        
    }
    
    @FXML
    public void pickPartChoice(ActionEvent event)
    {
        
    }
}
