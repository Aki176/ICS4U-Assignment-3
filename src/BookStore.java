import java.io.*;
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
    public static void writeNewBinFile(String fileName, Book outBook[], int numberRecord) throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        for (int i=0; i < numberRecord; i++) {
            outBook[i].writeRecord(randomAccessFile, i);
        }
        randomAccessFile.close();
    } // end writeNewBinFile

    public static int readNewBinFile(String fileName, Book inBook[]) throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
        System.out.println("The length is "+ randomAccessFile.length());
        int numberRecord = (int)randomAccessFile.length()/190;
        System.out.println("The # of records is "+ numberRecord);
        for (int i=0; i < numberRecord; i++) {
            inBook[i] = new Book();
            inBook[i].readRecord(randomAccessFile, i);
            System.out.println (inBook[i].getBookTitle() + "\t" + inBook[i].getBookAuthor()
                    + "\t" + inBook[i].getBookPublisher() + "\t" + inBook[i].getNumberISBN()
                    + "\t" + inBook[i].getBookPrice() + "\t" + inBook[i].getStockQuantity());
        } //end for
        randomAccessFile.close();
        System.out.println("File closed");
        return numberRecord;
    } // end writeNewBinFile

    public static void main (String[] args) throws IOException {
        System.out.println(' ');
        int records = 0;
        String ans;
        String txtFileName, binFileName;
        Scanner s = new Scanner(System.in);
        Book[] books = new Book[12];
        System.out.println("Do you want to read in a text file? (yes or no)");
        ans= s.nextLine();
        if (ans.charAt(0)=='y') {
            System.out.println("Please enter a file name");
            txtFileName= s.nextLine();
            records = inputData(txtFileName, books);
            printOutData(books, records);
        }
        System.out.println("Do you want to write to a binary file? (yes or no)");
        ans= s.nextLine();
        if (ans.charAt(0)=='y') {
            System.out.println("Please enter a Binary file name to write to");
            binFileName= s.nextLine();
            writeNewBinFile(binFileName, books, records);
        }
        System.out.println("Do you want to read from a binary file? (yes or no)");
        ans= s.nextLine();
        if (ans.charAt(0)=='y') {
            System.out.println("Please enter a Binary file name to read to");
            binFileName= s.nextLine();
            records = readNewBinFile(binFileName, books);
            printOutData(books, records);
        }
        s.close(); //Close the Text File
    }
}
