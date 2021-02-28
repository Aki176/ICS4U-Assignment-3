import java.io.IOException;
import java.io.RandomAccessFile;

public class Book {

    // Attributes
    private String bookTitle; // 112
    private String bookAuthor; // 40
    private String bookPublisher; // 40
    private long numberISBN; // 8
    private double bookPrice; // 8
    private int stockQuantity; // 4
    private final long recordLength = 212;
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

    // Method readRecord to read from a random access file
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

}
