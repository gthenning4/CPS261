import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountDigits {
    public static void main (String[] args) {
    Random random = new Random();
    IntStream stream = random.ints(100,0,10);
    stream.boxed().collect(Collectors.groupingBy(e->e,Collectors.counting())).forEach((k,v)->System.out.println(k+" : "+v));

    }
}