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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Aaron
 */
public class Part
{
    /**
     * Part id number
     */
    private IntegerProperty partID;
    /**
     * part name
     */
    private StringProperty name;
    /**
     * part price
     */
    private DoubleProperty price;
    /**
     * part description
     */
    private StringProperty description;
   
    /**
     * part constructor
     */
    public Part()
    {
        
    }
    
    /**
     * constructor for Part that assigns all data values
     * 
     * @param partID
     * @param partName
     * @param partPrice
     * @param description 
     */
    public Part(Integer partID, String partName, double partPrice, String description)
    {
        this.name = new SimpleStringProperty(partName);
        this.price = new SimpleDoubleProperty(partPrice);
        this.description = new SimpleStringProperty(description);
        this.partID = new SimpleIntegerProperty(partID);
    }

    /**
     * assign part name
     * 
     * @param value 
     */
    public void setName(String value)
    {
        name.set(value);
    }

    /**
     * assign part price
     * 
     * @param value 
     */
    public void setPrice(double value)
    {
        price.set(value);
    }
    
    /**
     * assign part description
     * 
     * @param value 
     */
    public void setDescription(String value)
    {
        description.set(value);
    }
    
    /**
     * assign partID
     * @param value 
     */
    public void setPartID(Integer value)
    {
        partID.set(value);
    }

    /**
     * return part name
     * 
     * @return 
     */
    public String getName()
    {
        return name.get();
    }

    /**
     * return part price
     * 
     * @return 
     */
    public double getPrice()
    {
        return price.get();
    }

    /**
     * return part description
     * 
     * @return 
     */
    public String getDescription()
    {
        return description.get();
    }

    /**
     * return partID
     * 
     * @return 
     */
    public int getPartID()
    {
        return partID.get();
    }
    
    
    public StringProperty nameProperty()
    {
        return name;
    }
    
    public IntegerProperty partIDProperty()
    {
        return partID;
    }
    
    public DoubleProperty partPriceProperty()
    {
        return price;
    }
    
    public StringProperty descriptionProperty()
    {
        return description;
    }
}
