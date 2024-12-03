import java.io.*;

// Class to handle saving and loading game data to and from files.
public class FileHandler {

    public static void saveGame(String data, String filename) {
        // Saves game data to the specified file.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data); // Writes the provided data to the file.
        } catch (IOException e) {
            // Handles errors that occur while saving.
            System.out.println("Failed to save the game: " + e.getMessage());
        }
    }

    public static String loadGame(String filename) {
        // Loads game data from the specified file.
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder data = new StringBuilder(); // Used to collect file content line by line.
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n"); // Appends each line with a newline character.
            }
            return data.toString(); // Returns the complete file content as a single string.
        } catch (IOException e) {
            // Handles errors that occur while loading.
            System.out.println("Failed to load the game: " + e.getMessage());
        }
        return ""; // Returns an empty string if loading fails.
    }
}
