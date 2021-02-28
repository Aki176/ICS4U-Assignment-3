import java.io.IOException;
import java.io.RandomAccessFile;

public class Book {

    // Attributes
    private String bookTitle; // 56 chars 112 bytes
    private String bookAuthor; // 20 chars 40 bytes
    private String bookPublisher; // 14 chars 28 bytes
    private long numberISBN; // 8 bytes
    private double bookPrice; // 8 bytes
    private int stockQuantity; // 4 bytes
    private final long recordLength = 200;
    // End attributes

    // Constructor
    public Book(String bookTitle, String bookAuthor, String bookPublisher, long numberISBN, double bookPrice, int stockQuantity) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.numberISBN = numberISBN;
        this.bookPrice = bookPrice;
        this.stockQuantity = stockQuantity;
    } // End constructor

    // Blank constructor
    public Book() {
        bookTitle = "";
        bookAuthor = "";
        bookPublisher = "";
        numberISBN = 0;
        bookPrice = 0.00;
        stockQuantity = 0;
    } // End blank constructor

    // Getter and setter
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public long getNumberISBN() {
        return numberISBN;
    }

    public void setNumberISBN(long numberISBN) {
        this.numberISBN = numberISBN;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    // End getter and setter

    // Method readRecord to read record from a random access file
    public void readRecord (RandomAccessFile randomAccessFile, int recordNumber) throws IOException {

        // Read String type
        randomAccessFile.seek(recordNumber * recordLength);
        String temp = "";
        for (int i = 0; i < 12; i++) {
            temp = temp + randomAccessFile.readChar();
        }
        bookTitle = temp.trim();
        temp = "";
        for (int i = 0; i < 12; i++) {
            temp = temp + randomAccessFile.readChar();
        }
        bookAuthor = temp.trim();
        temp = "";
        for (int i = 0; i < 12; i++) {
            temp = temp + randomAccessFile.readChar();
        }
        bookPublisher = temp.trim();
        temp = "";

        // Read number type
        numberISBN = randomAccessFile.readLong();
        bookPrice = randomAccessFile.readDouble();
        stockQuantity = randomAccessFile.readInt();
    }

    // End readRecord method

    // Method writeRecord to write a record into random access file
    public void writeRecord (RandomAccessFile randomAccessFile, int recordNumber) throws IOException {
        randomAccessFile.seek(recordNumber * recordLength); // Move pointer to position on file

        // Write bookTitle of the record
        int bookTitleLength = bookTitle.length(); // Determine if there is over 56 characters (112 bytes)
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
                randomAccessFile.readChar();
            }
        }

        // Write bookAuthor of the record
        int bookAuthorLength = bookAuthor.length();
        padLength = 0;
        if (bookAuthorLength > 20) {
            bookAuthorLength = 20;
        } else {
            padLength = 20 - bookAuthorLength;
        }
        for (int i = 0; i < bookAuthor.length(); i++) {
            randomAccessFile.writeChar(bookAuthor.charAt(i));
        }
        if (padLength > 0) {
            for (int i = 0; i < padLength; i++) {
                randomAccessFile.readChar();
            }
        }

        // Write bookPublisher of the record
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
                randomAccessFile.readChar();
            }
        }

        // Write number type of the record
        randomAccessFile.writeLong(numberISBN);
        randomAccessFile.writeDouble(bookPrice);
        randomAccessFile.writeInt(stockQuantity);
    } // End writeRecord method

} // End class Book
