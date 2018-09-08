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
 * GUI and corresponding functions of the Search command menu option. 
 * @author danielmil
 */
public class SearchMenu {
       
    public static JPanel mainPanel = new JPanel(new GridLayout(2,1));
    private JLabel searching = new JLabel("Searching Investments");
    
    private boolean error = false; 
    
    private JPanel topOuter;
    private JPanel topInnerLeft;
    private JPanel topInnerRight;
    private JPanel bottom;
    private JPanel topRightInnerInner = new JPanel(new GridLayout(2,1));
    
    private JTextArea textArea;
    private JLabel messages;
    
    private JLabel keywordsLabel = new JLabel("Name Keywords");
    private JLabel symbolLabel = new JLabel("Symbol");
    private JLabel lowLabel = new JLabel("Low Price");
    private JLabel highLabel = new JLabel("High Price");
    private JLabel blank = new JLabel();
    
    private JTextField symbolField = new JTextField("");
    private JTextField keywordsField = new JTextField("");
    private JTextField lowField = new JTextField("");
    private JTextField highField = new JTextField("");
    
    private JButton reset = new JButton("Reset");
    private JButton search = new JButton("Search");
    
    /**
     * GUI representation of the search menu. 
     */
    public SearchMenu() {
        
        topOuter = new JPanel(new GridLayout(1,2));
        topInnerLeft = new JPanel(new GridLayout(5,2));
        topInnerRight = new JPanel();
        topInnerRight.setLayout(new BorderLayout());
        
        mainPanel.add(topOuter);
        topOuter.setBorder(new EmptyBorder(0,10,10,10));
        topOuter.add(topInnerLeft);
        topOuter.add(topInnerRight);
        
        //Top Left
        searching.setFont(new Font("SansSerif", Font.PLAIN, 15));
        topInnerLeft.add(searching);
        topInnerLeft.add(blank);
        
        topInnerLeft.add(symbolLabel);
        topInnerLeft.add(symbolField);
        
        topInnerLeft.add(keywordsLabel);
        topInnerLeft.add(keywordsField);
        
        topInnerLeft.add(lowLabel);
        topInnerLeft.add(lowField);
        
        topInnerLeft.add(highLabel);
        topInnerLeft.add(highField);
        
        
        //Top Right
        topInnerRight.setBorder(new EmptyBorder(100,100,50,100));
        topInnerRight.add(topRightInnerInner, BorderLayout.CENTER);
        topRightInnerInner.add(reset);
        topRightInnerInner.add(search);
        
        //Bottom
        bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));
        bottom.setBorder(new EmptyBorder(10,10,10,10));
        
        mainPanel.add(bottom);
        
        messages = new JLabel("Search Results");
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
                Portfolio.resetFields(symbolField, lowField, highField, keywordsField);
            }
        });
        
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                try{
                    
                    if (searchValidation(highField.getText())){
                        error = true;
                        throw new NumberOutOfBoundsException();
                    }
                    
                    if (searchValidation(lowField.getText())) {
                        error = true;
                        throw new NumberOutOfBoundsException();
                    }
                    
                } catch (NumberOutOfBoundsException b) {   
                    textArea.setText("Invalid input in price field(s).");
                }
                
                if (!error){
                    textArea.setText(Portfolio.search(keywordsField.getText(), symbolField.getText(), highField.getText(), lowField.getText()));
                    Portfolio.resetFields(symbolField, lowField, highField, keywordsField);
                }
                
                error = false;
            }
        });
    }
    
    /**
     * Method checks if numerical field can be parsed to double.
     * @param toCheck string input to be checked.
     * @return false if value can be parsed, true if there is an error. 
     */
    public boolean searchValidation (String toCheck) {

        if (toCheck.equals("")) {
            return false;
        } else { 
            try {
                Double.parseDouble(toCheck);
            } catch (Exception e) {
                return true;
            }   
        }
        
        return false; 
    }
    
    /**
     * Method clears all text fields when search menu is invoked. 
     */
    public void clearField () {
        textArea.setText("");
        Portfolio.resetFields(symbolField, lowField, highField, keywordsField);
    }
}
