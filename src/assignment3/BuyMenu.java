package assignment3;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*; 
import javax.swing.border.EmptyBorder;

/**
 * GUI and corresponding functions of the Buy command menu option. 
 * @author danielmil
 */
public class BuyMenu {
    
    public static JPanel mainPanel = new JPanel(new GridLayout(2,1));
    
    private int investmentType;
    private String symbol = "";
    private String name = "";
    private int quantity = 0;
    private double price = 0;
    
    private boolean error = false;
    
    private JPanel topOuter;
    private JPanel topInnerLeft;
    private JPanel topInnerRight;
    private JPanel bottom;
    private JPanel topRightInnerInner = new JPanel(new GridLayout(2,1));
    
    private JTextArea textArea;
    
    private JLabel header;
    private JLabel messages;
    
    private JComboBox<String> type;
    private String[] types = {"Stock", "Mutual Fund"};
    
    private JLabel typeLabel = new JLabel("Type");
    private JLabel symbolLabel = new JLabel("Symbol");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel quantityLabel = new JLabel("Quantity");
    private JLabel priceLabel = new JLabel("Price");
    private JLabel blank = new JLabel();
    
    private JTextField symbolField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField quantityField = new JTextField();
    private JTextField priceField = new JTextField();
    
    private JButton reset = new JButton("Reset");
    private JButton buy = new JButton("Buy");
    
    /**
     * GUI Representation of the buy menu. 
     */
    public BuyMenu () {
        
        topOuter = new JPanel(new GridLayout(1,2));
        topInnerLeft = new JPanel(new GridLayout(6,2));
        topInnerRight = new JPanel();
        topInnerRight.setLayout(new BorderLayout());
        
        mainPanel.add(topOuter);
        topOuter.setBorder(new EmptyBorder(0,10,10,10));
        topOuter.add(topInnerLeft);
        topOuter.add(topInnerRight);
        
        //Top Left
        header = new JLabel("Buying an Investment");
        header.setFont(new Font("SansSerif", Font.PLAIN, 15));
        topInnerLeft.add(header);
        
        topInnerLeft.add(blank);
        
        topInnerLeft.add(typeLabel);
        type = new JComboBox<>(types);
        topInnerLeft.add(type);
        
        topInnerLeft.add(symbolLabel);
        topInnerLeft.add(symbolField);
        
        topInnerLeft.add(nameLabel);
        topInnerLeft.add(nameField);
        
        topInnerLeft.add(quantityLabel);
        topInnerLeft.add(quantityField);
        
        topInnerLeft.add(priceLabel);
        topInnerLeft.add(priceField);
        
        //Top Right
        topInnerRight.setBorder(new EmptyBorder(100,100,50,100));
        topInnerRight.add(topRightInnerInner, BorderLayout.CENTER);
        topRightInnerInner.add(reset);
        topRightInnerInner.add(buy);
        
        //Bottom
        bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));
        bottom.setBorder(new EmptyBorder(10,10,10,10));
        
        mainPanel.add(bottom);
        
        messages = new JLabel("Messages");
        messages.setFont(new Font("SansSerif", Font.PLAIN, 15));
        messages.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottom.add(messages);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JScrollPane pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        bottom.add(pane, BorderLayout.WEST); 
        textArea.setText("");
        
        //Buttons 
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                Portfolio.resetFields(symbolField, nameField, priceField, quantityField);
            }
        });
        
        buy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                try{
                    
                    if (Portfolio.testEmptyField(symbolField.getText())) {
                        error = true;
                        throw new EmptyFieldException(); 
                    }
                    
                    if (Portfolio.testEmptyField(nameField.getText())) {
                        error = true;
                        throw new EmptyFieldException();
                    }
                    
                    if (Portfolio.testIntegerInput(quantityField.getText())){
                        error = true;
                        throw new NumberOutOfBoundsException();
                    }
                    
                    if (Portfolio.testDoubleInput(priceField.getText())) {
                        error = true;
                        throw new NumberOutOfBoundsException();
                    }
                    
                } catch (EmptyFieldException a) {
                    textArea.setText("Empty field!");
                } catch (NumberOutOfBoundsException b) {   
                    textArea.setText("Invalid numerical input!");
                }
                
                if (!error){
                    
                    symbol = symbolField.getText();
                    name = nameField.getText();
                    price = Double.parseDouble(priceField.getText());
                    quantity = Integer.parseInt(quantityField.getText());
                    
                    if (type.getSelectedItem().toString().equals("Stock")) {
                        investmentType = Portfolio.TYPE_STOCK;
                    } else {
                        investmentType = Portfolio.TYPE_FUND;
                    }
                    
                    int retVal = Portfolio.buy(investmentType, symbol, name, quantity, price);
                    
                    if (retVal == Portfolio.EXIT_SUCCESS) {
                        textArea.setText("Successfully purchased new " + type.getSelectedItem().toString() + ".");
                    } else if (retVal == Portfolio.UPDATED) {
                        textArea.setText("Successfully updated " + type.getSelectedItem().toString() + ".");
                    } else if (retVal == Portfolio.EXIT_FAILURE) {
                        textArea.setText("Error purchasing a new investment, investment already exists as a different investment type.");
                    } else {
                        textArea.setText("An error has occured while purchasing investment.");
                    }
                    
                    Portfolio.resetFields(symbolField, nameField, priceField, quantityField);

                }
                
                error = false;
            }
        });
    }
    
    /**
     * Method clears all text fields when buy menu option is selected. 
     */
    public void clearField () {
        textArea.setText("");
        Portfolio.resetFields(symbolField, nameField, quantityField, priceField);
    }
}
