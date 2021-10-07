/*
    A brief summary:a program (named as proj2.java) that reads an ASCII file
                   that contains a set of numbers of type int and outputs all the numbers to another file,
                   but without any duplicated numbers.
                   Assume that in the input file, the numbers, one per line, are listed from smallest to largest.
                   After the program is run, the new output file will contain all the numbers in the original file,
                   but no number will appear more than once in the file.
                   The numbers in the output file should also be listed from smallest to largest.
                    In addition, the program will compute the average of those retained distinct numbers
                    and then save it as the last line of the output file.

    Authors: 1. Paul Sining Lu; sininglu@sandiego.edu
             2. Ashley; axu@sandiego.edu

    Last Date Modified: 09/30/2021
 */

import java.io.*;
import java.util.Scanner;

public class proj2 {
    public static void main(String[] args) {

        outerloop:
        while (true) {
            Scanner sc = new Scanner(System.in);

            // ask user to input file name
            System.out.println("\n");
            System.out.println("Please enter the name of an ASCII file that contains a sorted list of integer numbers, one per line:");
            String fileName = sc.nextLine();

            // ask for output file name
            System.out.println("\n");
            System.out.println("Please enter the name of the output file that saves the result from the program:");
            String newFileName = sc.nextLine();

            // create a new file
            PrintWriter out;
            try {
                // create a new file
                out = new PrintWriter(new FileOutputStream(newFileName));
                // Write distinct values into new file.
                try {
                    FileReader fr = new FileReader(fileName);
                    BufferedReader br = new BufferedReader(fr);

                    String numStr = br.readLine(); // read number into string
                    String lastNum = null; // keep track of the last num
                    int sum = 0, count=0; // keep track of sum and count, for calculating the avg.

                    while (numStr != null){

                        // check if last num is equal to current num, if so skip to next line
                        // shift to next line
                        if (!numStr.equals(lastNum)) {
                            // write current num into new file
                            out.println(numStr);

                            // mutate the sum & count
                            count++;
                            try {
                                sum += Integer.parseInt(numStr);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format in original file, please make sure each line contains only an integer.");
                                continue outerloop;
                            }

                            lastNum = numStr; // lastNum become current number
                        }
                        numStr = br.readLine();
                    }
                    try{
                        double avg = sum/count;
                        out.println(avg); // add the final avg line
                    }catch (ArithmeticException e){
                        System.out.println("Please at least have one number in your file. ");
                    }
                    br.close();
                    fr.close();
                    out.close();

                } catch (FileNotFoundException e) {
                    System.out.println("File not found, please enter an existing file.");
                    continue;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

            }catch (IOException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // Ask user if he/she wants to play again.
            String decision;
            // making a nested while loop in case that user doesn't type 'y' or 'n', we continue ask the user.
            while(true){
                // ask the user if he/she wants to play again
                System.out.println("\n");
                System.out.println("Do you want to run the program again (y for yes and n for no)?:");
                decision = sc.next();

                if (decision.equals("n")) {
                    break outerloop;
                } else if (decision.equals("y")) {
                    break;
                }
                else {
                    System.out.println("Invalid Command, please type: y for yes and n for no");
                }
            }

        }
    }
}