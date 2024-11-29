import java.util.ArrayList;
import java.util.List;

public class Solutions {

    public static String wordPattern(final String word){
        String wordLower = word.toLowerCase();
        List<Integer> intValues = new ArrayList<>();

        for (int i = 0; i < wordLower.length(); i++) {
            String letter = String.valueOf(wordLower.charAt(i));
            intValues.add(wordLower.indexOf(letter));
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < intValues.size(); i++) {
            out.append(intValues.get(i));
            out.append(".");
        }

        return out.substring(0,out.length() -1);
    }

}
