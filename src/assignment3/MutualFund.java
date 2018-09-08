package assignment3;

/**
 * Mutual fund sub class of investment super. 
 * @author danielmil
 */
public class MutualFund extends Investment {
    
    /**
     * Constructor for MutualFund. 
     * @param symbol from user.
     * @param name from user.
     * @param price from user.
     * @param quantity from user.
     */
    public MutualFund (String symbol, String name, double price, int quantity) {
        super(symbol, name, price, quantity); 
    }

    /**
     * Common method to turn object into a string.
     * @return MutualFund object as a string.
     */
    @Override
    public String toString () {
        return ("Name: " + getName() + " Symbol: " + getSymbol() + " Price: " + String.format("%.2f", getPrice()) + " Quantity: " + getQuantity() + " BookValue: " + String.format("%.2f", getBookValue()));
    }
    
    /**
     * Common method to check if MutualFund object is equal to other passed object.
     * @param newObject MutualFund object to compare to class object. 
     * @return Whether or not a match is true or false.
     */
    @Override
    public boolean equals (Object newObject) {
        
        MutualFund toCompare =  (MutualFund)newObject; 
        
        if (toCompare == null) {
            return false;
        } else {
            return getSymbol().equals(toCompare.getSymbol()) && getName().equals(toCompare.getName()) && getPrice() == toCompare.getPrice() 
                    && getQuantity() == toCompare.getQuantity() && getBookValue() == toCompare.getBookValue();
        }
        
    }
}
