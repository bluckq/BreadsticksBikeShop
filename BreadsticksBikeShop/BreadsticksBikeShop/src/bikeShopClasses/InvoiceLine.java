/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeShopClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Alex
 * 
 * InvoiceLine will be the data for invoice that stores
 * the total price and quantity of one part that the 
 * client purchased.
 */
public class InvoiceLine {
    /**
     * invoiceLine ID number
     */
    private IntegerProperty invoiceLineID;
    /**
     * invoice ID
     */
    private IntegerProperty invoiceID;
    /**
     * part id number
     */
    private IntegerProperty partsID;
    /**
     * part quantity
     */
    private IntegerProperty quantity;

    /**
     * InvoiceLine constructor that assigns all data values
     * 
     * @param invoiceLineID
     * @param invoiceID
     * @param partsID
     * @param quantity 
     */
    public InvoiceLine(int invoiceLineID, int invoiceID, int partsID, int quantity) {
        this.invoiceLineID =new SimpleIntegerProperty(invoiceLineID);
        this.invoiceID = new SimpleIntegerProperty(invoiceID);
        this.partsID = new SimpleIntegerProperty(partsID);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    /**
     * assign invoiceID
     * 
     * @param value 
     */
    public void setInvoiceID(int value)
    {
        invoiceID.set(value);
    }

    /**
     * assign partID
     * 
     * @param value 
     */
    public void setPartsID(int value)
    {
        partsID.set(value);
    }

    /**
     * assign part quantity
     * 
     * @param value 
     */
    public void setQuantity(int value)
    {
        quantity.set(value);
    }

    /**
     * return invoiceLineID
     * 
     * @return 
     */
    public int getInvoiceLineID()
    {
        return invoiceLineID.get();
    }

    /**
     * return invoiceID
     * 
     * @return 
     */
    public int getInvoiceID()
    {
        return invoiceID.get();
    }

    /**
     * return partID
     * 
     * @return 
     */
    public int getPartsID()
    {
        return partsID.get();
    }

    /**
     * return part 
     * @return 
     */
    public int getQuantity()
    {
        return quantity.get();
    }

    
    public IntegerProperty invoiceLineIDProperty()
    {
        return invoiceLineID;
    }
    
    public IntegerProperty invoiceIDProperty()
    {
        return invoiceID;
    }
    
    public IntegerProperty partsIDProperty()
    {
        return partsID;
    }
    
    public IntegerProperty quantityProperty()
    {
        return quantity;
    }
    
}
