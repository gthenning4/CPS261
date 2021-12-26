import java.io.*;
import java.util.Scanner;

public class ReadingWithExceptions {
    public static void main (String[] args){
        Scanner scan = null;
        BufferedReader reader=null;
        String newFileName=null;
        System.out.println("Name of input file?");
        scan = new Scanner(System.in);
        String inputFile = scan.nextLine();
        try{
            newFileName = process(inputFile);
            try{
                reader = new BufferedReader(new FileReader(newFileName));
                String line = reader.readLine();
                while(line != null){
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
            catch(Exception e){
                System.out.println("Unable to find new file.");
            }
            finally {
                reader.close();
            }
        }
        catch(Exception e){
            System.out.println("An error occurred while processing the file.");
        }
    }
    public static String process (String inputFilename){
        Scanner scannerIO = null;
        FileWriter outFile=null;
        String newFile="newfile.txt";
        int numToRead = 1000;
        int counter = 1;
        int lineCt=0;
        boolean toReadIsInt = true;
        try{
            try{
                scannerIO = new Scanner(new FileInputStream(inputFilename)); }
            catch(FileNotFoundException e){
                System.out.println("Input file not found.");
            }
            String outputFileName = scannerIO.next(); // Use scanner to grab output filename
            newFile = outputFileName;
            outFile = new FileWriter(outputFileName);
            try{
                numToRead = Integer.parseInt(scannerIO.next());
            }catch (Exception e){
                toReadIsInt = false;
                System.out.println("The number of integers to read is not an integer.");
            }
            while(scannerIO.hasNext() && counter<= numToRead){
                String token = scannerIO.next();
                if (token.matches("-?\\d+")){
                outFile.write (token + " ");
                counter++;
                lineCt++;
                    if(lineCt == 10){
                        outFile.write("\n");
                        lineCt=0;
                    }
                }
                else{
                    System.out.println(token + " is not an integer and was not copied to " + outputFileName);
                }
            }
            if (counter < numToRead && toReadIsInt){
                System.out.println("There are not enough integers to copy, but all integers were copied to " + outputFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                scannerIO.close();
                System.out.println(newFile +" was created.");
            try {
                outFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    return newFile;
}}
