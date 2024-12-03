
import javax.swing.JOptionPane;

public class UltimateRoom extends Room {
    private final Puzzle puzzle;
    private final Enemy enemy;
    private boolean puzzleSolved;
    private boolean enemyDefeated;

    public UltimateRoom(String description, Puzzle puzzle, Enemy enemy) {
        super(description);
        this.puzzle = puzzle;
        this.enemy = enemy;
        this.puzzleSolved = false;
        this.enemyDefeated = false;
    }

    @Override
    public void solveChallenge(GameGUI gui, Player player) {
        if (puzzleSolved) {
            gui.updateStory("The puzzle has already been solved. Prepare for the final battle!");
            return;
        }

        gui.updateStory("Puzzle: " + puzzle.getRiddle());
        gui.addButton("Submit Answer", _ -> {
            String answer = JOptionPane.showInputDialog(null, "Enter your answer:");
            if (puzzle.solve(answer)) {
                gui.updateStory("Correct! You feel a surge of power for the final battle.");
                player.setAttackBoost(2); // Boost for the next two attacks
                player.useMagic(-20); // Magic boost
                gui.updateMagic(player.getMagic());
                puzzleSolved = true;
            } else {
                gui.updateStory("Incorrect! You must face King Kraith without the puzzle's aid.");
            }
        });
    }

    @Override
    public void fightEnemy(Player player, GameGUI gui) {
        if (!puzzleSolved) {
            gui.updateStory("Solve the puzzle before engaging King Kraith.");
            return;
        }

        gui.updateStory("The final battle begins! King Kraith roars, flames erupting around him.");
        gui.addButton("Use Sword", _ -> {
            executeAttack("Sword", player, gui);
        });

        gui.addButton("Use Wand", _ -> {
            executeAttack("Wand", player, gui);
        });

        gui.addButton("Use Magic Potion", _ -> {
            executeAttack("Magic Potion", player, gui);
        });
    }

    private void executeAttack(String weapon, Player player, GameGUI gui) {
        int damage = player.calculateDamage(weapon, gui);
        enemy.takeDamage(damage);
        gui.updateStory("You attack with your " + weapon + "! King Kraith takes " + damage + " damage.");
        if (enemy.isAlive()) {
            gui.updateStory("King Kraith's health is now: " + enemy.getHealth());
            // Enemy counter-attack
            player.takeDamage(15);
            gui.updateStory("King Kraith attacks! Your health is now: " + player.getHealth());
            gui.updateHealth(player.getHealth());
            if (player.getHealth() <= 0) {
                endGame(gui, false);
            }
        } else {
            gui.updateStory("You have defeated King Kraith!");
            endGame(gui, true);
        }
    }

    private void endGame(GameGUI gui, boolean won) {
        if (won) {
            gui.updateStory("Congratulations! You have defeated King Kraith and rescued the Princess!");
        } else {
            gui.updateStory("You have been defeated. Play again.");
        }
        markCompleted();
        gui.clearButtons();
        gui.addButton("Play Again", _ -> restartGame(gui));
    }

    private void restartGame(GameGUI gui) {
        Game newGame = new Game();
        newGame.start();
    }
}
