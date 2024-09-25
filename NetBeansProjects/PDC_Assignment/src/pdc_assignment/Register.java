/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author Soprathna
 */
public class Register extends JFrame {

    private DatabaseManager databaseManager;
    
    public Register() {
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("User Name:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField(20);
         JLabel userGroupLabel = new JLabel("User Group:");
        String[] userGroups = {"admin", "customer"};
        JComboBox<String> userGroupComboBox = new JComboBox<>(userGroups);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back to Sign In");

        // Position User Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(userLabel, constraints);

        // Position User Text Field
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(userText, constraints);

        // Position Password Label
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        // Position Password Text Field
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordText, constraints);

        // Position Group Label
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(userGroupLabel, constraints);

        // Position Group Text Field
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(userGroupComboBox, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel(), constraints);


        // Position Submit Button
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(submitButton, constraints);

        // Position Back Button
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(backButton, constraints);

        add(panel);

        // Initialize the database manager
        databaseManager = new DatabaseManager();
        
        submitButton.addActionListener(e -> {
            String userName = userText.getText();
            String passWord = new String(passwordText.getPassword());
            String selectedUserGroup = (String) userGroupComboBox.getSelectedItem();
            System.out.println("User Name: " + userName);
            System.out.println("Password: " + passWord);
            System.out.println("User Group: " + selectedUserGroup);

            // Register user by inserting data into the database
            try {
                // Check if registering as admin
                if (selectedUserGroup.equals("admin")) {
                    // Prompt for special admin username and password
                    String adminUsername = JOptionPane.showInputDialog(this, "Enter admin username:");
                    String adminPassword = JOptionPane.showInputDialog(this, "Enter admin password:");
                    
                    if (adminUsername != null && adminPassword != null && adminUsername.equals("admin") && adminPassword.equals("admin")) {
                        // Proceed with admin registration
                        databaseManager.insertUser(userName, passWord, selectedUserGroup);
                        JOptionPane.showMessageDialog(this, "Admin registration successful!");
                        new Login().setVisible(true); // Go to login screen after successful registration
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid admin credentials. Please try again.");
                    }
                } else {
                    databaseManager.insertUser(userName, passWord, selectedUserGroup);
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    new Login().setVisible(true); // Go to login screen after successful registration
                    this.dispose();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred during registration.");
            }
        });


        backButton.addActionListener(e -> {
            new Login().setVisible(true);
            this.dispose();
        });
    }
    
    @Override
    public void dispose() {
        super.dispose();
        databaseManager.closeConnection();
    }
}
