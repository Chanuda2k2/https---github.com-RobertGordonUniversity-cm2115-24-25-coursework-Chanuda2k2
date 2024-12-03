
import javax.swing.JOptionPane;

public class PuzzleRoom extends Room {
    private final Puzzle puzzle;

    public PuzzleRoom(String description, Puzzle puzzle) {
        super(description);
        this.puzzle = puzzle;
    }

    @Override
    public void solveChallenge(GameGUI gui, Player player) {
        if (isCompleted) {
            gui.updateStory("The puzzle has already been solved. Move forward.");
            return;
        }

        gui.updateStory("Puzzle: " + puzzle.getRiddle());
        gui.addButton("Submit Answer", _ -> {
            String answer = JOptionPane.showInputDialog(null, "Enter your answer:");
            if (puzzle.solve(answer)) {
                gui.updateStory("Correct! You feel a surge of power.");
                player.useMagic(-20); // Magic boost
                gui.updateMagic(player.getMagic());
                player.addItem(new Item("Magic Potion"));
                markCompleted();
                // Apply attack boost
                player.setAttackBoost(2); // Next two attacks will be more powerful
            } else {
                gui.updateStory("Wrong answer. Try again.");
            }
        });
    }

    @Override
    public void fightEnemy(Player player, GameGUI gui) {
        gui.updateStory("There are no enemies here. Solve the puzzle to proceed.");
    }
}
