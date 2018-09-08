package assignment3;

/**
 *
 * @author danielmil
 */
public class Investment {
    
    private String symbol;
    private String name; 
    private double price;
    private int quantity;
    private double bookValue;
    
    /**
     * Constructor for Investment. 
     * @param symbol from user.
     * @param name from user.
     * @param price from user.
     * @param quantity from user.
     */
    public Investment (String symbol, String name, double price, int quantity) {
        setSymbol(symbol); 
        setName(name); 
        setPrice(price); 
        setQuantity(quantity); 
    }
    
    /**
     * Setter for Investment symbol. 
     * @param symbol.  
     */
    public void setSymbol (String symbol) {
        this.symbol = symbol; 
    }
    
    /**
     * Setter for Investment name.
     * @param name.
     */
    public void setName (String name) {
        this.name = name; 
    }
    
    /**
     * Setter for Investment price.
     * @param price. 
     */
    public void setPrice (double price) {
        this.price = price; 
    }
    
    /**
     * Setter for Investment quantity.
     * @param quantity. 
     */
    public void setQuantity (int quantity ) {
        this.quantity = quantity; 
    }
    
    /**
     * Setter for Investment bookValue
     * @param bookValue.
     */
    public void setBookValue (double bookValue) {
        this.bookValue = bookValue;  
    }
    
    /**
     * Getter for Investment symbol.
     * @return symbol. 
     */
    public String getSymbol () {
        return symbol;
    }
    
    /**
     * Getter for Investment name.
     * @return name.
     */
    public String getName () {
        return name;
    }
    
    /**
     * Getter for Investment price.
     * @return price.
     */
    public double getPrice () {
        return price;
    }
    
    /**
     * Getter of Investment quantity.
     * @return quantity.
     */
    public int getQuantity () {
        return quantity; 
    }
    
    /**
     * Getter for Investment bookValue.
     * @return bookValue.
     */
    public double getBookValue () {
        return bookValue;
    }
    
    /**
     * Common method to turn object into a string.
     * @return Investment object as a string.
     */
    @Override
    public String toString () {
        return ("Name: " + name + " Symbol: " + symbol + " Price: " + String.format("%.2f", price) + " Quantity: " + quantity + " BookValue: " + String.format("%.2f", bookValue));
    }
    
    /**
     * Common method to check if Investment object is equal to other passed object.
     * @param InvestmentObj Investment object to compare to class object. 
     * @return Whether or not a match is true or false.
     */
    @Override
    public boolean equals (Object InvestmentObj) {
        
        Investment Investment =  (Investment)InvestmentObj; 
        
        if (Investment == null) {
            return false;
        } else {
            return symbol.equals(Investment.symbol) && name.equals(Investment.name) && price == Investment.price 
                    && quantity == Investment.quantity && bookValue == Investment.bookValue;
        }
        
    }
}
