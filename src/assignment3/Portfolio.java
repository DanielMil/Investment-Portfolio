package assignment3;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*; 
import javax.swing.border.EmptyBorder;

/**
 * Class manages the stock and mutual fund classes that extend the investment parent class.
 * @author danielmil
 */
public class Portfolio extends JFrame {
    
    private double totalGain = 0;
    public static Portfolio p = new Portfolio();
    
    public ArrayList<Investment> investment = new ArrayList<>();
    public HashMap<String, ArrayList<Integer>> hMap = new HashMap<>();

    static Scanner stdin = new Scanner(System.in);
   
    public static final double MUTUALFUND_PRICE = 45.00;
    public static final double STOCK_PRICE = 9.99;
    public static final int INVALID_INPUT = -1;

    public static final int TOTAL_GAIN_TYPE = 0;
    public static final int INDIVIDUAL_GAIN_TYPE = 1; 
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    
    public static final int EXISTS_UPDATED = 1;
    public static final int EXISTS_NOT_UPDATED = 2;
    public static final int DOES_NOT_EXIST = 3;
    
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_FAILURE = -1;
    public static final int UPDATED = 1;
    
    public static final int TYPE_STOCK = 0;
    public static final int TYPE_FUND = 1;
    
    private JMenuBar menuBar;
    private JMenu commands;
    private JMenuItem buy;
    private JMenuItem sell;
    private JMenuItem search;
    private JMenuItem getGain; 
    private JMenuItem update; 
    private JMenuItem quit;
    
    private JLabel welcome;
    private JLabel text; 
    
    BuyMenu buyClass = new BuyMenu();
    SellMenu sellClass = new SellMenu();
    UpdateMenu updateClass = new UpdateMenu();
    GetGainMenu getGainClass = new GetGainMenu();
    SearchMenu searchClass = new SearchMenu();
    
    private String fileName;
        
    public Portfolio () {
        super("Investment Portfolio"); 
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        
        this.add(homePanel);
        this.add(BuyMenu.mainPanel);
        this.add(SearchMenu.mainPanel);
        this.add(SellMenu.mainPanel);
        this.add(GetGainMenu.mainPanel);
        this.add(UpdateMenu.mainPanel);
        
        BuyMenu.mainPanel.setVisible(false);
        SellMenu.mainPanel.setVisible(false);
        SearchMenu.mainPanel.setVisible(false);
        GetGainMenu.mainPanel.setVisible(false);
        UpdateMenu.mainPanel.setVisible(false);
        
        //Menu Bar
        buy = new JMenuItem("Buy");
        sell = new JMenuItem("Sell");
        update = new JMenuItem("Update");
        getGain = new JMenuItem("Get Gain");
        search = new JMenuItem("Search");
        quit = new JMenuItem("Quit");
    
        commands = new JMenu("Commands");
        commands.add(buy);
        commands.add(sell);
        commands.add(update); 
        commands.add(getGain);
        commands.add(search);
        commands.add(quit);
    
        menuBar = new JMenuBar();
        menuBar.add(commands);
    
        this.setJMenuBar(menuBar); 
        
        //Text Area
        welcome = new JLabel("<html>Welcome to Investment Portfolio.</html>", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.PLAIN, 25));
        homePanel.add(welcome); 
        
        text = new JLabel("<html>Choose a command from the \"Commands\" menu to buy or sell an investment, update the prices for all investments, "
                + "get gain for the portfolio, or quit the program.</html>", SwingConstants.CENTER);
        text.setBorder(new EmptyBorder(30,20,0,0));
        text.setFont(new Font("SansSerif", Font.PLAIN, 18));
        homePanel.add(text);
        
        //Command Buttons
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                homePanel.setVisible(false);
                SellMenu.mainPanel.setVisible(false);
                UpdateMenu.mainPanel.setVisible(false);
                SearchMenu.mainPanel.setVisible(false);
                GetGainMenu.mainPanel.setVisible(false);
                
                sell.setEnabled(true);
                search.setEnabled(true);
                getGain.setEnabled(true);
                update.setEnabled(true);
                
                buyClass.clearField();
                
                buy.setEnabled(false);
                BuyMenu.mainPanel.setVisible(true);
            }
        });
        
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                homePanel.setVisible(false);
                BuyMenu.mainPanel.setVisible(false);
                UpdateMenu.mainPanel.setVisible(false);
                SearchMenu.mainPanel.setVisible(false);
                GetGainMenu.mainPanel.setVisible(false);
                
                buy.setEnabled(true);
                search.setEnabled(true);
                getGain.setEnabled(true);
                update.setEnabled(true);
                
                sellClass.clearField();
                
                sell.setEnabled(false);
                SellMenu.mainPanel.setVisible(true);
            }
        });
        
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                homePanel.setVisible(false);
                BuyMenu.mainPanel.setVisible(false);
                SellMenu.mainPanel.setVisible(false);
                SearchMenu.mainPanel.setVisible(false);
                GetGainMenu.mainPanel.setVisible(false);
                
                sell.setEnabled(true);
                search.setEnabled(true);
                getGain.setEnabled(true);
                buy.setEnabled(true);
                
                updateClass.checkUpdate(); 
                
                update.setEnabled(false);
                UpdateMenu.mainPanel.setVisible(true);
            }
        });
        
        getGain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                homePanel.setVisible(false);
                BuyMenu.mainPanel.setVisible(false);
                SellMenu.mainPanel.setVisible(false);
                SearchMenu.mainPanel.setVisible(false);
                UpdateMenu.mainPanel.setVisible(false);
                
                sell.setEnabled(true);
                search.setEnabled(true);
                buy.setEnabled(true);
                update.setEnabled(true);
                
                getGain.setEnabled(false);
                GetGainMenu.mainPanel.setVisible(true);
                
                getGainClass.displayGain(); 
            }
        });
        
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                homePanel.setVisible(false);
                BuyMenu.mainPanel.setVisible(false);
                SellMenu.mainPanel.setVisible(false);
                GetGainMenu.mainPanel.setVisible(false);
                UpdateMenu.mainPanel.setVisible(false);
                
                sell.setEnabled(true);
                buy.setEnabled(true);
                getGain.setEnabled(true);
                update.setEnabled(true);
                
                searchClass.clearField(); 
                
                search.setEnabled(false);
                SearchMenu.mainPanel.setVisible(true);
            }
        });
        
        quit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed (ActionEvent e) {
               exit();
           }
        });
        
        //Window listener for window closing button invokes exit method. 
        addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing (WindowEvent e) {
               exit();
           } 
        });
    }
    
    /**
     * Method determines if user wants to save data before exiting and exits program accordingly. 
     */
    public void exit () {
        int confirmed = JOptionPane.showConfirmDialog(null, 
        "Would you like to save the program state?", "Exiting Program",
        JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            saveProgram();
            System.exit(0);
        } else if (confirmed == JOptionPane.NO_OPTION) {
            System.exit(0);
        } 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid command line arguments.\nFile name required.\nExiting program.");
            System.exit(0);
        }
        p.fileName = args[0];
        
        loadProgram();
        p.setVisible(true);
    }
    
    /**
     * Method resets all the text fields given. 
     * @param f1 text field 1 to reset.
     * @param f2 text field 2 to reset.
     * @param f3 text field 3 to reset.
     * @param f4 text field 4 to reset.
     */
    public static void resetFields (JTextField f1, JTextField f2, JTextField f3, JTextField f4) {
        
        f1.setText("");
        f2.setText("");
        f3.setText("");  
        f4.setText("");
    }
    
      /**
     * Method resets all the text fields given. 
     * @param f1 text field 1 to reset.
     * @param f2 text field 2 to reset.
     * @param f3 text field 3 to reset.
     */
    public static void resetFields (JTextField f1, JTextField f2, JTextField f3) {
        
        f1.setText("");
        f2.setText("");
        f3.setText("");  
    }
    
    /**
     * Method test for an empty string input.
     * @param input to check.
     * @return true if the string is empty, false otherwise. 
     */
    public static boolean testEmptyField (String input) {
        
        if (input.isEmpty())
            return true;
        
        return false;
    }
    
    /**
     * Method tests if a string input can be parsed to double.
     * @param input to check.
     * @return true if there is an error in parsing, false otherwise. 
     */
    public static boolean testDoubleInput (String input) {
        
        double test;
        
        try {
            test = Double.parseDouble(input);
        } catch (Exception e) {
            return true;
        }
         
        if (test <= 0)
            return true;
         
        return false;
    }
    
     /**
     * Method tests if a string input can be parsed to integer.
     * @param input to check.
     * @return true if there is an error in parsing, false otherwise. 
     */
    public static boolean testIntegerInput (String input) {
        
        int test;
        
        try {
            test = Integer.parseInt(input);
        } catch (Exception e) {
            return true;
        }
         
        if (test <= 0)
            return true;
        
        return false; 
    }

    /**
     * Method ask user for input and creates a new stock or mutual fund if one does not already exist.
     * @param type investment type (Stock or mutual fund). 
     * @param sym investment symbol.
     * @param nam investment name.
     * @param quant investment quantity.
     * @param pr investment price.
     * @return EXIT_SUCCESS if buying was successful, EXIT_FAILURE otherwise.
     */
    public static int buy (int type, String sym, String nam, int quant, double pr) {

        Investment newInvestment;
        int exists = DOES_NOT_EXIST;

        int stockOrFund = type;
        String id = sym;
        String name = nam;
        double price = pr;
        int quantity = quant;  
        
        exists = check(id, stockOrFund, price, quantity);

        if (exists == DOES_NOT_EXIST) {
            
            if (stockOrFund == 0) {
                newInvestment = new Stock(id, name, price, quantity);
                newInvestment.setBookValue(price * quantity + STOCK_PRICE);
                p.investment.add(newInvestment);
            } else {
                newInvestment = new MutualFund(id, name, price, quantity);
                newInvestment.setBookValue(price * quantity);
                p.investment.add(newInvestment);
            }
            
            return EXIT_SUCCESS;
            
        } else if (exists == EXISTS_UPDATED) {
            return UPDATED;
        }
        
        return EXIT_FAILURE; 
    }

    /**
     * Method checks if symbol inputted by user already exists as a stock or mutual fund.
     * @param key Symbol to check for uniqueness.
     * @param stockOrFund String that can either be a stock or mutual fund, used to determine
     * whether to add to existing list or tell user that they made an error.
     * @param pr investment price.
     * @param qty investment quantity.
     * @return EXISTS_UPDATED, EXISTS_NOT_UPDATED or DOES_NOT_EXIST based on the search results. 
     */
    public static int check (String key, int stockOrFund, double pr, int qty) {

        int counter = 0;

        if (stockOrFund == 0) {

            for (Investment toCheck : p.investment) {
                if (toCheck.getSymbol().toLowerCase().equals(key.toLowerCase())&& toCheck instanceof Stock) {
                    addInvestment(stockOrFund, counter, pr, qty);
                    return EXISTS_UPDATED;
                }
                counter++;
            }

            for (Investment toCheckFund : p.investment) {
                if (toCheckFund.getSymbol().toLowerCase().equals(key.toLowerCase()) && toCheckFund instanceof MutualFund) {
                    return EXISTS_NOT_UPDATED;
                }
            }
        } else {

            for (Investment toCheckStock : p.investment) {
                if (toCheckStock.getSymbol().toLowerCase().equals(key.toLowerCase()) && toCheckStock instanceof Stock) {
                    return EXISTS_NOT_UPDATED;
                }
            }

            for (Investment toCheckFund : p.investment) {
                if (toCheckFund.getSymbol().toLowerCase().equals(key.toLowerCase()) && toCheckFund instanceof MutualFund) {
                    addInvestment(1, counter, pr, qty);
                    return EXISTS_UPDATED;
                }
                counter++;
            }

        }

        return DOES_NOT_EXIST;
    }

    /**
     * Adds additional quantity and new price if user attempts to buy stock or mutual fund they already own.
     * @param stockOrFund String that can either be a stock or mutual fund, used to determine
     * whether to add to existing list or tell user that they made an error.
     * @param index Integer index of investment position in its respective list.
     * @param newPrice new price to update.
     * @param qty new quantity to add. 
     */
    public static void addInvestment (int stockOrFund, int index, double newPrice, int qty) {

        int quantity = qty;
            
        p.investment.get(index).setPrice(newPrice);
        p.investment.get(index).setQuantity(p.investment.get(index).getQuantity() + quantity);
       
        if (stockOrFund == 0) {
            p.investment.get(index).setBookValue(p.investment.get(index).getBookValue() + (newPrice * quantity) + STOCK_PRICE);
        } else {
            p.investment.get(index).setBookValue(p.investment.get(index).getBookValue() + (newPrice * quantity));
        }
    }

    /**
     * Method sells respective investment and adjust the quantity and book value.
     * @param symbol corresponding to selling investment. 
     * @param quantity of selling investment.
     * @param price of selling investment.
     * @return String prompt with the result of selling investment.
     */
    public static String sell (String symbol, int quantity, double price) {

        boolean found = false;
        int quantityRemaining = 0;
        int findIndex = 0;
        double totalGain = 0;


        for (Investment toCheck : p.investment) {

            if (toCheck.getSymbol().toLowerCase().equals(symbol.toLowerCase())) {

                if (quantity > toCheck.getQuantity()) {
                    return "You do not own enough of this investment!";
                } else {

                    quantityRemaining = toCheck.getQuantity() - quantity;

                    if (quantityRemaining == 0) {

                        toCheck.setPrice(price);

                        if (toCheck.getClass().toString().equals("class assignment3.Stock")) {
                            totalGain = ((toCheck.getPrice() * toCheck.getQuantity() - STOCK_PRICE) - toCheck.getBookValue());
                        } else {
                            totalGain = ((toCheck.getPrice() * toCheck.getQuantity() - MUTUALFUND_PRICE) - toCheck.getBookValue());
                        }
                        
                        p.investment.remove(findIndex);
                        return ("You do not own anymore of this investment.\nTotal gain from this investmenmt: " + String.format("%.2f", totalGain));

                    } else {

                        toCheck.setPrice(price);
                        toCheck.setBookValue(toCheck.getBookValue() * quantityRemaining / (double)toCheck.getQuantity());
                        toCheck.setQuantity(quantityRemaining);

                    }
                }

                found = true;
            }

            findIndex++;

        }

        if (!found) {
            return "Symbol not found!";
        }
        
        return "Successfully sold investment.";
    }

    /**
     * Method to update all investment prices bases on user input.
     * @param newPrice of investment.
     * @param index of investment to update in ArrayList. 
     */
    public static void update (double newPrice, int index) {

          p.investment.get(index).setPrice(newPrice); 
    }

    /**
     * Method to compute the total gain based on the current book value, price and quantity.
     * @param type total gain or individual gains.
     * @return  String prompt corresponding to appropriate gain type. 
     */
    public static String getGain (int type) {

        String indGains = ""; 
        double indGain = 0; 

        p.totalGain = 0; 
        
        for (Investment toCheck : p.investment) {

            if (toCheck.getClass().toString().equals("class assignment3.Stock")) {
                indGain = ((toCheck.getPrice() * toCheck.getQuantity() - STOCK_PRICE) - toCheck.getBookValue());
                p.totalGain += indGain; 
            } else {
                indGain = ((toCheck.getPrice() * toCheck.getQuantity() - MUTUALFUND_PRICE) - toCheck.getBookValue());
                p.totalGain += indGain;
            }
            indGains += "The gain from investment \'" + toCheck.getSymbol() + "\' is: " + (String.format("%.2f", indGain) + "\n");
        }
        
        if (type == TOTAL_GAIN_TYPE)
            return (String.format("%.2f", p.totalGain));
        else 
            return indGains;
    }

    /**
     * Method takes in a name, symbol, range or any combination of the 3 to search
     * both lists for a matching investment.
     * @param toFindName Name of associated investment. 
     * @param toFindId Symbol of associated investment.
     * @param upper bound of price.
     * @param lower bound of price.
     * @return String prompt with associated search result.
     */
    public static String search (String toFindName, String toFindId, String upper, String lower) {

        boolean found = true;
        boolean nameFind = true;
        int j = 0;
        int foundCount = 0;
        String result = "";
        double lowerDouble = 0;
        double upperDouble = 0;
        
        loadHashMap(); 

        if (upper.equals("") && lower.equals("") && toFindId.equals("") && toFindName.equals("")) {

            for (Investment i : p.investment) {
                result += (i.toString() + "\n");
            }
            return result;
        }

        for (Investment toCheck : p.investment) {

            found = true;
            nameFind = true;

            if (!toFindName.equals("")) {

                String name[] = toFindName.split(" ");

                for (String name1 : name) {

                    if (p.hMap.containsKey(name1)) {

                        if (!(p.hMap.get(name1).contains(j))) {
                            nameFind = false;
                        }

                    } else {
                        nameFind = false;
                    }
                }

                if (!nameFind) {
                    found = false;
                }

            }
            if (!toFindId.equals("")) {
                if (!toFindId.toLowerCase().equals(toCheck.getSymbol().toLowerCase())) {
                    found = false;
                }
            }
            if (!(upper.equals("") && lower.equals(""))) {
                
                if (upper.equals("")) {
                    lowerDouble = Double.parseDouble(lower);
                    
                    if (toCheck.getPrice() < lowerDouble) {
                        found = false; 
                    }
                } else if (lower.equals("")) {
                    upperDouble = Double.parseDouble(upper);
                    
                    if (toCheck.getPrice() > upperDouble) {
                        found = false; 
                    }
                } else {
                    lowerDouble = Double.parseDouble(lower);
                    upperDouble = Double.parseDouble(upper);
                    
                    if (toCheck.getPrice() > upperDouble || toCheck.getPrice() < lowerDouble) {
                        found = false; 
                    }
                }
            }

            j++;

            if (found) {
                result += (toCheck.toString() + "\n");
                foundCount++;
            }
        }

        if (foundCount == 0) {
            return "No results found!";
        } else {
            return result;
        }
       
    }

    /**
     * Method saves the state of the ArrayList after exiting the program.
     */
    public static void saveProgram () {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(p.fileName));

            for (Investment i : p.investment) {

                if (i.getClass().toString().equals("class assignment3.Stock")) {

                    writer.write("stock," + i.getSymbol() + "," + i.getName() + "," + String.format("%.2f", i.getPrice()) + "," + String.format("%.2f", i.getBookValue()) + "," + i.getQuantity());

                } else {

                    writer.write("mutualfund," + i.getSymbol() + "," + i.getName() + "," + String.format("%.2f", i.getPrice()) + "," + String.format("%.2f", i.getBookValue()) + "," + i.getQuantity());

                }

                writer.write("\n");
            }

        } catch (Exception e) {
            System.out.println("Error writing to file!");
        }

        try {
            if (writer != null) {
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing file!");
        }
    }

    /**
     * Method load Investment ArrayList with all prior investments stored in the file.
     */
    public static void loadProgram () {

        BufferedReader reader = null;
        String parts[] = null;

        try {

            File f = new File(p.fileName);
            FileReader fr = new FileReader(f);
            reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {

                parts = line.split(",");

                if (parts[0].equals("stock")) {

                    Investment newStock = new Stock(parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[5]));
                    newStock.setBookValue(Double.parseDouble(parts[4]));
                    p.investment.add(newStock);

                } else if (parts[0].equals("mutualfund")) {

                    Investment newFund = new MutualFund(parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[5]));
                    newFund.setBookValue(Double.parseDouble(parts[4]));
                    p.investment.add(newFund);

                } else {

                    System.out.println("Error 1 reading in file!");
                    return;

                }

            }


        } catch (Exception e) {

            System.out.println("No previous existing file with that name.\nNew save file will be generated.");
        }

        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing file!");
        }
    }

    /**
     * Method to load the Hash map with the name keyword keys and corresponding ArrayList containing index occurrences of the key. 
     */
    public static void loadHashMap () {

        String keyword[];
        int j = 0;

        p.hMap.clear();

        for (Investment i :  p.investment) {

            keyword = i.getName().toLowerCase().split(" ");

            for (String a : keyword) {

                if (p.hMap.containsKey(a)) {
                    if (!p.hMap.get(a).contains(j))
                        p.hMap.get(a).add(j);
                } else {
                    ArrayList<Integer> newList = new ArrayList<>();
                    newList.add(j);
                    p.hMap.put(a, newList);
                }
            }

            j++;
        }

    }
}
