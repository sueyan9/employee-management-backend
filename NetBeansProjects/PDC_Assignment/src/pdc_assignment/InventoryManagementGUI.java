/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import com.sun.jdi.connect.spi.Connection;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.sql.SQLException;

/**
 *
 * @author Soprathna
 */
public class InventoryManagementGUI extends JFrame {

    public InventoryManagementGUI() {
        setTitle("Inventory Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons for different functionalities
        JButton addButton = new JButton("Add Product");
        
        JButton removeButton = new JButton("Remove Product");
        JButton viewInventoryButton = new JButton("View Inventory");
        JButton viewCustomerOrderHistoryButton = new JButton("View Customer Order History");

        // Set layout
        setLayout(new GridLayout(4, 2));

        // Add buttons to the frame
        add(addButton);
        
        add(removeButton);
        add(viewInventoryButton);
        add(viewCustomerOrderHistoryButton);
        
        String url = "jdbc:derby:/Users/xuyan/Downloads/db-derby-10.14.2.0-bin/inventory;create=true";
       String user = "username"; // Update with your database username
        String password = "password"; // Update with your database password


       // Add action listeners for button clicks
        addButton.addActionListener(e -> handleAdd());
        removeButton.addActionListener(e -> handleRemove());
        viewInventoryButton.addActionListener(e -> handleViewInventory());
        viewCustomerOrderHistoryButton.addActionListener(e -> handleViewCustomerOrderHistory());
        addButton.addActionListener(e -> new AddProductFrame().setVisible(true));
    }

    private void handleAdd() {
        System.out.println("Handling add...");
        // Implement add functionality here
        AddProductFrame addProductFrame = new AddProductFrame();
        addProductFrame.setVisible(true);
    }
    private void handleRemove() {
        System.out.println("Handling Remove...");
        // Implement remove functionality here
        RemoveProductFrame removeProductFrame = new RemoveProductFrame();
        removeProductFrame.setVisible(true);
    }

    private void handleViewInventory() {
        System.out.println("Handling View Inventory...");
        // Implement view inventory functionality here
        ViewInventoryFrame viewInventoryFrame = new ViewInventoryFrame();
        viewInventoryFrame.setVisible(true);
    }
    
    private void handleViewCustomerOrderHistory() {
        System.out.println("Handling View Customer Order History...");
        ViewCustomerOrderHistoryFrame viewCustomerOrderHistoryFrame = new ViewCustomerOrderHistoryFrame();
        viewCustomerOrderHistoryFrame.setVisible(true);
    }
    
}