package assignment3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*; 
import javax.swing.border.EmptyBorder;

/**
 * GUI and corresponding functions of the Sell command menu option. 
 * @author danielmil
 */
public class SellMenu {
    
    public static JPanel mainPanel = new JPanel(new GridLayout(2,1));
    private JLabel selling = new JLabel("Selling Investment");
    
    private JPanel topOuter;
    private JPanel topInnerLeft;
    private JPanel topInnerRight;
    private JPanel bottom;
    private JPanel topRightInnerInner = new JPanel(new GridLayout(2,1));
    
    private String symbol = "";
    private int quantity = 0;
    private double price = 0;
    
    private JTextArea textArea;
    private JLabel messages;    
    
    private JLabel symbolLabel = new JLabel("Symbol");
    private JLabel quantityLabel = new JLabel("Quantity");
    private JLabel priceLabel = new JLabel("Price");
    private JLabel blank = new JLabel();
    
    private JTextField symbolField = new JTextField();
    private JTextField quantityField = new JTextField();
    private JTextField priceField = new JTextField();
    
    private JButton reset = new JButton("Reset");
    private JButton sell = new JButton("Sell");
    
    private boolean error = false; 
    
    /**
     * GUI representation of sell menu.
     */
    public SellMenu () {

        topOuter = new JPanel(new GridLayout(1,2));
        topInnerLeft = new JPanel(new GridLayout(4,2));
        topInnerRight = new JPanel();
        topInnerRight.setLayout(new BorderLayout());
        
        mainPanel.add(topOuter);
        topOuter.setBorder(new EmptyBorder(0,10,10,10));
        topOuter.add(topInnerLeft);
        topOuter.add(topInnerRight);
        
        //Top Left
        selling.setFont(new Font("SansSerif", Font.PLAIN, 15));
        topInnerLeft.add(selling);
        
        topInnerLeft.add(blank);
        
        topInnerLeft.add(symbolLabel);
        topInnerLeft.add(symbolField);
        
        topInnerLeft.add(quantityLabel);
        topInnerLeft.add(quantityField);
        
        topInnerLeft.add(priceLabel);
        topInnerLeft.add(priceField);
        
        //Top Right
        topInnerRight.setBorder(new EmptyBorder(100,100,50,100));
        topInnerRight.add(topRightInnerInner, BorderLayout.CENTER);
        topRightInnerInner.add(reset);
        topRightInnerInner.add(sell);
        
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
                Portfolio.resetFields(symbolField, priceField, quantityField);
            }
        });
        
        sell.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    
                    if (Portfolio.testEmptyField(symbolField.getText())) {
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
                    price = Double.parseDouble(priceField.getText());
                    quantity = Integer.parseInt(quantityField.getText());
                    
                    textArea.setText(Portfolio.sell(symbol, quantity, price));
                    
                    Portfolio.resetFields(symbolField, priceField, quantityField);
                }
                
                error = false;
            }
        });
    }
    
    /**
     * Method clears text fields in sell menu when menu is invoked. 
     */
    public void clearField () {
        textArea.setText("");
        Portfolio.resetFields(symbolField, quantityField, priceField);
    }
}
