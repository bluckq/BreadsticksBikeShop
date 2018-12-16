/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeShopClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Alex
 * The Client class contains data about client's 
 * first name, last name, address, and phone number.
 * Its unique identifier is the client id.
 */
public class Client {

    /**
     * Client ID number
     */
    private IntegerProperty clientID;
    /**
     * Client first name
     */
    private StringProperty firstName;
    /**
     * Client last name
     */
    private StringProperty lastName;
    /**
     * Client address
     */
    private StringProperty address;
    /**
     * Client phone
     */
    private StringProperty phone;
    
    /**
     * Default constructor for Client
     */
    public Client()
    {
        
    }
    
    /**
     * Constructor for client that assigns all data to Client
     * @param clientID
     * @param firstName
     * @param lastName
     * @param address
     * @param phone 
     */
    public Client(Integer clientID, String firstName, String lastName, String address, String phone) {
        this.clientID = new SimpleIntegerProperty(clientID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
    }

    /**
     * returns the first name of Clients
     * @return 
     */
    public String getFirstName()
    {
        return firstName.get();
    }

    /**
     * it assigns the first Name of clients
     * @param value 
     */
    public void setFirstName(String value)
    {
        firstName.set(value);
    }

    /**
     * it gets the last name of clients
     * @return 
     */
    public String getLastName()
    {
        return lastName.get();
    }

    /**
     * Assign last name for client
     * @param value 
     */
    public void setLastName(String value)
    {
        lastName.set(value);
    }

    /**
     * returns address of clients
     * @return 
     */
    public String getAddress()
    {
        return address.get();
    }

    /**
     * assign address for client
     * @param value 
     */
    public void setAddress(String value)
    {
        address.setValue(value);
    }

    /**
     * returns phone for client
     * @return 
     */
    public String getPhone()
    {
        return phone.get();
    }

    /**
     * assign phone for client
     * @param value 
     */
    public void setPhone(String value)
    {
        phone.setValue(value);
    }
    
    /**
     * returns client id
     * 
     * @return 
     */
    public int getClientID()
    {
        return clientID.get();
    }
    
    /**
     * assign client id
     * 
     * @param value 
     */
    public void setClientID(Integer value)
    {
        clientID.setValue(value);
    }
    
    public IntegerProperty clientIDProperty()
    {
        return clientID;
    }
    
    public StringProperty firstNameProperty()
    {
        return firstName;
    }
    
    public StringProperty lastNameProperty()
    {
        return lastName;
    }
    
    public StringProperty addressProperty()
    {
        return address;
    }
    
    public StringProperty phoneProperty()
    {
        return phone;
    }
}
