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
 * GUI and corresponding functions of the Get Gain command menu option. 
 * @author danielmil
 */
public class GetGainMenu {
    
    public static JPanel mainPanel = new JPanel(new GridLayout(2,1));
    private JLabel gettingGain = new JLabel("Getting Total Gain");
    
    private JPanel top = new JPanel(new GridLayout(1,2));
    private JPanel topLeft = new JPanel(new GridLayout(6,2));
    private JPanel bottom = new JPanel();
    
    private JLabel blank = new JLabel();
    private JLabel blank2 = new JLabel();
    private JLabel blank3 = new JLabel();
    private JLabel blank4 = new JLabel();
    private JLabel blank5 = new JLabel();
    private JLabel blank6 = new JLabel();
    private JLabel indGains = new JLabel("Individual Gains");
    private JLabel totalGainLabel = new JLabel("Total gain");
    
    private JTextField totalGainField = new JTextField("");
    
    private JTextArea textArea = new JTextArea("");
    
    /**
     * GUI representation of the getGain menu. 
     */
    public GetGainMenu() {
        
        mainPanel.add(top);
        
        top.add(topLeft);
        top.add(blank2);
        
        gettingGain.setFont(new Font("SansSerif", Font.PLAIN, 15));
        top.setBorder(new EmptyBorder(10,10,50,10));
        
        topLeft.add(gettingGain);
        topLeft.add(blank);
        topLeft.add(blank3);
        topLeft.add(blank4);
        topLeft.add(blank5);
        topLeft.add(blank6);
        topLeft.add(totalGainLabel);
        topLeft.add(totalGainField);
        
        
        totalGainLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        totalGainField.setEditable(false);
        totalGainField.setBorder(new EmptyBorder(10,10,0,10));
        
        mainPanel.add(bottom);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));
        bottom.setBorder(new EmptyBorder(10,10,10,10));
        
        bottom.add(indGains);
        indGains.setAlignmentX(Component.CENTER_ALIGNMENT);
        indGains.setFont(new Font("SansSerif", Font.PLAIN, 15));
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JScrollPane pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        bottom.add(pane, BorderLayout.WEST); 
        textArea.setText("");
    }
    
    /**
     * Method displays appropriate data when GetGain menu option is pressed. 
     */
    public void displayGain () {
        
        if (Portfolio.p.investment.isEmpty()) {
            totalGainField.setText("No gain to display.");
            textArea.setText("No current holdings.\nPurchase an investment in order to display the gain.");
        } else {
            totalGainField.setText(Portfolio.getGain(Portfolio.p.TOTAL_GAIN_TYPE));
            textArea.setText(Portfolio.getGain(Portfolio.p.INDIVIDUAL_GAIN_TYPE));
        }
    }
}
