public class EnemyRoom extends Room { // Class represents a room with an enemy that the player must fight.
    private final Enemy enemy;

    public EnemyRoom(String description, Enemy enemy) {
        super(description);
        this.enemy = enemy;
    }

    @Override
    public void solveChallenge(GameGUI gui, Player player) {
                // Overrides the method to display a message that there’s no puzzle here, just an enemy to fight.
        gui.updateStory("There is no puzzle here. Defeat the enemy to proceed!");
    }

    @Override
    public void fightEnemy(Player player, GameGUI gui) {
         // Handles the fight sequence between the player and the enemy.
        if (!enemy.isAlive()) {
            gui.updateStory("The enemy has already been defeated.");
            return;
        }

        // Show Kraith health and ask the player to select an item from the inventory
        gui.updateStory("You engage the " + enemy.getName() + "!\n" +
                "The " + enemy.getName() + "'s health is currently: " + enemy.getHealth() + " HP.");

        gui.addButton("Use Sword", _ -> {
            int damage = player.calculateDamage("Sword", gui);
            enemy.takeDamage(damage);
            gui.updateStory("You attack with your sword! The Kraith takes " + damage + " damage.");
            if (enemy.isAlive()) {
                gui.updateStory("The Kraith's health is now: " + enemy.getHealth());
            } else {
                showBattleResult(gui, player, enemy);
            }
        });

        gui.addButton("Use Wand", _ -> {
            int damage = player.calculateDamage("Wand", gui);
            enemy.takeDamage(damage);
            gui.updateStory("You attack with your wand! The Kraith takes " + damage + " damage.");
            if (enemy.isAlive()) {
                gui.updateStory("The Kraith's health is now: " + enemy.getHealth());
            } else {
                showBattleResult(gui, player, enemy);
            }
        });

        gui.addButton("Use Magic Potion", _ -> {
            int damage = player.calculateDamage("Magic Potion", gui);
            enemy.takeDamage(damage);
            gui.updateStory("You attack with a magic potion! The Kraith takes " + damage + " damage.");
            if (enemy.isAlive()) {
                gui.updateStory("The Kraith's health is now: " + enemy.getHealth());
            } else {
                showBattleResult(gui, player, enemy);
            }
        });
    }

    @Override
    protected void showBattleResult(GameGUI gui, Player player, Enemy enemy) {
        gui.updateStory("You have defeated the " + enemy.getName() + "!");
        player.heal(player.getHealth() / 2); // Boost health by half
        gui.updateHealth(player.getHealth());
        gui.addItem("Sword"); // Add sword to inventory
        markCompleted();
    }
}
