import javax.swing.*;

public class Temperature_converter extends JFrame {
    private JComboBox<String> sourceUnitComboBox;
    private JComboBox<String> targetUnitComboBox;
    private JTextField inputTextField;
    private JTextField resultTextField;

    public Temperature_converter() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Temperature Converter");

        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Temperature Converter");
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));
        panel.add(titleLabel);

        sourceUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        targetUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        inputTextField = new JTextField(10);
        resultTextField = new JTextField(10);
        resultTextField.setEditable(false);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(evt -> convertTemperature());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(evt -> clearFields());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(evt -> System.exit(0));

        panel.add(sourceUnitComboBox);
        panel.add(inputTextField);
        panel.add(convertButton);
        panel.add(targetUnitComboBox);
        panel.add(resultTextField);
        panel.add(clearButton);
        panel.add(exitButton);

        getContentPane().add(panel);
        pack();
    }

    private void convertTemperature() {
        String sourceUnit = (String) sourceUnitComboBox.getSelectedItem();
        String targetUnit = (String) targetUnitComboBox.getSelectedItem();
        String inputText = inputTextField.getText().trim();

        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a temperature value.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double inputValue = Double.parseDouble(inputText);
            double result;

            if (sourceUnit.equals("Celsius") && targetUnit.equals("Fahrenheit")) {
                result = (inputValue * 9 / 5) + 32;
            } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Celsius")) {
                result = (inputValue - 32) * 5 / 9;
            } else {
                result = inputValue; // Conversion not needed
            }

            resultTextField.setText(String.format("%.2f", result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid temperature value entered.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        inputTextField.setText("");
        resultTextField.setText("");
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new Temperature_converter().setVisible(true));
    }
}
