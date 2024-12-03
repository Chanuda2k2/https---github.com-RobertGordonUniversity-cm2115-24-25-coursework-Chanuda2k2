import java.util.ArrayList; // Imports ArrayList for the player's inventory.
import java.util.List; // Imports List for the inventory collection interface.

public final class Player {
    private final String name; // The player's name.
    private int health; // The player's health.
    private int magic; // The player's magic points.
    private final List<Item> inventory; // The player's inventory of items.
    private int attackBoost; // Tracks how many next attacks are boosted.

    public Player(String name, int health, int magic) {
        this.name = name;
        this.health = health;
        this.magic = magic;
        this.inventory = new ArrayList<>();
        this.attackBoost = 0; // No attack boost by default
        addItem(new Item("Wand")); // Start with a wand
        addItem(new Item("Parchment")); // Start with parchment
        addItem(new Item("Sword")); // Start with a sword
    }

    public void takeDamage(int damage) {
        health -= damage; // Subtracts health based on incoming damage.
        if (health <= 0) {
            health = 0;
            System.out.println("You have perished in the land of King Kraith. Game over.");
            System.exit(0); // Ends the game if health reaches 0.
        }
    }

    public void heal(int amount) {
        health += amount; // Heals the player by a certain amount.
        if (health > 100) {
            health = 100; // Limits health to 100.
        }
    }

    public void useMagic(int amount) {
        magic -= amount; // Decreases magic by the specified amount.
        if (magic < 0) {
            magic = 0; // Ensures magic cannot go below 0.
        }
    }

    public void addItem(Item item) {
        inventory.add(item); // Adds an item to the inventory.
    }

    public List<Item> getInventory() {
        return inventory; // Returns the player's inventory.
    }

    public int getHealth() {
        return health; // Returns the player's health.
    }

    public int getMagic() {
        return magic; // Returns the player's magic points.
    }

    public void setAttackBoost(int boost) {
        this.attackBoost = boost; // Sets the attack boost.
    }

    // Method to calculate damage based on the item used
    public int calculateDamage(String itemName, GameGUI gui) {
        int damage = 0;

        if (attackBoost > 0) {
            attackBoost--; // Decreases the boost for the next attack.
            damage = 16; // Double the attack damage temporarily.
        }

        switch (itemName) {
            case "Sword" -> damage += 8; // Sword deals 8 damage.
            case "Wand" -> damage += 10; // Wand deals 10 damage.
            case "Magic Potion" -> {
                damage += 15; // Magic potion deals 15 damage.
                useMagic(10); // Reduce magic by potion cost.
                gui.updateMagic(getMagic()); // Updates magic display in GUI.
            }
        }

        return damage; // Returns the total calculated damage.
    }
}
