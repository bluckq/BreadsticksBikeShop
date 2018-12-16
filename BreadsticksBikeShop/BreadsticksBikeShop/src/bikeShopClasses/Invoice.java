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
 * @author Aaron
 * 
 * The Invoice class contains the total price of the transaction.
 * It also includes the number of parts that where purchased.
 */
public class Invoice 
{
    /**
     * invoice id
     */
    private IntegerProperty invoiceID;
    /**
     * client id
     */
    private IntegerProperty clientID;
    /**
     * invoice date received
     */
    private StringProperty dateIn;
    /**
     * invoice date returned
     */
    private StringProperty dateOut;
    
    /**
     * Invoice default constructor
     */
    public Invoice() {}
    
    /**
     * invoice constructor that assigns values for all invoice data
     * @param invoiceID
     * @param clientID
     * @param dateIn
     * @param dateOut 
     */
    public Invoice(Integer invoiceID, Integer clientID, String dateIn, String dateOut)
    {
        this.invoiceID = new SimpleIntegerProperty(invoiceID);
        this.clientID = new SimpleIntegerProperty(clientID);
        this.dateIn = new SimpleStringProperty(dateIn);
        this.dateOut = new SimpleStringProperty(dateOut);
    }

    /**
     * assign invoice dateIn
     * @param value 
     */
    public void setDateIn(String value)
    {
        dateIn.set(value);
    }

    /**
     * assign invoice dateOut
     * @param value 
     */
    public void setDateOut(String value)
    {
        dateOut.set(value);
    }

    /**
     * assign client Id
     * @param value 
     */
    public void setClientID(int value)
    {
        clientID.set(value);
    }

    /**
     * return invoice id
     * @return 
     */
    public int getInvoiceID()
    {
        return invoiceID.get();
    }

    /**
     * return client id
     * @return 
     */
    public int getClientID()
    {
        return clientID.get();
    }

    /**
     * return dateIn
     * 
     * @return 
     */
    public String getDateIn()
    {
        return dateIn.get();
    }

    /**
     * return date out
     * 
     * @return 
     */
    public String getDateOut()
    {
        return dateOut.get();
    }
    
    @Override
    public String toString()
    {
        return getInvoiceID() + ", " + getClientID() + ", " + getDateIn() + 
                ", " + getDateOut();    
    }
    
    public IntegerProperty invoiceIDProperty()
    {
        return invoiceID;
    }
    
    public IntegerProperty clientIDProperty()
    {
        return clientID;
    }
    
    public StringProperty dateInProperty()
    {
        return dateIn;
    }
    
    public StringProperty dateOutProperty()
    {
        return dateOut;
    }
}
