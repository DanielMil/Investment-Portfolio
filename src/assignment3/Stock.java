package assignment3;

/**
 * Stock sub class of investment super. 
 * @author danielmil
 */
public class Stock extends Investment {
    
    /**
     * Constructor for Stock. 
     * @param symbol from user.
     * @param name from user.
     * @param price from user.
     * @param quantity from user. 
     */
    public Stock (String symbol, String name, double price, int quantity) {
        super(symbol, name, price, quantity); 
    }

    /**
     * Common method to turn object into a string.
     * @return Stock object as a string.
     */
    @Override
    public String toString () {
        return ("Name: " + getName() + " Symbol: " + getSymbol() + " Price: " + String.format("%.2f", getPrice()) + " Quantity: " + getQuantity() + " BookValue: " + String.format("%.2f", getBookValue()));
    }
    
    /**
     * Common method to check if stock object is equal to passed object. 
     * @param newObject cast to Stock object to compare. 
     * @return Whether or not a match is true or false.
     */
   @Override
    public boolean equals (Object newObject) {
        
        Stock toCompare =  (Stock)newObject; 
        
        if (toCompare == null) {
            return false;
        } else {
            return getSymbol().equals(toCompare.getSymbol()) && getName().equals(toCompare.getName()) && getPrice() == toCompare.getPrice() 
                    && getQuantity() == toCompare.getQuantity() && getBookValue() == toCompare.getBookValue();
        }
        
    }
}
