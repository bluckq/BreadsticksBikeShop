/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadsticksbikeshop;

import bikeShopClasses.Invoice;
import bikeShopClasses.Invoice;
import bikeShopClasses.Part;
import bikeShopQueries.InvoiceQueries;
import bikeShopQueries.PartQueries;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class ViewInvoiceScreenController implements Initializable {
    
    @FXML
        TableView<Invoice> invoiceTableView;

        @FXML
        TableColumn<Invoice, String> clientIDColumn;

        @FXML
        TableColumn<Invoice, Double> invoiceIDColumn;

        @FXML
        TableColumn<Invoice, Integer> dateInColumn;

        @FXML
        TableColumn<Invoice, String> dateOutColumn;

        @FXML
        Button addInvoiceButton;

        @FXML
        Button updateInvoiceButton;

        @FXML
        Button removeInvoiceButton;

        @FXML
        Button searchInvoiceButton;

        @FXML
        Button backButton;

        @FXML
        TextField searchInvoiceTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        InvoiceQueries invoiceQuery = new InvoiceQueries();
        
        ObservableList<Invoice> data = FXCollections.observableArrayList(invoiceQuery.getAllInvoices());
        
        
        
        
        invoiceTableView.setEditable(true);
        
      
        invoiceIDColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
        clientIDColumn.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        dateInColumn.setCellValueFactory(new PropertyValueFactory<>("dateIn"));
        dateOutColumn.setCellValueFactory(new PropertyValueFactory<>("dateOut"));

        invoiceTableView.setItems(null);
        invoiceTableView.setItems(data);
    }

public void addInvoice(ActionEvent event) throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("AddInvoiceScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }  

public void removeAction(ActionEvent event) throws IOException
{
    InvoiceQueries invoiceQuery = new InvoiceQueries();
    Invoice deleteInvoice = invoiceTableView.getSelectionModel().getSelectedItem();
    Integer invoiceID = deleteInvoice.getInvoiceID();
    
    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
       
    confirmDialog.setTitle("Confirm Delete");
    confirmDialog.setContentText("Are you sure you want to delete this invoice?");
    
       
    Optional<ButtonType> result = confirmDialog.showAndWait();
    
    if(result.get()==ButtonType.OK)
    {        
        invoiceQuery.deleteInvoice(invoiceID);
        
        displayTableView();
    }
    else
    {
        displayAlert(Alert.AlertType.INFORMATION, "Delete Canceled", "Delete canceled"); 
    }
}

public void updateAction(ActionEvent event) throws IOException
{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("updateInvoiceScreen.fxml"));
    
    Parent newScreen = loader.load();
    Scene scene = new Scene(newScreen);
    
    UpdateInvoiceScreenController controller = loader.getController();
    controller.initData(invoiceTableView.getSelectionModel().getSelectedItem());
    
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
}

public void backAction(ActionEvent event) throws IOException
{
    Parent blah = FXMLLoader.load(getClass().getResource("MainMenuScreen.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
}

private void displayTableView()
    {
        InvoiceQueries invoiceQuery = new InvoiceQueries();
        
        ObservableList<Invoice> data = FXCollections.observableArrayList(invoiceQuery.getAllInvoices());
        
        invoiceTableView.setEditable(true);
        
        invoiceIDColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
        clientIDColumn.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        dateInColumn.setCellValueFactory(new PropertyValueFactory<>("dateIn"));
        dateOutColumn.setCellValueFactory(new PropertyValueFactory<>("dateOut"));

        invoiceTableView.setItems(null);
        invoiceTableView.setItems(data);
    }
    private void displayAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
