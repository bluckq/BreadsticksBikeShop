/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeShopQueries;

import bikeShopClasses.Part;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PartQueries
{
    private static final String URL = "jdbc:mysql://localhost:3306/bikeshop";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "Password1";

   private Connection connection; // manages connection
   private PreparedStatement selectAllParts;       
   private PreparedStatement selectPartsByName;
   private PreparedStatement insertNewPart;       
   private PreparedStatement updateExistingPart = null; 
   private PreparedStatement deleteExistingPart;
   private PreparedStatement selectPartByID;
   //Constructor
   public PartQueries()
   {
       try{
       connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
       
       selectAllParts = connection.prepareStatement(
                "Select * FROM Part ORDER BY name");
       
       selectPartByID = connection.prepareStatement("SELECT * FROM Part "
               + "WHERE partID = ?");
       
       selectPartsByName = connection.prepareStatement(
                "Select * FROM Part WHERE name = ? "
                + "ORDER BY PartID");
       
       insertNewPart = connection.prepareStatement(
             "INSERT INTO Part "
             + "(name, price, Description)"
             +  "VALUES(?, ?, ?)");
       
       updateExistingPart = connection.prepareStatement(
                "UPDATE Part SET name = ?, price = ?, Description = ? "
                + "WHERE PartID = ?");
       
       deleteExistingPart = connection.prepareStatement("DELETE FROM Part "
                            + "Where PartID = ?");
       }
       catch (SQLException sqlException) {
     
           sqlException.printStackTrace();
           System.exit(1);
      } 
   }
   
   public List<Part> getPartByID(int id)
   {
       try
       {
           selectPartByID.setInt(1, id);
       }
       catch(SQLException sql)
       {
           sql.printStackTrace();
       }
       try(ResultSet resultSet = selectPartByID.executeQuery())
       {
           List<Part> results = new ArrayList();
           while(resultSet.next())
           {
                results.add(new Part(resultSet.getInt("partID"),resultSet.getString("name"),
                    resultSet.getDouble("price"),resultSet.getString("description")));
           }
           return results;

       }
       catch(SQLException sql)
       {
           sql.printStackTrace();
       }
       
       return null;
   }
   
    //Display all Parts
   public List<Part> getAllPart()
   {
       try(ResultSet resultSet = selectAllParts.executeQuery())
       {
           List<Part> results = new ArrayList<Part>();
           
           while(resultSet.next())
           {
               results.add(new Part(
                       resultSet.getInt("PartID"),
                       resultSet.getString("name"),
                       resultSet.getDouble("price"),
                       resultSet.getString("Description")));
           }
               
           return results;
       }
       catch (SQLException sqlException)
       {
         sqlException.printStackTrace();         
       }
       
       return null;
   }
   
   //Display Parts by PartName
   public List<Part> getAllPartsByPartName(String partName)
   {
       try
       {
           selectPartsByName.setString(1, partName);
       }
       catch(SQLException sqlException)
       {
           sqlException.printStackTrace();
           return null;
       }
       
       //executeQuery returns ResultSet containing matching entries
       try(ResultSet resultSet = selectAllParts.executeQuery())
       {
           List<Part> results = new ArrayList<Part>();
           
           while(resultSet.next())
           {
               results.add(new Part(
                       resultSet.getInt("PartID"),
                       resultSet.getString("name"),
                       resultSet.getDouble("price"),
                       resultSet.getString("Description")));
           }
               
           return results;
       }
       catch (SQLException sqlException)
       {
         sqlException.printStackTrace(); 
         return null;
       }
   }
   
   //Add an Invoice to the database
   public int addInParts(String partName, double partPrice, String description)
   {
       try
       {
           insertNewPart.setString(1, partName);
           insertNewPart.setDouble(2, partPrice);
           insertNewPart.setString(3, description);
           
           return insertNewPart.executeUpdate();
       }
       catch(SQLException sqlException)
       {
           sqlException.printStackTrace();
           return 0;
       }
   }
   
     //update an Invoice
   public int updatePart(int partID, String partName, double partPrice, String description)
   {
       try
       {
           updateExistingPart.setString(1, partName);
           updateExistingPart.setDouble(2, partPrice);
           updateExistingPart.setString(3, description);
           updateExistingPart.setInt(4, partID);
           
           return updateExistingPart.executeUpdate();
       }
       catch (SQLException sqlException) 
       {
            sqlException.printStackTrace();
            close();
            return 0;
      }
   }
   
   public int deletePart(int partID)
   {
       try
       {
           deleteExistingPart.setInt(1, partID);
           
           return deleteExistingPart.executeUpdate();
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
