import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOError;

public class cal extends JFrame {

    public () {
        // Frame setup - remove the unnecessary JFrame creation since this class extends JFrame
        setTitle("Calculatrice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null); // Center the window on the screen

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK); // Set background to black
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding

        // Screen (JTextArea)
        JTextArea screen = new JTextArea();
        screen.setFont(new Font("Roboto", Font.PLAIN, 36)); // Modern font
        screen.setEditable(false);
        screen.setBackground(new Color(30, 30, 30)); // Dark background
        screen.setForeground(new Color(240, 240, 240)); // Light text
        screen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 50, 50), 2), // Dark border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding inside the border
        ));

        JPanel screenPanel = new JPanel(new BorderLayout());
        screenPanel.setBackground(Color.BLACK); // Match the main panel background
        screenPanel.setBorder(new EmptyBorder(0, 0, 20, 0)); // Add 20px padding at the bottom
        screenPanel.add(screen, BorderLayout.CENTER);
        mainPanel.add(screenPanel, BorderLayout.NORTH);

        // Buttons panel with GridLayout
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // 4 rows, 4 columns, with gaps
        buttonsPanel.setBackground(Color.BLACK); // Set to black

        // Button labels
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+"
        };

        // Create and style buttons
        for (String label : buttonLabels) {
            JButton button = createStyledButton(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (label.equals("C")) {
                        // Clear the screen
                        screen.setText("");
                    } else {
                        // Append the button's text to the screen
                        screen.setText(screen.getText() + label);
                    }
                }
            });
            buttonsPanel.add(button);
        }

        JButton equalsButton = createStyledButton("=");
        equalsButton.setPreferredSize(new Dimension(400, 70)); // Set preferred size
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Evaluate the expression
                try {
                    String expression = screen.getText();
                    // Note: You'll need to implement the Operations class with evaluateExpression method
                    // or modify this part to use a different evaluation approach
                    float result = 0; // Replace with actual calculation
                    // Example placeholder for calculation logic
                    // float result = Operations.evaluateExpression(expression);
                    
                    if((result-(int)result) > 0) {
                        screen.setText(String.valueOf(result));
                    } else {
                        screen.setText(String.valueOf((int)result));
                    }
                } catch (Exception ex) {
                    screen.setText("Error");
                }
            }
        });

        // Panel for the "=" button
        JPanel equalsPanel = new JPanel(new BorderLayout());
        equalsPanel.setBackground(Color.BLACK); // Set to black
        equalsPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // Add padding
        equalsPanel.add(equalsButton, BorderLayout.CENTER);

        // Add components to the main panel
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(equalsPanel, BorderLayout.SOUTH);

        // Add main panel to the frame (this)
        add(mainPanel);
        setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Roboto", Font.BOLD, 20)); // Modern font
        
        // Different colors for different button types
        if (label.equals("=")) {
            button.setBackground(new Color(0, 150, 136)); // Teal for equals
        } else if (label.equals("C")) {
            button.setBackground(new Color(239, 83, 80)); // Red for clear
        } else if (label.matches("[+\\-*/]")) {
            button.setBackground(new Color(255, 152, 0)); // Orange for operators
        } else {
            button.setBackground(new Color(66, 66, 66)); // Dark gray for numbers
        }
        
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 40, 40), 1), // Dark border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding inside the border
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(button.getBackground().darker()); // Darker on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(button.getBackground().brighter()); // Restore original color
            }
        });

        return button;
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Calculatrice();
                }
            });
        } catch (IOError e) {
            System.err.println("IO Error: " + e.getMessage());
        }
    }
}