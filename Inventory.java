import java.util.ArrayList; // Imports ArrayList for managing inventory items.
import java.util.List; // Imports List for the items collection interface.

public class Inventory {
    private final List<Item> items; // Holds the inventory items.

    public Inventory() {
        items = new ArrayList<>(); // Initializes the inventory as an ArrayList.
    }

    public void addItem(Item item) {
        items.add(item); // Adds an item to the inventory.
    }

    public void removeItem(Item item) {
        items.remove(item); // Removes an item from the inventory.
    }

    public List<Item> getItems() {
        return items; // Returns the list of items in the inventory.
    }

    public boolean contains(String itemName) {
        return items.stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase(itemName)); // Checks if an item with the specified name exists in the inventory.
    }
}
