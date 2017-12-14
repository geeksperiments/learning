import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Yahtzee {
    private final int[] dices;

    public Yahtzee(int... dices) {

        this.dices = dices;
    }

    public int ones() {
        return countEyesFor(1);
    }

    public int twos() {
        return countEyesFor(2);
    }

    public int threes() {
        return countEyesFor(3);
    }

    public int fours() {
        return countEyesFor(4);
    }

    public int fives() {
        return countEyesFor(5);
    }

    public int pair() {
        return pairs()
                .max(Comparator.comparing(Map.Entry::getKey))
                .map(Yahtzee::pairValue)
                .orElse(0L)
                .intValue();
    }

    public int twoPairs() {
        long[] pairValues = pairs()
                .mapToLong(Yahtzee::pairValue)
                .toArray();
        return pairValues.length != 2
                ? 0
                : (int) Arrays.stream(pairValues).sum();
    }

    private static boolean isPair(Map.Entry<Integer, Long> e) {
        return e.getValue() == 2;
    }

    private static Long pairValue(Map.Entry<Integer, Long> e) {
        return e.getKey() * e.getValue();
    }

    private Stream<Map.Entry<Integer, Long>> pairs() {
        return distinctEyes()
                .entrySet()
                .stream()
                .filter(Yahtzee::isPair);
    }

    private Map<Integer, Long> distinctEyes() {
        return Arrays
                .stream(dices)
                .boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()));
    }

    private int countEyesFor(int whichEye) {
        return (int) Arrays
                .stream(dices).filter(d -> d == whichEye)
                .count() * whichEye;
    }
}
