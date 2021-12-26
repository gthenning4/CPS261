import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sort2DArray {
    public static void main(String[] args){
        int[][] numberArray = {{34,89},{56,3},{27,61},{45,8},{45,89}};
        Stream.of(numberArray).flatMapToInt(e-> IntStream.of(e)).distinct().sorted().forEach(value -> System.out.print(value+ " "));
    }
}
