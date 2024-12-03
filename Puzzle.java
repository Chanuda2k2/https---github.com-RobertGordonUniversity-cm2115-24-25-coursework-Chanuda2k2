public class Puzzle {
    private final String riddle;
    private final String answer;

    public Puzzle(String riddle, String answer) {
        this.riddle = riddle;
        this.answer = answer;
    }

    public String getRiddle() {
        return riddle;
    }

    public boolean solve(String response) {
        return response.equalsIgnoreCase(answer);
    }
}
