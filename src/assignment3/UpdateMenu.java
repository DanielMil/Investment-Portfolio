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
 * GUI and corresponding functions of the Update command menu option. 
 * @author danielmil
 */
public class UpdateMenu {
    
    public static JPanel mainPanel = new JPanel(new GridLayout(2,1));
    private JLabel updating = new JLabel("Updating Investments");
    
    private boolean error = false;
    
    private JPanel topOuter;
    private JPanel topInnerLeft;
    private JPanel topInnerRight;
    private JPanel bottom;
    private JPanel topRightInnerInner = new JPanel(new GridLayout(2,1));
    
    private JTextArea textArea;
    private JLabel messages;    
    
    private JLabel symbolLabel = new JLabel("Symbol");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel priceLabel = new JLabel("Price");
    private JLabel blank = new JLabel();
    
    private JTextField symbolField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField priceField = new JTextField();
    
    private JButton previous = new JButton("Previous");
    private JButton next = new JButton("Next");
    private JButton save = new JButton("Save");
    
    private static int currentIndex = 0;
    
    public static final int NEXT_UPDATE = 0;
    public static final int PREV_UPDATE = 1; 
    
    public UpdateMenu() {
        
        topOuter = new JPanel(new GridLayout(1,2));
        topInnerLeft = new JPanel(new GridLayout(4,2));
        topInnerRight = new JPanel();
        topInnerRight.setLayout(new BorderLayout());
        
        mainPanel.add(topOuter);
        topOuter.setBorder(new EmptyBorder(0,10,10,10));
        topOuter.add(topInnerLeft);
        topOuter.add(topInnerRight);
        
        //Top Left
        updating.setFont(new Font("SansSerif", Font.PLAIN, 15));
        topInnerLeft.add(updating);
        
        topInnerLeft.add(blank);
        
        topInnerLeft.add(symbolLabel);
        topInnerLeft.add(symbolField);
        symbolField.setEditable(false);
        
        topInnerLeft.add(nameLabel);
        topInnerLeft.add(nameField);
        nameField.setEditable(false);
        
        topInnerLeft.add(priceLabel);
        topInnerLeft.add(priceField);
        
        //Top Right
        topInnerRight.setBorder(new EmptyBorder(80,100,15,100));
        topInnerRight.add(topRightInnerInner, BorderLayout.CENTER);
        topRightInnerInner.setLayout(new GridLayout(3,1));
        topRightInnerInner.add(previous);
        topRightInnerInner.add(next);
        topRightInnerInner.add(save);
        
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
        
        //GUI buttons
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUpdate(NEXT_UPDATE);
            }
        });
        
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUpdate(PREV_UPDATE);
            }
        });
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                try {
                    
                    if (Portfolio.testDoubleInput(priceField.getText())) {
                        error = true;
                        throw new NumberOutOfBoundsException();
                    }
                
                } catch (NumberOutOfBoundsException b) {
                    textArea.setText("Invalid numerical input!");
                }    
                
                if (!error) {
                    Portfolio.update(Double.parseDouble(priceField.getText()), currentIndex);
                    textArea.setText("Update successful.");
                }
                
                error = false; 
            }
        });
        
    }
    
    /**
     * Method sets appropriate data in text fields if investment ArrayList isn't empty.
     */
    public void checkUpdate () {
        
        if (Portfolio.p.investment.isEmpty()) {
            textArea.setText("No investments to update.\nBuy more holdings to use this feature.");
            nameField.setText(""); 
            symbolField.setText("");
            priceField.setText("");
            
            previous.setEnabled(false);
            next.setEnabled(false);
            save.setEnabled(false);
        } else {
            textArea.setText("");
            previous.setEnabled(false);
            next.setEnabled(true);
            save.setEnabled(true);
            currentIndex = 0; 
            
            nameField.setText(Portfolio.p.investment.get(currentIndex).getName()); 
            symbolField.setText(Portfolio.p.investment.get(currentIndex).getSymbol());
            priceField.setText(Double.toString(Portfolio.p.investment.get(currentIndex).getPrice()));
            
            if (Portfolio.p.investment.size() == 1) {
                next.setEnabled(false);
            }
        }
    }
    
    /**
     * Method displays appropriate data upon button click (next and previous).
     * @param updateType next or previous button. 
     */
    public void displayUpdate (int updateType) {
        
        if (updateType == NEXT_UPDATE) {
            currentIndex++; 
        } else {   
            currentIndex--; 
        }
        
        nameField.setText(Portfolio.p.investment.get(currentIndex).getName()); 
        symbolField.setText(Portfolio.p.investment.get(currentIndex).getSymbol());
        priceField.setText(Double.toString(Portfolio.p.investment.get(currentIndex).getPrice()));

        if (currentIndex > 0) {
            previous.setEnabled(true);
        } else {
            previous.setEnabled(false);
        }
        
        if (currentIndex == Portfolio.p.investment.size() - 1){
            next.setEnabled(false);
        } else {
            next.setEnabled(true);
        }
    }    
}
