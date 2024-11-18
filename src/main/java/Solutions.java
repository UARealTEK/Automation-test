import java.math.BigDecimal;

public class Solutions {
    public static int singleDigit(int n) {
        if (String.valueOf(n).length() == 1) {
            return n;
        }

        while (String.valueOf(n).length() > 1) {
            n = sumInt(intToBinaryNumeric(n));
        }

        return n;
    }

    public static int sumInt(BigDecimal arg) {
        String strVal = String.valueOf(arg);
        int sum = 0;

        for (int i = 0; i < strVal.length(); i++) {
            sum += Integer.parseInt(String.valueOf(strVal.charAt(i)));
        }

        return sum;
    }

    public static BigDecimal intToBinaryNumeric(int value) {
        String binaryString = Integer.toBinaryString(value); // Converts the number to a binary string (e.g., "101")
        return new BigDecimal(binaryString);// Converts the string "101" back to the number 101 (decimal)
    }

}
