/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeShopQueries;

import bikeShopClasses.Client;
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
public class ClientQueries {
    private static final String URL = "jdbc:mysql://localhost:3306/bikeshop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Password1";

    private Connection connection; 
    
    private PreparedStatement displayAllClients;
    private PreparedStatement insertNewClient;
    private PreparedStatement displayClientsByLastName;
    private PreparedStatement displayClientsByID;
    private PreparedStatement updateExistingClient;
    private PreparedStatement deleteExistingClient;
    
    public ClientQueries()
    {
        try {
         connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         
         displayAllClients=connection.prepareStatement("SELECT * FROM Client");
         
         displayClientsByID = connection.prepareStatement("SELECT * FROM Client "
                 + "WHERE clientID=?");
         
         displayClientsByLastName = connection.prepareStatement("SELECT * FROM Client"
                 + " WHERE lastName = ?");
         
         insertNewClient = connection.prepareStatement("INSERT INTO Client"
                    + " (firstName, lastName, address, phone) VALUES (?,?,?,?)");
         
         updateExistingClient = connection.prepareStatement("UPDATE Client SET firstName = ?, lastName = ?, " +
            "address = ?, phone = ? WHERE clientID = ?");
         
         deleteExistingClient = connection.prepareStatement("DELETE FROM Client "
                                                    + "Where ClientID = ? ");
        }
        
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         System.exit(1);
      } 
    }
    
    public List<Client> getClientByID(int id)
    {
        try
       {
           displayClientsByID.setInt(1, id);
       }
       catch(SQLException sql)
       {
           sql.printStackTrace();
       }
       try(ResultSet resultSet = displayClientsByID.executeQuery())
       {
           List<Client> results = new ArrayList();
           while(resultSet.next())
           {
                results.add(new Client(resultSet.getInt("clientID"),resultSet.getString("firstName"),
                    resultSet.getString("lastName"),resultSet.getString("address"),resultSet.getString("phone")));
           }
           return results;

       }
       catch(SQLException sql)
       {
           sql.printStackTrace();
       }
       
       return null;
    }
    public List<Client> getAllClients()
    {
        try (ResultSet resultSet = displayAllClients.executeQuery()) {
         List<Client> results = new ArrayList<Client>();
         
         while (resultSet.next()) {
            results.add(new Client(
               resultSet.getInt("clientID"),
               resultSet.getString("firstName"),
               resultSet.getString("lastName"),
               resultSet.getString("address"),
               resultSet.getString("phone")));
         }
         return results;
        }
        
        catch (SQLException sqlException) {
         sqlException.printStackTrace();         
      }
        return null;
    }
    
    public List<Client> getClientsByLastName(String lastName)
    {
        try {
            displayClientsByLastName.setString(1,lastName);
        }
        
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return null;
      }
        try (ResultSet resultSet = displayClientsByLastName.executeQuery()) {
         List<Client> results = new ArrayList<Client>();

         while (resultSet.next()) {
            results.add(new Client(
               resultSet.getInt("clientID"),
               resultSet.getString("firstName"),
               resultSet.getString("lastName"),
               resultSet.getString("address"),
               resultSet.getString("phone")));
         } 

         return results;
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return null;
      } 
    }
    
    public int addNewClient(String firstName, String lastName, String address, String phone)
    {
        try
        {
            insertNewClient.setString(1,firstName);
            insertNewClient.setString(2,lastName);
            insertNewClient.setString(3,address);
            insertNewClient.setString(4,phone);
            
            return insertNewClient.executeUpdate();
        }
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return 0;
      }
    }
    
    public int updateClient(int id, String firstName, String lastName, String address, String phone)
    {
        try
        {
            updateExistingClient.setString(1,firstName);
            updateExistingClient.setString(2,lastName);
            updateExistingClient.setString(3,address);
            updateExistingClient.setString(4,phone);
            updateExistingClient.setInt(5,id);
            
            return updateExistingClient.executeUpdate();
        }
        catch (SQLException sqlException) {
         sqlException.printStackTrace();
         close();
         return 0;
      }
    }
    
    public int deleteClient(int clientID)
    {
        try
        {
            deleteExistingClient.setInt(1, clientID);
            
            return deleteExistingClient.executeUpdate();
        }
        catch(SQLException sqlException)
        {
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
}
