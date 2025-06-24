import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGeneratorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel("Password Generator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        JLabel passwordLabel = new JLabel("Generated Password:");
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(passwordLabel);

        JTextField passwordField = new JTextField(20);
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(passwordField);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton copyButton = new JButton("Copy to Clipboard");
        copyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        copyButton.addActionListener(e -> {
            if (!passwordField.getText().isEmpty()) {
                StringSelection stringSelection = new StringSelection(passwordField.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(frame, "Password copied to clipboard!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainPanel.add(copyButton);
        mainPanel.add(Box.createVerticalStrut(10));


        JLabel lengthLabel = new JLabel("Length (8-50):");
        lengthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lengthLabel);

        JTextField lengthField = new JTextField("12", 5);
        lengthField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lengthField);
        mainPanel.add(Box.createVerticalStrut(10));

        JCheckBox uppercaseCheck = new JCheckBox("Include Uppercase Letters", true);
        uppercaseCheck.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(uppercaseCheck);

        JCheckBox lowercaseCheck = new JCheckBox("Include Lowercase Letters", true);
        lowercaseCheck.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lowercaseCheck);

        JCheckBox numbersCheck = new JCheckBox("Include Numbers", true);
        numbersCheck.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(numbersCheck);

        JCheckBox symbolsCheck = new JCheckBox("Include Symbols", true);
        symbolsCheck.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(symbolsCheck);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton generateButton = new JButton("Generate Password");
        generateButton.setBackground(new Color(0, 140, 186));
        generateButton.setForeground(Color.WHITE);
        generateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int length = Integer.parseInt(lengthField.getText());
                    String password = PasswordGenerator.generatePassword(
                            length,
                            uppercaseCheck.isSelected(),
                            lowercaseCheck.isSelected(),
                            numbersCheck.isSelected(),
                            symbolsCheck.isSelected()
                    );
                    passwordField.setText(password);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for length.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(generateButton);


        frame.add(mainPanel);
        frame.setVisible(true);

        generateButton.doClick();
    }
}