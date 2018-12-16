/*
* This is a class that will have all the validation 
* methods
 */
package DataValidation;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;

/**
 *
 * @author szaki5
 */
public class DataValidationC {
    
    //Null form validation
    //does not allow null input
    
    public static boolean textFieldIsNull (TextField inputTextField, Label inputLabel, 
            String validationText)
    {
        boolean isNull=false;
        String validationString = null;
        
        if (inputTextField.getText().isEmpty())
        {
            isNull=true;
            validationString=validationText;
        }
        inputLabel.setText(validationString);
         return isNull;
    }
    
    /* data length form validation
    *only allows input with a specified length
    */
    //note the Label inputLabel will display the error message
    //String validationText is the error message that will be displayed
    
    public static boolean dataLength 
        (TextField inputTextField, Label inputLabel, String validationText, 
                String requiredLength)
        {
            boolean isDataLength=true;
            String validationString=null;
            
            
         if (!inputTextField.getText().matches("\\b\\w-{"+ requiredLength+"}-\\b"))
                 {
                     isDataLength=false;
                     validationString=validationText;
                }
         inputLabel.setText(validationString);
        return isDataLength;
}
        
        //method to validate that ZID starts with a letter Z followed by numbers
        //it will take three arguments the TextField that it will check, the Label that will
        //display the error message, and a String which is the message that will be displayed
        
        public static boolean ZID (TextField inputTextField, Label inputLabel, String validationText)
        {
            //initialize the return value to be true and the message to be null
            boolean isZID=true;
            String validationString=null;
            
            //use the matches of regular expression
            //need to check the following regular expression
            if (!inputTextField.getText().matches("\\z[0-9]+"))
            {
              isZID=false;
              validationString=validationText;
            }
            inputLabel.setText(validationString);
          
            return isZID;
        }
        
        //method to check email format using regular expression
      public static boolean emailFormat 
        (TextField inputTextField, Label inputLabel,String validationText )
        {
            //init the return value
            boolean isEmail =true;
            String validationString=null;
            
            if (!inputTextField.getText().matches 
                ("([a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.(?:edu|com|org|gov))"))
            {
               isEmail=false;
               validationString=validationText;
               
            }
            inputLabel.setText (validationString);
            return isEmail;
        }
        //method to check that the values entered are alphabets
        public static boolean textAlphabet (TextField inputTextField, Label inputLabel, String validationText)
        {
            boolean isAlphabet=true;
            String validationString = null;
            
            if (!inputTextField.getText().matches ("[a-zA-Z]+"))
            {
                isAlphabet=false;
                validationString=validationText;
            }
            inputLabel.setText(validationString);
          return isAlphabet;
          
            
        }
        
        //numeric form validation
        //only allows inputs with numbers only
        
        public static boolean textNumeric (TextField inputTextField, Label inputLabel,
                String validationText)
        {
            boolean isNumeric=true;
            String validationString=null;
            
            if (!inputTextField.getText().matches ("[0-9]+"))
            {
                isNumeric=false;
                validationString=validationText;
            }
            inputLabel.setText(validationString);
            
            return isNumeric;
        }

}

