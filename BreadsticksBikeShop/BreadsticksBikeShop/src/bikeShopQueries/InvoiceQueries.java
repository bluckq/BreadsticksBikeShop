/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeShopQueries;

import bikeShopClasses.Invoice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class InvoiceQueries 
{
   private static final String URL = "jdbc:mysql://localhost:3306/bikeshop";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "Password1";

   private Connection connection; // manages connection
   private PreparedStatement selectAllInvoices;       
   private PreparedStatement selectInvoiceByClientID;
   private PreparedStatement insertNewInvoice;       
   private PreparedStatement updateExistingInvoice = null; 
   private PreparedStatement deleteExistingInvoice;
   //Constructor
   public InvoiceQueries()
   {
       try{
       connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
       
       selectAllInvoices = connection.prepareStatement(
                "Select * FROM Invoice ORDER BY InvoiceID");
       
       selectInvoiceByClientID = connection.prepareStatement(
                "Select * FROM Invoice WHERE ClientID LIKE ? "
                + "ORDER BY InvoiceID");
       
       insertNewInvoice = connection.prepareStatement(
             "INSERT INTO Invoice "
             + "(ClientID, DateIn, DateOut)"
             +  "VALUES(?, ?, ?)");
       
       updateExistingInvoice = connection.prepareStatement(
                "UPDATE Invoice SET DateIn = ?, DateOut = ? "
                + "WHERE InvoiceID = ?");
       
       deleteExistingInvoice = connection.prepareStatement("Delete From Invoice "
                + " Where InvoiceID = ?");
       }
       catch (SQLException sqlException) {
     
           sqlException.printStackTrace();
           System.exit(1);
      } 
   }
   
   //Display all Invoices
   public List<Invoice> getAllInvoices()
   {
       try(ResultSet resultSet = selectAllInvoices.executeQuery())
       {
           List<Invoice> results = new ArrayList<Invoice>();
           
           while(resultSet.next())
           {
               results.add(new Invoice(
                       resultSet.getInt("InvoiceID"),
                       resultSet.getInt("ClientID"),
                       resultSet.getString("DateIn"),
                       resultSet.getString("DateOut")));
           }
               
           return results;
       }
       catch (SQLException sqlException)
       {
         sqlException.printStackTrace();         
       }
       
       return null;
   }
   
   //Display Invoices by ClientID
   public List<Invoice> getAllInvoicesByClientID(int clientID)
   {
       try
       {
           selectInvoiceByClientID.setInt(1, clientID);
       }
       catch(SQLException sqlException)
       {
           sqlException.printStackTrace();
           return null;
       }
       
       //executeQurey returns ResultSet containing matching entries
       try(ResultSet resultSet = selectAllInvoices.executeQuery())
       {
           List<Invoice> results = new ArrayList<Invoice>();
           
           while(resultSet.next())
           {
               results.add(new Invoice(
                       resultSet.getInt("InvoiceID"),
                       resultSet.getInt("ClientID"),
                       resultSet.getString("DateIn"),
                       resultSet.getString("DateOut")));
           }
               
           return results;
       }
       catch (SQLException sqlException)
       {
         sqlException.printStackTrace(); 
         return null;
       }
   }
   
   //Add and Invoice to the database
   public int addInvoice(int clientID, String dateIn, String dateOut)
   {
       try
       {
           insertNewInvoice.setInt(1, clientID);
           insertNewInvoice.setString(2, dateIn);
           insertNewInvoice.setString(3, dateOut);
           
           return insertNewInvoice.executeUpdate();
       }
       catch(SQLException sqlException)
       {
           sqlException.printStackTrace();
           return 0;
       }
   }
   
   //update an Invoice
   public int updateInvoice(int invoiceID, String dateIn, String dateOut)
   {
       try
       {
           updateExistingInvoice.setString(1, dateIn);
           updateExistingInvoice.setString(2, dateOut);
           updateExistingInvoice.setInt(3, invoiceID);
           
           
           return updateExistingInvoice.executeUpdate();
       }
       catch (SQLException sqlException) 
       {
            sqlException.printStackTrace();
            close();
            return 0;
      }
   }
   
   //delete an Invoice
   public int deleteInvoice(int invoiceID)
   {
       try
       {
           deleteExistingInvoice.setInt(1, invoiceID);
           
           return deleteExistingInvoice.executeUpdate();
       }
       catch(SQLException sqlException)
       {
           sqlException.printStackTrace();
           close();
           return 0;
       }
   }
   
   public void close()
   {
       try
       {
           connection.close();
       }
       catch(SQLException sqlException)
       {
           sqlException.printStackTrace();
       }
   }
}
