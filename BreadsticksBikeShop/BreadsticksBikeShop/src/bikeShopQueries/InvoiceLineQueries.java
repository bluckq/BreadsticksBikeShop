/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeShopQueries;

import bikeShopClasses.InvoiceLine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cisp24
 */
public class InvoiceLineQueries {
    
     private static final String URL = "jdbc:mysql://localhost:3306/bikeshop";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "Password1";

    private Connection connection; 
    
    private PreparedStatement displayAllLineItems;
    private PreparedStatement displayLineItemsByInvoiceID;
    private PreparedStatement insertNewLineItem;
    private PreparedStatement updateLineItem;
    private PreparedStatement deleteLineItem;
    
    public InvoiceLineQueries()
    {
        try {
         connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         
         displayAllLineItems=connection.prepareStatement("SELECT * FROM InvoiceLine");
         
         displayLineItemsByInvoiceID = connection.prepareStatement("SELECT * FROM InvoiceLine "
                 + "WHERE invoiceID = ?");
         
         insertNewLineItem = connection.prepareStatement("INSERT INTO InvoiceLine"
                    + " (invoiceLineID, invoiceID, partID, quantity) VALUES (?,?,?,?)");
         
         updateLineItem = connection.prepareStatement("UPDATE InvoiceLine SET partID = ?, quantity = ? " +
            "WHERE invoiceLineID = ? AND invoiceID = ?");
         
         deleteLineItem = connection.prepareStatement("DELETE FROM invoiceline "
                            + "Where invoiceLineID = ? AND invoiceID = ?");
        }
        
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         System.exit(1);
      } 
    }
    
    public List<InvoiceLine> getAllLineItems()
    {
        try (ResultSet resultSet = displayAllLineItems.executeQuery()) {
         List<InvoiceLine> results = new ArrayList<InvoiceLine>();
         
         while (resultSet.next()) {
            results.add(new InvoiceLine(
               resultSet.getInt("invoiceLineID"),
               resultSet.getInt("invoiceID"),
               resultSet.getInt("partID"),
               resultSet.getInt("quantity")));
         }
         return results;
        }
        
        catch (SQLException sqlException) {
         sqlException.printStackTrace();         
      }
        return null;
    }
    
    public List<InvoiceLine> getLineItemsByInvoiceID(int invoiceID)
    {
        try {
            displayLineItemsByInvoiceID.setInt(1, invoiceID);
        }
        
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return null;
      }
        try (ResultSet resultSet = displayLineItemsByInvoiceID.executeQuery()) {
         List<InvoiceLine> results = new ArrayList<InvoiceLine>();

         while (resultSet.next()) {
             results.add(new InvoiceLine(
               resultSet.getInt("invoiceLineID"),
               resultSet.getInt("invoiceID"),
               resultSet.getInt("partID"),
               resultSet.getInt("quantity")));
         } 

         return results;
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return null;
      }
    }
    
    public int addNewLineItem(int invoiceID, int partID, int quantity)
    {
        int idToSet = 0;
        try
        {
            displayLineItemsByInvoiceID.setInt(1,invoiceID);
            
        }
        catch (SQLException sql)
        {
            sql.printStackTrace();
            return 0;
        }
        
        try(ResultSet resultSet = displayLineItemsByInvoiceID.executeQuery())
        {
            while(resultSet.next())
            {
                idToSet++;
                InvoiceLine currentLine = new InvoiceLine(resultSet.getInt("invoiceLineID"),
                resultSet.getInt("invoiceID"), resultSet.getInt("partID"), resultSet.getInt("quantity"));
                
                if(idToSet != currentLine.getInvoiceLineID())
                {
                    idToSet--;
                    break;
                }
            }
            idToSet++;
        }
        catch (SQLException sql)
        {
            sql.printStackTrace();
            return 0;
        }
        try
        {
            insertNewLineItem.setInt(1,idToSet);
            insertNewLineItem.setInt(2,invoiceID);
            insertNewLineItem.setInt(3,partID);
            insertNewLineItem.setInt(4,quantity);
           
            
            return insertNewLineItem.executeUpdate();
        }
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return 0;
        }
        
    }
    
    public int changeLineItem(int invoiceLineID, int invoiceID, int partID, int quantity)
    {
        try
        {
            updateLineItem.setInt(1,partID);
            updateLineItem.setInt(2,quantity);
            updateLineItem.setInt(3,invoiceLineID);
            updateLineItem.setInt(4,invoiceID);
            
            return updateLineItem.executeUpdate();
        }
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         close();
         return 0;
      }
    }
    public void close() {
      try {
         connection.close();
      } 
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
      } 
   }
    
    public int deleteItem(int invoiceLineID, int invoiceID)
    {
        try
        {
            deleteLineItem.setInt(1, invoiceLineID);
            deleteLineItem.setInt(2, invoiceID);
            return deleteLineItem.executeUpdate();
        }
        catch(SQLException sql)
        {
            sql.printStackTrace();
            return 0;
        }
    }
}
