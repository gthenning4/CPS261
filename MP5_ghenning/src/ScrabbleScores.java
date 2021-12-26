import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Word{
    private String label;
    private int score;

    public Word(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return (this.getLabel()+": "+this.getScore());
    }
}
public class ScrabbleScores {
    public static int scoreWord(String word,Map<Character,Integer> hashMap){
        int score = 0;
        char[] charArray = word.toCharArray();
        for (char letter: charArray) {
            score += hashMap.get(Character.toLowerCase(letter));
        }
        return score;
    }
    public static void main(String[] args){
        Map<Character, Integer> letterValues = new HashMap();
        letterValues.put('a', 1);
        letterValues.put('b', 3);
        letterValues.put('c', 3);
        letterValues.put('d', 2);
        letterValues.put('e', 1);
        letterValues.put('f', 4);
        letterValues.put('g', 2);
        letterValues.put('h', 4);
        letterValues.put('i', 1);
        letterValues.put('j', 8);
        letterValues.put('k', 5);
        letterValues.put('l', 1);
        letterValues.put('m', 3);
        letterValues.put('n', 1);
        letterValues.put('o', 1);
        letterValues.put('p', 3);
        letterValues.put('q', 10);
        letterValues.put('r', 1);
        letterValues.put('s', 1);
        letterValues.put('t', 1);
        letterValues.put('u', 1);
        letterValues.put('v', 8);
        letterValues.put('w', 4);
        letterValues.put('x', 8);
        letterValues.put('y', 4);
        letterValues.put('z', 10);

        Word[] wordArr = {
                new Word("Java"),
                new Word("program"),
                new Word("list"),
                new Word("string"),
                new Word("unix"),
                new Word("hours"),
                new Word("syntax"),
                new Word("error")
        };
        for (Word word: wordArr) {
            word.setScore(scoreWord(word.getLabel(),letterValues));
        }

        System.out.println("Top 3 words are:");
        List<Word> sortedList = Stream.of(wordArr).sorted(Comparator.comparing(Word::getScore).reversed()).limit(3).collect(Collectors.toList());
        sortedList.forEach(w -> System.out.println(w));

        double mean = Stream.of(wordArr).collect(Collectors.averagingInt(e -> e.getScore()));
        System.out.println("\nAverage score is: " + mean);

        System.out.println("\nWords below average: ");
        List<Word> belowAvg = Stream.of(wordArr).filter(w -> w.getScore() < mean).collect(Collectors.toList());
        belowAvg.forEach(w-> System.out.println(w.getLabel()));

        System.out.println("\nWords above average: ");
        List<Word> aboveAvg=Stream.of(wordArr).filter(w -> w.getScore() > mean).collect(Collectors.toList());
        aboveAvg.forEach(w-> System.out.println(w.getLabel()));




    }// end of main
}
