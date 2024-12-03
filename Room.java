import java.util.HashMap;
import java.util.Map;

public abstract class Room {
    protected String description;
    protected Map<String, Room> exits;
    protected boolean isCompleted;

    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.isCompleted = false;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public boolean canMove(String direction) {
        return exits.containsKey(direction) && isCompleted;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getDescription() {
        return description + (isCompleted ? "\nThe way forward is clear." : "\nYou must complete the challenges here to proceed.");
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public abstract void solveChallenge(GameGUI gui, Player player);

    public abstract void fightEnemy(Player player, GameGUI gui);

    protected void markCompleted() {
        this.isCompleted = true;
    }

    // Method to show battle results without exiting
    protected void showBattleResult(GameGUI gui, Player player, Enemy enemy) {
        if (enemy.isAlive()) {
            gui.updateStory("You strike the " + enemy.getName() + "!" + " Yu get hit. Now Your Health -5");
            player.takeDamage(5);
            gui.updateHealth(player.getHealth());
            gui.updateStory("The " + enemy.getName() + " strikes back Damagin Your Health -5");
        } else {
            gui.updateStory("You have defeated the " + enemy.getName() + "!"+ " Your Health restored");
            player.heal(player.getHealth() / 2); // Boost health by half
            gui.updateHealth(player.getHealth());
            gui.addItem("Sword"); // Add sword to inventory
            markCompleted();
        }
    }
}
