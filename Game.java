import java.awt.event.ActionEvent;

public class Game {
    private final Player player;
    private Room currentRoom;
    private final GameGUI gui;

    public Game() {
        gui = new GameGUI();
        player = new Player("Hero", 100, 50);
        setupRooms();
    }

    public void start() {
        gui.updateStory("Welcome to the world of King Kraith. Your journey begins...");
        updateUI();
        setupActions();
    }

    private void setupRooms() {
        // Room 1: Starting Room - Forest
        Room forestRoom = new PuzzleRoom(
                """
        You wake in a beautiful, mysterious land. The air is floral, sunlight filters through towering trees, and colorful wildflowers dot the rolling hills. A strange calm surrounds you.        
      As you explore, you find a glowing wooden box. Inside are a wand and parchment with a riddle     
        'What has to be broken before you can use it?'""",
                new Puzzle("What has to be broken before you can use it?", "egg")
        );
    
        // Room 2: Encounter with 1 Kraith - Castle Gates
        Room castleGateRoom = new EnemyRoom(
                "When spoken aloud, a magical door appears.Inside the door, a Kraith (monstrous creature) ambushes you..",
                new Enemy("Kraith", 10)
        );
    
        // Room 3: Encounter with 3 Kraiths - Castle Hall
        Room castleHallRoom = new EnemyRoom(
                "you defeat the Kraith. The parchment changes, revealing a new word. A magical door appears again.You enter the castle's main hall, where three Kraiths surround a broken fountain. Their eyes glow red, and their teeth gnash as they prepare to attack.",
                new Enemy("Kraith", 30) // Combined health for 3 Kraiths
        );
    
        // Room 4: First Power Boost Puzzle
        Room libraryRoom = new PuzzleRoom(
                """
You enter a castle and encounter three Kraiths and their Mother Kraith. You must decide how to approach.olving its riddle activates a magical energy bar, boosting your wand's power. You face the Mother Kraith with newfound strength.                'The more you take, the more you leave behind. What is it?'""",
                new Puzzle("The more you take, the more you leave behind. What is it?", "footsteps")
        );
    
        // Room 5: Encounter with 5 Kraiths - Throne Room
        Room throneRoom = new EnemyRoom(
                "After defeating the Kraiths a New door Opens, The parchment leads you to a Sky Temple filled with flying Kraiths.",
                new Enemy("Kraith", 50) // Combined health for 5 Kraiths
        );
    
        // Room 6: Encounter with 1 Mother Kraith - Hidden Lair
        Room hiddenLairRoom = new UltimateRoom(
                """
                In the depths of the temple lies a hidden lair. A massive Mother Kraith guards the entrance to the next world. A glowing riddle is etched into the walls:
                """,
                new Puzzle("I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?", "echo"),
                new Enemy("Mother Kraith", 60)
        );
    
        // Room 7: Transition to the Sky Temple
        Room skyTempleRoom = new PuzzleRoom(
                """
                A portal transports you to a  TemplSy. The clouds swirl around the marble floors. On a pedestal, another riddle glows:
                'I always running, but I never move. What am I?'""",
                new Puzzle("I’m always running, but I never move. What am I?", "time")
        );
    
        // Room 8: Encounter with 2 Mother Kraiths - Celestial Arena
        Room celestialArenaRoom = new EnemyRoom(
                "In the STemplSy's arena, two Mother Kraiths emerge from portals. Their roars echo across the heavens, and they attack with coordinated fury.",
                new Enemy("Mother Kraiths", 80) // Combined health for 2 Mother Kraiths
        );
    
        // Room 9: Rescue the Princess - Hell's Antechamber
        Room hellAntechamberRoom = new PuzzleRoom(
                """
                You descend into Hell's fiery depths. In a glowing cage, the Princess calls out to you, her voice barely audible over the flames. A riddle burns on the cage:
                'What comes once in a minute, twice in a moment, but never in a thousand years?'""",
                new Puzzle("What comes once in a minute, twice in a moment, but never in a thousand years?", "m")
        );
    
        // Room 10: Final Battle - Hell Chamber with King Kraith
        Room hellChamberRoom = new UltimateRoom(
                "You enter Hell's final chamber. The King Kraith looms over you, his form engulfed in flames. The Princess stands by your side, ready to aid you in this ultimate fight.",
                new Puzzle("Solve this: 'I am not alive, but I can grow. I don’t have lungs, but I need air. What am I?'", "fire"),
                new Enemy("King Kraith", 100)
        );
    
        // Connecting rooms
        forestRoom.setExit("forward", castleGateRoom);
        castleGateRoom.setExit("forward", castleHallRoom);
        castleHallRoom.setExit("forward", libraryRoom);
        libraryRoom.setExit("forward", throneRoom);
        throneRoom.setExit("forward", hiddenLairRoom);
        hiddenLairRoom.setExit("forward", skyTempleRoom);
        skyTempleRoom.setExit("forward", celestialArenaRoom);
        celestialArenaRoom.setExit("forward", hellAntechamberRoom);
        hellAntechamberRoom.setExit("forward", hellChamberRoom);
    
        // Set initial room
        currentRoom = forestRoom;
    }
    

    private void setupActions() {
        gui.clearButtons();
    
        if (currentRoom.isCompleted() && currentRoom.canMove("forward")) {
            gui.addButton("Move Forward", _ -> move("forward")); }
        
        gui.addButton("Move Forward", _ -> move("forward"));
        gui.addButton("Solve Puzzle", _ -> currentRoom.solveChallenge(gui, player));
        gui.addButton("Fight Enemy", _ -> currentRoom.fightEnemy(player, gui));
    
}
    
    private void move(String direction) {
        if (currentRoom.isCompleted() && currentRoom.canMove(direction)) {
            currentRoom = currentRoom.getExit(direction);
            gui.updateStory(currentRoom.getDescription());
            updateUI();
            setupActions();
        } else {
            gui.updateStory("You must complete the room's challenges to proceed.");
        }
    }
    

    private void updateUI() {
        gui.updateHealth(player.getHealth());
        gui.updateMagic(player.getMagic());
        gui.clearInventory();
        player.getInventory().forEach(item -> gui.addItem(item.getName()));
    }
    
}