public class Utils {
    public static boolean validateAnswer(String input, String correctAnswer) {
        return input.equalsIgnoreCase(correctAnswer);
    }

    public static int calculateDamage(int baseDamage, int powerBoost) {
        return baseDamage + powerBoost;
    }
}
