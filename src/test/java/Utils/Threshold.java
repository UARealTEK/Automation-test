package Utils;

public class Threshold {

    private final int threshold = 2;

    public boolean isValueWithinThreshold(int actual, int expected) {

        int result = Math.abs(actual) - Math.abs(expected);

        return Math.abs(result) < threshold;
    }
}
