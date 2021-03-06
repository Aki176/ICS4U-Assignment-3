import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class BookStore {

    // Method to read from a text file into an array of Book
    public static int inputData (String fileName, Book inBook[]) {
        int i = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while (in.ready() == true) {
                inBook[i] = new Book();
                inBook[i].setBookTitle(in.readLine());
                inBook[i].setBookAuthor(in.readLine());
                inBook[i].setBookPublisher(in.readLine());
                inBook[i].setNumberISBN(Long.parseLong(in.readLine()));
                inBook[i].setBookPrice(Double.parseDouble(in.readLine()));
                inBook[i].setStockQuantity(Integer.parseInt(in.readLine()));
                i++;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        return i;
    }

    // Print out field within an array of Book
    public static void printOutData (Book printBooks[], int numberRecord) {
        for (int i = 0; i < numberRecord; i++) {
            System.out.println(printBooks[i].getBookTitle() + "\t" + printBooks[i].getBookAuthor()
                    + "\t" + printBooks[i].getBookPublisher() + "\t" + printBooks[i].getNumberISBN()
                    + "\t" + printBooks[i].getBookPrice() + "\t" + printBooks[i].getStockQuantity());
        }  // end printOutData
    }

    // Method to write a binary file from an array of Book
    public static void writeNewBinFile(String fileName, Book Book[], int numberRecord) throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        for (int i=0; i < numberRecord; i++) {
            Book[i].writeRecord(randomAccessFile, i);
        }
        randomAccessFile.close();
    } // end writeNewBinFile

    public static int readNewBinFile(String fileName, Book Book[]) throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
        System.out.println("The length is "+ randomAccessFile.length());
        int numberRecord = (int)randomAccessFile.length()/190;
        System.out.println("The # of records is "+ numberRecord);
        for (int i=0; i < numberRecord; i++) {
            Book[i] = new Book();
            Book[i].readRecord(randomAccessFile, i);
            System.out.println (Book[i].getBookTitle() + "\t" + Book[i].getBookAuthor()
                    + "\t" + Book[i].getBookPublisher() + "\t" + Book[i].getNumberISBN()
                    + "\t" + Book[i].getBookPrice() + "\t" + Book[i].getStockQuantity());
        } //end for
        randomAccessFile.close();
        System.out.println("File closed");
        return numberRecord;
    } // end writeNewBinFile

    public static void printReport (Book Book[]) {
        for (int i = 0; i < Book.length; i++) {
            System.out.println();
            System.out.println("Book #" + (i + 1));
            System.out.println("Title: " + Book[i].getBookTitle());
            System.out.println("Author: " + Book[i].getBookAuthor());
            System.out.println("Publisher: " + Book[i].getBookPublisher());
            System.out.println("ISBN: " + Book[i].getNumberISBN());
            System.out.println("Price: " + Book[i].getBookPrice());
            System.out.println("Quantity: " + Book[i].getStockQuantity());
            System.out.println("Total Price: " + (Book[i].getBookPrice()*Book[i].getStockQuantity()));
            System.out.println();
            System.out.println("====================================");
        }
    }


    public static void main (String[] args) throws IOException {
        System.out.println(' ');
        int records = 0;
        String ans;
        String txtFileName, binFileName;
        Scanner s = new Scanner(System.in);
        Book[] books = new Book[12];

        // Welcome message
        System.out.println("====================================");
        System.out.println("          Book Manager v1.0         ");
        System.out.println("====================================");

        while (true) {
            // Menu list
            System.out.println();
            System.out.println("1: Read an ASCII file");
            System.out.println("2: Read a binary file");
            System.out.println("3: Store the array in a binary file");
            System.out.println("4: Sort the array by the ISBN");
            System.out.println("5: Print the data on the books.");
            System.out.println("6: Add a new record and to store it in a binary file");
            System.out.println("7: Delete a record and to store that updated array to a binary file");
            System.out.println("8: Edit a field of a record and to store that updated array in a binary field");
            System.out.println("9: Search by ISBN");
            System.out.println("10: Exit");
            System.out.println();

            // Input choice
            int choice;
            Scanner input = new Scanner(System.in);
            System.out.println("Please input your menu choice and hit ENTER: ");
            choice = Integer.parseInt(input.nextLine());

            // Choice handler
            switch (choice) {
                case 1:
                    System.out.println("Please enter the ASCII file name you want to read from with extension:");
                    txtFileName= s.nextLine();
                    records = inputData(txtFileName, books);
                    printOutData(books, records);
                    break;
                case 2:
                    System.out.println("Please enter the Binary file name you want to read from with extension:");
                    binFileName= s.nextLine();
                    records = readNewBinFile(binFileName, books);
                    printOutData(books, records);
                    break;
                case 3:
                    System.out.println("Please enter the Binary file name you want to write to:");
                    binFileName= s.nextLine();
                    writeNewBinFile(binFileName, books, records);
                    break;
                case 4:

                    break;
                case 5:
                    printReport(books);
                    break;
                case 6:

                case 7:

                case 8:

                case 9:

                case 10:
                    System.out.println("Exiting program...");
                    System.out.println("Program exit!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Your selection is not available! Please rechoose.");
                    break;
            }
        }

//        System.out.println("Do you want to write to a binary file? (yes or no)");
//        ans= s.nextLine();
//        if (ans.charAt(0)=='y') {
//            System.out.println("Please enter a Binary file name to write to");
//            binFileName= s.nextLine();
//            writeNewBinFile(binFileName, books, records);
//        }
//        System.out.println("Do you want to read from a binary file? (yes or no)");
//        ans= s.nextLine();
//        if (ans.charAt(0)=='y') {
//            System.out.println("Please enter a Binary file name to read to");
//            binFileName= s.nextLine();
//            records = readNewBinFile(binFileName, books);
//            printOutData(books, records);
//        }

    }
}
