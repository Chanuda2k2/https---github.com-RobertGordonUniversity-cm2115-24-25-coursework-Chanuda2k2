public class Enemy { 
    // This class represents an enemy with a name and health.
    
    private final String name; 
    // The name of the enemy. It's final, so it can't change once set.

    private int health; 
    // The health of the enemy, which can decrease when damaged.

    public Enemy(String name, int health) { 
        // Constructor to create an enemy with a name and starting health.
        this.name = name;
        this.health = health;
    }

    public String getName() { 
        // Returns the name of the enemy.
        return name;
    }

    public int getHealth() { 
        // Returns the current health of the enemy.
        return health;
    }

    public boolean isAlive() { 
        // Checks if the enemy is still alive (health > 0).
        return health > 0;
    }

    public void takeDamage(int damage) { 
        // Reduces the enemy's health by the damage amount.
        // If health goes below 0, it sets health to 0.
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}
