/*
 * Author: Duc Anh Vu - Aki176
 * Last Modified: March 8, 2021
 * Description: Book database management
 */


import java.io.*;
import java.util.Scanner;

public class BookStore {

    /**
     * Method Name: inputData
     * Author: Kyle McKay
     * Creation Date: ?
     * Modified Date: ?
     * Description: Input data record from text file to array of object books
     * Parameters: File name, Book array
     * Return Value: Number index of the array
     * Data Type: Integer
     * Dependencies: java.io.BufferedReader, java.io.InputStreamReader, java.io.FileInputStream
     * Throws/Exceptions: IOException
     */
    public static int inputData(String fileName, Book[] Book) {
        int i = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while (in.ready()) {
                Book[i] = new Book();
                Book[i].setBookTitle(in.readLine());
                Book[i].setBookAuthor(in.readLine());
                Book[i].setBookPublisher(in.readLine());
                Book[i].setNumberISBN(Long.parseLong(in.readLine()));
                Book[i].setBookPrice(Double.parseDouble(in.readLine()));
                Book[i].setStockQuantity(Integer.parseInt(in.readLine()));
                i++;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        return i;
    }

    /**
     * Method Name: printOutData
     * Author: Kyle McKay
     * Creation Date: ?
     * Modified Date: ?
     * Description: Print out record from the array of object books
     * Parameters: Book array, Number of record
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static void printOutData(Book[] Book, int numberRecord) {
        for (int i = 0; i < numberRecord; i++) {
            System.out.println(Book[i].getBookTitle() + "\t" + Book[i].getBookAuthor()
                    + "\t" + Book[i].getBookPublisher() + "\t" + Book[i].getNumberISBN()
                    + "\t" + Book[i].getBookPrice() + "\t" + Book[i].getStockQuantity());
        }  // end printOutData
    }

    /**
     * Method Name: writeNewBinFile
     * Author: Kyle McKay
     * Creation Date: ?
     * Modified Date: ?
     * Description: Write the record from array to binary file
     * Parameters: File name, Book array, Number of record
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: java.io.RandomAccessFile
     * Throws/Exceptions: IOException
     */
    public static void writeNewBinFile(String fileName, Book[] Book, int numberRecord) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        for (int i = 0; i < numberRecord; i++) {
            Book[i].writeRecord(randomAccessFile, i);
        }
        randomAccessFile.close();
    } // end writeNewBinFile

    /**
     * Method Name: readNewBinFile
     * Author: Kyle McKay
     * Creation Date: ?
     * Modified Date: ?
     * Description: Read record from binary file to array
     * Parameters: File name, Book array
     * Return Value: Number of record
     * Data Type: Integer
     * Dependencies: java.io.RandomAccessFile
     * Throws/Exceptions: IOException
     */
    public static int readNewBinFile(String fileName, Book[] Book) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        System.out.println("The length is " + randomAccessFile.length());
        int numberRecord = (int) randomAccessFile.length() / 190;
        System.out.println("The # of records is " + numberRecord);
        for (int i = 0; i < numberRecord; i++) {
            Book[i] = new Book();
            Book[i].readRecord(randomAccessFile, i);
            System.out.println(Book[i].getBookTitle() + "\t" + Book[i].getBookAuthor()
                    + "\t" + Book[i].getBookPublisher() + "\t" + Book[i].getNumberISBN()
                    + "\t" + Book[i].getBookPrice() + "\t" + Book[i].getStockQuantity());
        } //end for
        randomAccessFile.close();
        System.out.println("File closed");
        return numberRecord;
    } // end writeNewBinFile

    /**
     * Method Name: printReport
     * Author: Duc Anh Vu
     * Creation Date: March 6, 2021
     * Modified Date: March 6, 2021
     * Description: Print out report from array of book
     * Parameters: Book array
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static void printReport(Book[] Book) {
        for (int i = 0; i < Book.length; i++) {
            System.out.println();
            System.out.println("Book #" + (i + 1));
            System.out.println("Title: " + Book[i].getBookTitle());
            System.out.println("Author: " + Book[i].getBookAuthor());
            System.out.println("Publisher: " + Book[i].getBookPublisher());
            System.out.println("ISBN: " + Book[i].getNumberISBN());
            System.out.println("Price: " + Book[i].getBookPrice());
            System.out.println("Quantity: " + Book[i].getStockQuantity());
            System.out.println("Total Price: " + (Book[i].getBookPrice() * Book[i].getStockQuantity()));
            System.out.println();
            System.out.println("====================================");
        }
    } // End printReport

    /**
     * Method Name: sortByISBN
     * Author: Duc Anh Vu
     * Creation Date: March 6, 2021
     * Modified Date: March 6, 2021
     * Description: Sort book array by ISBN
     * Parameters: Book array
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static void sortByISBN(Book[] Book) {
        int n = Book.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                if (Book[j].getNumberISBN() < Book[minIndex].getNumberISBN())
                    minIndex = j;

            // Swap the found minimum element with the first element
            Book temp = Book[minIndex];
            Book[minIndex] = Book[i];
            Book[i] = temp;
        }
    } // End sortByISBN

    /**
     * Method Name: search
     * Author: Duc Anh Vu
     * Creation Date: March 6, 2021
     * Modified Date: March 6, 2021
     * Description: Search book by ISBN
     * Parameters: Book array, ISBN number
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static void search(Book[] Book, long numberISBN) {
        boolean found = false;
        for (int i = 0; i < Book.length; i++) {
            if (Book[i].getNumberISBN() == (numberISBN)) {
                System.out.println();
                System.out.println("Book found! Here are the details:");
                System.out.println("Title: " + Book[i].getBookTitle());
                System.out.println("Author: " + Book[i].getBookAuthor());
                System.out.println("Publisher: " + Book[i].getBookPublisher());
                System.out.println("ISBN: " + Book[i].getNumberISBN());
                System.out.println("Price: " + Book[i].getBookPrice());
                System.out.println("Quantity: " + Book[i].getStockQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }

    /**
     * Method Name: search
     * Author: Duc Anh Vu
     * Creation Date: March 6, 2021
     * Modified Date: March 6, 2021
     * Description: Search book array by author
     * Parameters: Book array, Author
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     */
    public static void search(Book[] Book, String bookAuthor) {
        boolean found = false;
        for (int i = 0; i < Book.length; i++) {
            if (Book[i].getBookAuthor().equals(bookAuthor)) {
                System.out.println();
                System.out.println("Book found! Here are the details:");
                System.out.println("Title: " + Book[i].getBookTitle());
                System.out.println("Author: " + Book[i].getBookAuthor());
                System.out.println("Publisher: " + Book[i].getBookPublisher());
                System.out.println("ISBN: " + Book[i].getNumberISBN());
                System.out.println("Price: " + Book[i].getBookPrice());
                System.out.println("Quantity: " + Book[i].getStockQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }

    /**
     * Method Name: addBook
     * Author: Duc Anh Vu
     * Creation Date: March 6, 2021
     * Modified Date: March 6, 2021
     * Description: Add a book object to array and add to RAF
     * Parameters: File name, Book array, Book title, Book author, Book publisher, ISBN, Book price, Stock quantity
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: java.io.RandomAccessFile
     * Throws/Exceptions: IOException
     */
    public static void addBook(String fileName, Book[] Book,
                               String bookTitle, String bookAuthor, String bookPublisher, long numberISBN, double bookPrice, int stockQuantity) throws IOException {
        // Initialize RAF
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");

        // Update array Book
        Book[Book.length - 1] = new Book(); // Assign to last element
        Book[Book.length - 1].setBookTitle(bookTitle);
        Book[Book.length - 1].setBookAuthor(bookAuthor);
        Book[Book.length - 1].setBookPublisher(bookPublisher);
        Book[Book.length - 1].setNumberISBN(numberISBN);
        Book[Book.length - 1].setBookPrice(bookPrice);
        Book[Book.length - 1].setStockQuantity(stockQuantity);

        // Update RAF
        randomAccessFile.seek(randomAccessFile.length()); // Move pointer to the end of the file
        int bookTitleLength = bookTitle.length();
        int padLength = 0; // Calculate the length that's need to be
        if (bookTitleLength > 56) {
            bookTitleLength = 56;
        } else {
            padLength = 56 - bookTitleLength;
        }
        for (int i = 0; i < bookTitle.length(); i++) {
            randomAccessFile.writeChar(bookTitle.charAt(i));
        }
        if (padLength > 0) {
            for (int i = 0; i < padLength; i++) {
                randomAccessFile.writeChar(' ');
            }
        }

        int bookAuthorLength = bookAuthor.length();
        padLength = 0;
        if (bookAuthorLength > 15) {
            bookAuthorLength = 15;
        } else {
            padLength = 15 - bookAuthorLength;
        }
        for (int i = 0; i < bookAuthor.length(); i++) {
            randomAccessFile.writeChar(bookAuthor.charAt(i));
        }
        if (padLength > 0) {
            for (int i = 0; i < padLength; i++) {
                randomAccessFile.writeChar(' ');
            }
        }

        int bookPublisherLength = bookPublisher.length();
        padLength = 0;
        if (bookPublisherLength > 14) {
            bookPublisherLength = 14;
        } else {
            padLength = 14 - bookPublisherLength;
        }
        for (int i = 0; i < bookPublisher.length(); i++) {
            randomAccessFile.writeChar(bookPublisher.charAt(i));
        }
        if (padLength > 0) {
            for (int i = 0; i < padLength; i++) {
                randomAccessFile.writeChar(' ');
            }
        }

        randomAccessFile.writeLong(numberISBN);
        randomAccessFile.writeDouble(bookPrice);
        randomAccessFile.writeInt(stockQuantity);

        randomAccessFile.close();
    } // End addBook

    /**
     * Method Name: editBook
     * Author: Duc Anh Vu
     * Creation Date: March 6, 2021
     * Modified Date: March 6, 2021
     * Description: Edit a book object to array and in RAF
     * Parameters: File name, Book array, ISBN search, Book title, Book author, Book publisher, ISBN, Book price, Stock quantity
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: java.io.RandomAccessFile
     * Throws/Exceptions: IOException
     */
    public static void editBook(String fileName, Book[] Book, long numberISBNSearch, String bookTitle, String bookAuthor, String bookPublisher,
                                long numberISBN, double bookPrice, int stockQuantity) throws IOException {
        // Initialize RAF
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");

        // Edit array book obj
        boolean found = false;
        int index = 0;
        for (int i = 0; i < Book.length; i++) {
            if (Book[i].getNumberISBN() == (numberISBNSearch)) { // Search ISBN
                Book[i].setBookTitle(bookTitle);
                Book[i].setBookAuthor(bookAuthor);
                Book[i].setBookPublisher(bookPublisher);
                Book[i].setNumberISBN(numberISBN);
                Book[i].setBookPrice(bookPrice);
                Book[i].setStockQuantity(stockQuantity);
                found = true;
                index = i;
            }
        }
        if (!found) {
            System.out.println("Book not found with that ISBN!");
        }

        // Update RAF
        randomAccessFile.seek(index * 190); // Point to the beginning of the record

        int bookTitleLength = bookTitle.length();
        int padLength = 0; // Calculate the length that's need to be
        if (bookTitleLength > 56) {
            bookTitleLength = 56;
        } else {
            padLength = 56 - bookTitleLength;
        }
        for (int i = 0; i < bookTitle.length(); i++) {
            randomAccessFile.writeChar(bookTitle.charAt(i));
        }
        if (padLength > 0) {
            for (int i = 0; i < padLength; i++) {
                randomAccessFile.writeChar(' ');
            }
        }

        int bookAuthorLength = bookAuthor.length();
        padLength = 0;
        if (bookAuthorLength > 15) {
            bookAuthorLength = 15;
        } else {
            padLength = 15 - bookAuthorLength;
        }
        for (int i = 0; i < bookAuthor.length(); i++) {
            randomAccessFile.writeChar(bookAuthor.charAt(i));
        }
        if (padLength > 0) {
            for (int i = 0; i < padLength; i++) {
                randomAccessFile.writeChar(' ');
            }
        }

        int bookPublisherLength = bookPublisher.length();
        padLength = 0;
        if (bookPublisherLength > 14) {
            bookPublisherLength = 14;
        } else {
            padLength = 14 - bookPublisherLength;
        }
        for (int i = 0; i < bookPublisher.length(); i++) {
            randomAccessFile.writeChar(bookPublisher.charAt(i));
        }
        if (padLength > 0) {
            for (int i = 0; i < padLength; i++) {
                randomAccessFile.writeChar(' ');
            }
        }

        randomAccessFile.writeLong(numberISBN);
        randomAccessFile.writeDouble(bookPrice);
        randomAccessFile.writeInt(stockQuantity);

        randomAccessFile.close();
    } // End editBook

    /**
     * Method Name: deleteBook
     * Author: Duc Anh Vu
     * Creation Date: March 6, 2021
     * Modified Date: March 6, 2021
     * Description: Delete a book object to array and in RAF
     * Parameters: File name, Book array, ISBN search
     * Return Value: N/A
     * Data Type: Void
     * Dependencies: java.io.RandomAccessFile
     * Throws/Exceptions: IOException
     */
    public static void deleteBook(String fileName, Book[] Book, long numberISBNSearch) throws IOException {
        // Initialize RAF
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");

        // Edit array book obj
        boolean found = false;
        int index = 0;
        int i;
        for (i = 0; i < Book.length; i++) {
            if (Book[i].getNumberISBN() == (numberISBNSearch)) { // Search ISBN
                Book[i].setBookTitle("");
                Book[i].setBookAuthor("");
                Book[i].setBookPublisher("");
                Book[i].setNumberISBN(0);
                Book[i].setBookPrice(0.00);
                Book[i].setStockQuantity(0);
                found = true;
                index = i;
            }
        }
        if (!found) {
            System.out.println("Book not found with that ISBN!");
        }

        // Update RAF
        randomAccessFile.seek(index * 190); // Move pointer to the beginning of record
        long currentPosition = randomAccessFile.getFilePointer();
        System.out.println(currentPosition);
        for (int z = 0; z < 85; z++) { // Write Title, author and publisher as space
            randomAccessFile.writeChar(' ');
        }
        randomAccessFile.writeLong(0);
        randomAccessFile.writeDouble(0.00);
        randomAccessFile.writeInt(0);

        randomAccessFile.close();
    }


    public static void main(String[] args) throws IOException {
        System.out.println(' ');
        int records = 0;
        String txtFileName, binFileName;
        String bookTitle, bookAuthor, bookPublisher;
        long numberISBNSearch, numberISBN;
        double bookPrice;
        int stockQuantity;
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
            String ans;
            Scanner input = new Scanner(System.in);
            System.out.println("Please input your menu choice and hit ENTER: ");
            choice = Integer.parseInt(input.nextLine());

            // Choice handler
            switch (choice) {
                case 1:
                    System.out.println("Please enter the ASCII file name you want to read from with extension:");
                    txtFileName = input.nextLine();
                    records = inputData(txtFileName, books);
                    printOutData(books, records);
                    break;
                case 2:
                    System.out.println("Please enter the Binary file name you want to read from with extension:");
                    binFileName = input.nextLine();
                    records = readNewBinFile(binFileName, books);
                    break;
                case 3:
                    System.out.println("Please enter the Binary file name you want to write to:");
                    binFileName = input.nextLine();
                    writeNewBinFile(binFileName, books, records);
                    break;
                case 4:
                    sortByISBN(books);
                    printOutData(books, records);
                    break;
                case 5:
                    printReport(books);
                    break;
                case 6:
                    System.out.println("What is the RAF file you are trying to add to?");
                    binFileName = input.nextLine();
                    System.out.println("Please input the book title you want to add:");
                    bookTitle = input.nextLine();
                    System.out.println("Please input the book author you want to add:");
                    bookAuthor = input.nextLine();
                    System.out.println("Please input the book publisher you want to add:");
                    bookPublisher = input.nextLine();
                    System.out.println("Please input the ISBN number you want to add:");
                    numberISBN = Long.parseLong(input.nextLine());
                    System.out.println("Please input the book price you want to add:");
                    bookPrice = Double.parseDouble(input.nextLine());
                    System.out.println("Please input the stock quantity you want to add:");
                    stockQuantity = Integer.parseInt(input.nextLine());
                    addBook(binFileName, books, bookTitle, bookAuthor, bookPublisher, numberISBN, bookPrice, stockQuantity);
                    break;
                case 7:
                    System.out.println("What is the RAF file you are trying to delete a record?");
                    binFileName = input.nextLine();
                    System.out.println("Please input the ISBN of the book you want to delete:");
                    numberISBNSearch = Long.parseLong(input.nextLine());
                    deleteBook(binFileName, books, numberISBNSearch);
                    break;
                case 8:
                    System.out.println("What is the RAF file you are trying to edit?");
                    binFileName = input.nextLine();
                    System.out.println("Please input the ISBN of the book you want to edit:");
                    numberISBNSearch = Long.parseLong(input.nextLine());
                    System.out.println("Please input the book title you want to edit:");
                    bookTitle = input.nextLine();
                    System.out.println("Please input the book author you want to edit:");
                    bookAuthor = input.nextLine();
                    System.out.println("Please input the book publisher you want to edit:");
                    bookPublisher = input.nextLine();
                    System.out.println("Please input the ISBN number you want to edit:");
                    numberISBN = Long.parseLong(input.nextLine());
                    System.out.println("Please input the book price you want to edit:");
                    bookPrice = Double.parseDouble(input.nextLine());
                    System.out.println("Please input the stock quantity you want to edit:");
                    stockQuantity = Integer.parseInt(input.nextLine());
                    editBook(binFileName, books, numberISBNSearch, bookTitle, bookAuthor, bookPublisher, numberISBN, bookPrice, stockQuantity);
                    break;
                case 9:
                    System.out.println("Search by ISBN (i) or Search by Author (a)?");
                    ans = input.nextLine();
                    if (ans.charAt(0) == 'i') {
                        System.out.println();
                        numberISBNSearch = Long.parseLong(input.nextLine());
                        search(books, numberISBNSearch);
                    } else if (ans.charAt(0) == 'a') {
                        System.out.println("Please type the author name in:");
                        bookAuthor = input.nextLine();
                        search(books, bookAuthor);
                    } else {
                        System.out.println("No chosen method!");
                    }
                    break;
                case 10:
                    System.out.println("Exiting program...");
                    System.out.println("Program exit!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Your selection is not available! Please choose again.");
                    break;
            }
        }
    }
}
