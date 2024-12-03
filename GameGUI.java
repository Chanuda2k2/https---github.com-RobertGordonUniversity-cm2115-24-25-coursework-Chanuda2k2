import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameGUI {
    private final JFrame frame;
    private final JTextArea storyArea;
    private final JLabel healthLabel;
    private final JLabel magicLabel;
    private final DefaultListModel<String> inventoryModel;
    private final JList<String> inventoryList;
    private final JPanel buttonPanel;

    public GameGUI() {
        // Frame setup
        frame = new JFrame("King Kraith");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);

        // Header
        JLabel title = new JLabel("King Kraith", SwingConstants.CENTER);
        title.setFont(new Font("Blackletter", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        frame.add(title, BorderLayout.NORTH);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.BLACK);
        sidebar.setPreferredSize(new Dimension(250, 700));

        healthLabel = createStyledLabel("Health: 100");
        magicLabel = createStyledLabel("Magic: 50");

        JLabel inventoryLabel = createStyledLabel("Inventory:");
        inventoryModel = new DefaultListModel<>();
        inventoryList = new JList<>(inventoryModel);
        inventoryList.setBackground(Color.DARK_GRAY);
        inventoryList.setForeground(Color.WHITE);

        sidebar.add(healthLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(magicLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(inventoryLabel);
        sidebar.add(new JScrollPane(inventoryList));
        frame.add(sidebar, BorderLayout.WEST);

        // Story area
        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setWrapStyleWord(true);
        storyArea.setLineWrap(true);
        storyArea.setFont(new Font("Blackletter", Font.PLAIN, 18));
        storyArea.setForeground(Color.WHITE);
        storyArea.setBackground(Color.BLACK);
        frame.add(new JScrollPane(storyArea), BorderLayout.CENTER);

        // Button panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10));
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Blackletter", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    public void updateStory(String text) {
        storyArea.setText(text);
    }

    public void updateHealth(int health) {
        healthLabel.setText("Health: " + health);
    }

    public void updateMagic(int magic) {
        magicLabel.setText("Magic: " + magic);
    }

    public void addItem(String item) {
        inventoryModel.addElement(item);
    }

    public void clearInventory() {
        inventoryModel.clear();
    }

    public void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        buttonPanel.add(button);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public void clearButtons() {
        buttonPanel.removeAll();
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}
