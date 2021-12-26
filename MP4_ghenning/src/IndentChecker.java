import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class BadIndentationException extends RuntimeException
{
    BadIndentationException(String error)
    {
        super(error);
    }
}
public class IndentChecker {
    Stack<Integer> indentStack = new Stack<Integer>();

    private int findFirstNonBlank(String line)
    {
        // return index of first non-blank character
        // return -1 if the line doesn't contain a nonblank character
        char[] charArr = line.toCharArray();
        int index = -1;
        for(int i=0; i < line.length(); i++){
            if(!Character.isWhitespace(charArr[i])){
                index=i;
                break;
            }
        }
        return index;

    }

    private void processLine(String line, int lineNumber)
    {
        int index = findFirstNonBlank(line);
        // Skip blank lines ... i.e. return immediately
        if(index == -1){
            return;
        }
        // If the stack is empty, then push this index onto the stack and return
        if(indentStack.isEmpty()){
            indentStack.push(index);
        }
            // If this index > than the top of the stack, then push this index onto the stack and return
        if(index > indentStack.peek()){
            indentStack.push(index);
            return;
        }
            // Pop off all Indentation indexes > index
        while(indentStack.peek() > index){
            indentStack.pop();
        }

            // At his point the top of the stack should match the current index. If it
        // doesn't throw a BadIndentationException. In the error message, include the source Line Number
        if(!(indentStack.peek() == index)){
            throw new BadIndentationException("Error at line number "+lineNumber);
        }

    }

    public void checkIndentation(String fileName)
    {
        // Clear the stack
        indentStack.clear();

        // read through the file line by line
        // for each line, call processLine to check indentation
        Scanner input = null;
        try {
            int lineCt=0;
            input = new Scanner (new File(fileName));
            while (input.hasNextLine()) {
                lineCt++;
                String line = input.nextLine();
                System.out.println(lineCt +":"+ line);
                processLine(line, lineCt);
            }

        }
        catch (BadIndentationException error)
        {
            System.out.println(error);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Can't open file: "+fileName);
        }
        finally
        {
            if (input != null)
                input.close();
        }
    }


    public static void main(String[] args) {
        IndentChecker indentChecker = new IndentChecker();
        for (int i=0; i < args.length; i++)
        {
            System.out.println("Processing file: " + args[i]);
            indentChecker.checkIndentation(args[i]);
        }
    }
}