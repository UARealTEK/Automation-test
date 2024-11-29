import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public static int wordsToMarks (String text) {
        int sum = 0;

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            sum += convertToAlphabetPosition(letter);
        }

        return sum;
    }

    public static int convertToAlphabetPosition(char letter) {
        return (int) Character.toLowerCase(letter) - 96;
    }

}
