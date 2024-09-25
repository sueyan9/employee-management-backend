package pdc_assignment;

/**
 *
 * @author xuyan
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Vector;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.Statement;


public class CartGUI extends JFrame {
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    private DatabaseManager dbManager;
    private JButton checkoutButton;
    private JButton removeButton;
    private Vector<Vector<Object>> carts;

    public CartGUI(Vector<Vector<Object>> carts) {
        this.carts = carts;
        setTitle("Shopping Cart");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        dbManager = new DatabaseManager();

        // Set layout manager
        setLayout(new BorderLayout());

        // Cart Table
        cartTableModel = new DefaultTableModel(new String[]{"Customer ID", "Product ID", "Product Name", "Quantity", "Price"}, 0);
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        add(cartScrollPane, BorderLayout.CENTER);

        // Load cart items
        loadCartItems();

        // Buttons
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement checkout logic here
                int confirmation  = JOptionPane.showConfirmDialog(CartGUI.this,"Confirm checkout?");
                if(confirmation == JOptionPane.YES_OPTION){
                    // Get selected multiple rows of data.

                    int[] selectedRows = cartTable.getSelectedRows();
                    List<CartItem> cartList = new ArrayList<>();
                    Integer customerId = 0;
                    for (int rowIndex : selectedRows) {
                        Vector<Object> selectedRow = carts.get(rowIndex);
                         System.out.println("Selected Row: " + rowIndex);
                         System.out.println("Customer ID: " + selectedRow.get(0));
                         System.out.println("Product ID: " + selectedRow.get(1));
                         System.out.println("Product Name: " + selectedRow.get(2));
                         System.out.println("Quantity: " + selectedRow.get(3));
                         System.out.println("Price: " + selectedRow.get(4));
                         
                        CartItem cartItem = new CartItem();
                        customerId = (Integer) selectedRow.get(0);
                        cartItem.setCustomerId((Integer) selectedRow.get(0));
                        cartItem.setProductId((Integer) selectedRow.get(1));
                        cartItem.setProductName((String) selectedRow.get(2));
                        cartItem.setQuantity((Integer) selectedRow.get(3));
                        cartItem.setPrice((Double) selectedRow.get(4));

                        // Add the CartItem to cartList
                        cartList.add(cartItem);
                     }
                    
                    dbManager.insertOrder(customerId, cartList);
                    
                    dbManager.clearCartItems(cartList);
                    
                    new CustomerGUI(customerId).setVisible(true);
                }
            }
        });
       
        
 
        // Remove Button
        removeButton = new JButton("Remove Selected Item");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cartTable.getSelectedRow();
                System.out.println("Selected row: " + selectedRow); // Debugging statement

                if (selectedRow != -1) {
                    // 1. Get the selected product ID
                    Object productIdObj = carts.get(selectedRow).get(1); 
                    System.out.println("Product ID Object: " + productIdObj); // Debugging statement

                    try {
                        int productId = Integer.parseInt(productIdObj.toString());
                        System.out.println("Product ID: " + productId); // Debugging statement

                        // 2. Update this info in the database
                        boolean deleteCartItem = dbManager.deleteCartItem(productId);
                        System.out.println("Delete Cart Item result: " + deleteCartItem); // Debugging statement

                        if (deleteCartItem) {
                            // Remove row from table model after successful deletion
                            cartTableModel.removeRow(selectedRow);
                            System.out.println("Row removed from table model: " + selectedRow); // Debugging statement
                            JOptionPane.showMessageDialog(CartGUI.this, "Item removed from cart.");
                        } else {
                            System.out.println("Failed to remove item from database."); // Debugging statement
                            JOptionPane.showMessageDialog(CartGUI.this, "Failed to remove item from cart.");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid product ID format."); // Debugging statement
                        JOptionPane.showMessageDialog(CartGUI.this, "Invalid product ID format.");
                    }
                } else {
                    System.out.println("No item selected."); // Debugging statement
                    JOptionPane.showMessageDialog(CartGUI.this, "No item selected.");
                }
            }
        });

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkoutButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCartItems() {
        for (Vector<Object> cart : carts) {
            cartTableModel.addRow(cart);
        }
    }
    private Vector<Vector<Object>> getCartItems() {
        Vector<Vector<Object>> cartItems = new Vector<>();
        for (int i = 0; i < cartTable.getRowCount(); i++) {
            Vector<Object> row = new Vector<>();
            for (int j = 0; j < cartTable.getColumnCount(); j++) {
                row.add(cartTable.getValueAt(i, j));
            }
            cartItems.add(row);
        }
        return cartItems;
    }

    private void processPurchase() throws SQLException {
        cartTableModel.setRowCount(0);
        JOptionPane.showMessageDialog(this, "Purchase successful!");
    }
    private void clearCart() {
        // Remove all rows from the cart table model
        cartTableModel.setRowCount(0);

        // Optional: Clear the 'carts' vector if needed
        carts.clear();

        // Update the UI to reflect the changes
        cartTable.revalidate();
        cartTable.repaint();
}
    
    private double calculateTotal() {
    double total = 0;
    for (Vector<Object> cartItem : carts) {
        total += (double) cartItem.get(3) * (int) cartItem.get(2); 
    }
    return total;
}
    // Provide a method to load and display order history
    private void loadOrderHistory(int customerId) {
        // Fetch order history data from the database
    Vector<Vector<Object>> orderHistory = dbManager.selectCustomerOrderHistory(customerId);

    // Display the fetched order history in the cart table
    for (Vector<Object> order : orderHistory) {
        cartTableModel.addRow(order);
    }
    }

} 