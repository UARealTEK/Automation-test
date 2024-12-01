import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public static int oddOne(int[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                index = i;
            }
        }
        return index;
    }

}
