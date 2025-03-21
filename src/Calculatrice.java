import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Dimension2D;

public class Calculatrice extends JFrame {

    public Calculatrice() {
        JFrame window = new JFrame("Calculatrice");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 500);
        window.setLocation(500,150);

        JTextArea screen = new JTextArea();
        screen.setFont(new Font("Arial", Font.PLAIN, 36));
        screen.setEditable(false);
        screen.setSize(370,100);
        screen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // Set a rounded border for the JTextArea
        Border roundedBorder = BorderFactory.createLineBorder(Color.GREEN, 2, true);
        screen.setBorder(roundedBorder);
        screen.setLocation(100,10);
        window.add(screen, BorderLayout.NORTH);

        JPanel numberspanel = new JPanel();
        numberspanel.setLayout(new GridLayout(4, 4, 10, 10));
        for(int i=9;i>=1;i--){
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screen.setText(screen.getText()+button.getText());
                }
            });
            button.setFont(new Font("Arial", Font.PLAIN, 13));
            button.setSize(50,50);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberspanel.add(button);
        }
        // button 0
        JButton button0 = new JButton("0");
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText(screen.getText()+button0.getText());
            }
        });
        button0.setFont(new Font("Arial", Font.PLAIN, 16));
        button0.setSize(50,50);
        button0.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        numberspanel.add(button0);

        // button 0

        //buttonC
        JButton buttonC = new JButton("C");
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText("");
            }
        });
        numberspanel.add(buttonC);

        //buttonC

        //button+
        JButton buttonpl = new JButton("+");
            buttonpl.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screen.setText(screen.getText()+buttonpl.getText());
                }
            });
            buttonpl.setFont(new Font("Arial", Font.PLAIN, 16));
            buttonpl.setSize(50,50);
            buttonpl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberspanel.add(buttonpl);
        //button+

        //button-
        JButton buttonM = new JButton("-");
            buttonM.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screen.setText(screen.getText()+buttonM.getText());
                }
            });
            buttonM.setFont(new Font("Arial", Font.PLAIN, 16));
            buttonM.setSize(50,50);
            buttonM.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberspanel.add(buttonM);
        //button-

        //button*
        JButton buttonMUL = new JButton("*");
            buttonMUL.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screen.setText(screen.getText()+buttonMUL.getText());
                }
            });
            buttonMUL.setFont(new Font("Arial", Font.PLAIN, 16));
            buttonMUL.setSize(50,50);
            buttonMUL.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberspanel.add(buttonMUL);
        //button*

        //button/
        JButton buttondiv = new JButton("/");
        buttondiv.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screen.setText(screen.getText()+buttondiv.getText());
                }
            });
            buttondiv.setFont(new Font("Arial", Font.PLAIN, 16));
            buttondiv.setSize(50,50);
            buttondiv.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            numberspanel.add(buttondiv);
        //button/

        //button=
        JButton buttonEg = new JButton("=");
        buttonEg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String expression = screen.getText();
                    float result = opperations.evaluateExpression(expression);
                    screen.setText(String.valueOf(result));
                } catch (ArithmeticException | IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        numberspanel.add(buttonEg);

        //button=


        //************************************************************************************* */

        numberspanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        window.add(numberspanel);
        

        window.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculatrice();
            }
        });
    }
}