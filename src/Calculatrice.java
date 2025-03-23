import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculatrice extends JFrame {

    public Calculatrice() {
        // Frame setup
        JFrame window = new JFrame("Calculatrice");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 600);
        window.setLocationRelativeTo(null); // Center the window on the screen

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding

        // Screen (JTextArea)
        JTextArea screen = new JTextArea();
        screen.setFont(new Font("Roboto", Font.PLAIN, 36)); // Modern font
        screen.setEditable(false);
        screen.setBackground(new Color(255, 255, 255)); // White background
        screen.setForeground(new Color(50, 50, 50));
        screen.setBorder(null); // Dark gray text
        screen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2), // Light gray border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding inside the border
        ));

        JPanel screenPanel = new JPanel(new BorderLayout());
screenPanel.setBackground(new Color(240, 240, 240)); // Match the main panel background
screenPanel.setBorder(new EmptyBorder(0, 0, 20, 0)); // Add 20px padding at the bottom
screenPanel.add(screen, BorderLayout.CENTER);
        mainPanel.add(screenPanel, BorderLayout.NORTH);

        // Buttons panel with GridLayout
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // 5 rows, 4 columns, with gaps
        buttonsPanel.setBackground(new Color(240, 240, 240)); // Light gray background

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
                    float result = Operations.evaluateExpression(expression);
                    screen.setText(String.valueOf(result));
                } catch (ArithmeticException | IllegalArgumentException ex) {
                    screen.setText("Error");
                }
            }
        });

        // Panel for the "=" button
        JPanel equalsPanel = new JPanel(new BorderLayout());
        equalsPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        equalsPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // Add padding
        equalsPanel.add(equalsButton, BorderLayout.CENTER);

        // Add equals panel to the main panel
        mainPanel.add(equalsPanel, BorderLayout.SOUTH);

        // Add main panel to the window
        window.add(mainPanel);
        window.setVisible(true);

        // Add buttons panel to the main panel
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Add main panel to the window
        window.add(mainPanel);
        window.setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Roboto", Font.BOLD, 20)); // Modern font
        button.setBackground(new Color(50, 150, 250)); // Blue background
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 120, 200), 2), // Darker blue border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding inside the border
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 120, 200)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 150, 250)); // Restore original color
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculatrice();
            }
        });
    }
}