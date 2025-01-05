import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FarmerMarketGUI {

    public static void main(String[] args) {
        new MainFrame();
    }
}

class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private FarmerPanel farmerPanel;
    private ConsumerPanel consumerPanel;
    private AdminPanel adminPanel;

    public MainFrame() {
        setTitle("Farmer Market Platform");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        farmerPanel = new FarmerPanel();
        consumerPanel = new ConsumerPanel();
        adminPanel = new AdminPanel();

        mainPanel.add(new HomePanel(), "Home");
        mainPanel.add(farmerPanel, "Farmer");
        mainPanel.add(consumerPanel, "Consumer");
        mainPanel.add(adminPanel, "Admin");

        add(mainPanel);

        setVisible(true);
    }

    class HomePanel extends JPanel {
        public HomePanel() {
            setLayout(new GridLayout(4, 1));

            JLabel titleLabel = new JLabel("Welcome to Farmer Market", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            add(titleLabel);

            JButton farmerButton = new JButton("Farmer Login");
            JButton consumerButton = new JButton("Consumer Login");
            JButton adminButton = new JButton("Admin Login");

            farmerButton.addActionListener(e -> cardLayout.show(mainPanel, "Farmer"));
            consumerButton.addActionListener(e -> cardLayout.show(mainPanel, "Consumer"));
            adminButton.addActionListener(e -> cardLayout.show(mainPanel, "Admin"));

            add(farmerButton);
            add(consumerButton);
            add(adminButton);
        }
    }
}

class FarmerPanel extends JPanel {

    private ArrayList<Product> products;

    public FarmerPanel() {
        products = new ArrayList<>();
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Farmer Dashboard", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JTextField productNameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JButton addButton = new JButton("Add Product");

        formPanel.add(new JLabel("Product Name:"));
        formPanel.add(productNameField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(quantityField);
        formPanel.add(new JLabel(""));
        formPanel.add(addButton);

        add(formPanel, BorderLayout.CENTER);

        JList<Product> productList = new JList<>(new DefaultListModel<>());
        JScrollPane scrollPane = new JScrollPane(productList);
        add(scrollPane, BorderLayout.EAST);

        addButton.addActionListener(e -> {
            String name = productNameField.getText();
            String priceText = priceField.getText();
            String quantityText = quantityField.getText();

            if (!name.isEmpty() && isNumeric(priceText) && isNumeric(quantityText)) {
                Product product = new Product(name, Double.parseDouble(priceText), Integer.parseInt(quantityText));
                products.add(product);
                ((DefaultListModel<Product>) productList.getModel()).addElement(product);
                JOptionPane.showMessageDialog(this, "Product added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please fill out all fields correctly.");
            }
        });
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class ConsumerPanel extends JPanel {
    public ConsumerPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Consumer Dashboard", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);
    }
}

class AdminPanel extends JPanel {
    public AdminPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Admin Dashboard", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);
    }
}

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - Rs." + price + " - Qty: " + quantity;
    }
}
