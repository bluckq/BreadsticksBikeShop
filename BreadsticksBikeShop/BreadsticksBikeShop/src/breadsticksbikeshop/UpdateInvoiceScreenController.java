/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopClasses.Invoice;
import bikeShopClasses.InvoiceLine;
import bikeShopClasses.Part;
import bikeShopQueries.InvoiceLineQueries;
import bikeShopQueries.InvoiceQueries;
import bikeShopQueries.PartQueries;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class UpdateInvoiceScreenController implements Initializable {

    private int selectedInvoiceID;
    
    private double totalPrice;
    
    @FXML TextField dateInTextField;
    
    @FXML TextField dateOutTextField;
    @FXML TextField totalPriceTextField;
    
    @FXML ComboBox partComboBox;
    
    @FXML TextField quantityTextField;
    
    @FXML 
    TableView <InvoiceLine> invoiceLineTableView;
    
    @FXML TableColumn <InvoiceLine, Integer> invoiceLineIDColumn;
    
    @FXML TableColumn <InvoiceLine, Integer> invoiceIDColumn;
    
    @FXML TableColumn <InvoiceLine, Integer> partIDColumn;
    
    @FXML TableColumn <InvoiceLine, Integer> quantityColumn;
    
    
    /**
     * Initializes the controller class.
     */
    
    public void initData(Invoice invoice)
    {
        selectedInvoiceID = invoice.getInvoiceID();
        dateInTextField.setText(invoice.getDateIn());
        dateOutTextField.setText(invoice.getDateOut());
        
        totalPrice = 0;
        
        
        PartQueries partQuery = new PartQueries();  
        ObservableList<Part> partData = FXCollections.observableArrayList(partQuery.getAllPart());
        
        displayTableView();
        
      
        for(int i = 0; i < partData.size();i++)
            partComboBox.getItems().addAll(partData.get(i).getPartID() + " " + partData.get(i).getName());
        
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   public void backButtonPressed(ActionEvent event) throws IOException
   {
       Parent blah = FXMLLoader.load(getClass().getResource("ViewInvoiceScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
   }
   
   public void addAction(ActionEvent event)
   {
       PartQueries query = new PartQueries();
       InvoiceLineQueries lineQuery = new InvoiceLineQueries();
       int quantityInteger=Integer.parseInt(quantityTextField.getText());
       String value = partComboBox.getValue().toString();
       String idString = value.substring(0, value.indexOf(" "));
       
       ArrayList<Part> selectedPart = new ArrayList(query.getPartByID(Integer.parseInt(idString)));
       
       lineQuery.addNewLineItem(selectedInvoiceID, selectedPart.get(0).getPartID(), quantityInteger);
       
       displayTableView();
   }
   
   public void deleteAction(ActionEvent event)
   {
       InvoiceLineQueries lineQuery = new InvoiceLineQueries();
       Alert confirmDialog = new Alert(AlertType.CONFIRMATION);
       
       confirmDialog.setTitle("Confirm Delete");
       confirmDialog.setContentText("Are you sure you want to delete this line?");
       
       Optional<ButtonType> result = confirmDialog.showAndWait();
       
       if(result.get() == ButtonType.OK)
       {
    
            InvoiceLine deleteInvoiceLine = invoiceLineTableView.getSelectionModel().getSelectedItem();

            lineQuery.deleteItem(deleteInvoiceLine.getInvoiceLineID(), selectedInvoiceID);

            displayTableView();
            
       }
       else
       {
           displayAlert(Alert.AlertType.INFORMATION, "Delete Canceled", "Delete canceled.");
       }
   }
   
  
   public void submitAction(ActionEvent event)
   {
       InvoiceQueries query = new InvoiceQueries();
       
       String newDateIn = dateInTextField.getText();
       String newDateOut = dateOutTextField.getText();
       
       if(query.updateInvoice(selectedInvoiceID,newDateIn,newDateOut)!=0)
       {
           displayAlert(Alert.AlertType.INFORMATION, "Invoice Updated", 
                    "Invoice successfully updated.");
       }
        else
        {
            displayAlert(Alert.AlertType.ERROR, "Invoice not updated",
                    "Unable to update invoice.");
        }
   }
   private void displayTableView()
    {
        totalPrice = 0;
        InvoiceLineQueries invoiceLineQuery = new InvoiceLineQueries();
        PartQueries partQuery = new PartQueries();
        DecimalFormat currency = new DecimalFormat("$   #.00");
        
        ObservableList<InvoiceLine> data = FXCollections.observableArrayList(
                invoiceLineQuery.getLineItemsByInvoiceID(selectedInvoiceID));
        
        invoiceLineTableView.setEditable(true);
        
        invoiceLineIDColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceLineID"));
        invoiceIDColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partsID"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        invoiceLineTableView.setItems(null);
        invoiceLineTableView.setItems(data);
        
        for(int i = 0; i < data.size(); i++)
        {
            List<Part> part = partQuery.getPartByID(data.get(i).getPartsID());
            totalPrice += part.get(0).getPrice() * data.get(i).getQuantity();
        }
        
        totalPriceTextField.setText(currency.format(totalPrice));
    }
   
   private void displayAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
