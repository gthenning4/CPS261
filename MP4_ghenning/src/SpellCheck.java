import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SpellCheck {

    // We could hava also used TreeSet for the dictionary
    private HashSet<String> dictionary = new HashSet<String>();
    private TreeSet<String> miss_spelled_words = new TreeSet<String>();

    public SpellCheck() throws FileNotFoundException {
        // Add all of the words from "dictionary.txt" to the dictionary HashSet
        try(BufferedReader br = new BufferedReader(new FileReader("../dictionary.txt"))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                dictionary.add(line);
            }
            // line is not visible here.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkSpelling(String fileName) throws FileNotFoundException
    {
        System.out.println("======== Spell checking " + fileName + " =========");
        miss_spelled_words.clear(); // Clear miss_spelled_words
        // Read in each line from "fileName"
        try(BufferedReader br = new BufferedReader(new FileReader("../"+fileName))) {
            int lineCt=0;
            for(String line; (line = br.readLine()) != null; ) {
                lineCt++;
                // For each line, break the line into words using the following StringTokenizer
                StringTokenizer st = new StringTokenizer(line, " \t,.;:- %'\"");
                boolean badLine = false;
                ArrayList badWords=new ArrayList();
                while(st.hasMoreTokens()){
                     String word = st.nextToken().toLowerCase(); // lower case each word obtained from the StringTokenizer
                     char firstChar=word.charAt(0);
                     if(Character.isDigit(firstChar)){ // skip word if the first character is not a letter
                         continue;
                     }
                     else if(dictionary.contains(word) || miss_spelled_words.contains(word)){
                         // Skip over word if it can be found in either dictionary, or miss_spelled_words
                         continue;
                     }
                     else if(word.endsWith("s") && (dictionary.contains(word.substring(0,word.length()-1)) || miss_spelled_words.contains(word.substring(0,word.length()-1)))){
                         // If word ends with 's', then try the singular version of the word in the dictionary and miss_spelled_words ... skip if found
                         continue;
                     }
                     else{
                         badLine = true;
                         if(!miss_spelled_words.contains(word)){
                             miss_spelled_words.add(word);
                         }
                         if(!badWords.contains(word)){
                             badWords.add(word);
                         }
                     }
                }
                if (badLine){
                    int wordCt = badWords.size(); //Ct num of misspelled words so we can print correct num of "add to dict." prompts
                    System.out.println(lineCt +" : "+line);
                    for(int i=0; i <wordCt; i++){
                        String newWord = (String) badWords.get(i);
                        System.out.println( newWord+" is not in the dictionary. Add it? (y/n) ");
                        try{
                            Scanner keyboard = new Scanner(System.in);
                            String reply = keyboard.next();
                            if(reply.equals("y")){
                                dictionary.add(newWord);
                                System.out.println(newWord +" was added to the dictionary.");
                            }
                            else if(reply.equals("n")){
                                continue;
                            }
                            else{
                                System.out.println("Bad input");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print line containing miss-spelled word(make sure you
        //only print it once if multiple miss-spelled words are found on
        //this line)

        // Ask the user if he wants the word added to the
        //dictionary or the miss-spelled words and add word as specified
        //by the user


    }

    public void dump_miss_spelled_words()
    {
        System.out.println("List of misspelled words: ");
        Iterator it = miss_spelled_words.iterator();
        while(it.hasNext()){ // Print out the miss-spelled words
            System.out.println(it.next());
        }
    }


    public static void main(String[] args) {
        try {
            SpellCheck spellCheck = new SpellCheck();

            for (int i=0; i < args.length; i++)
            {
                spellCheck.checkSpelling(args[i]);
                spellCheck.dump_miss_spelled_words();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }

    }
}
