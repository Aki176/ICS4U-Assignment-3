import java.io.IOException;
import java.io.RandomAccessFile;

public class Book {

    // Attributes
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private long numberISBN;
    private double bookPrice;
    private int stockQuantity;
    private final long recordLength = 192;
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
    public Book () {
        
    }


    public void readRecord (RandomAccessFile randomAccessFile, int recordNumber) throws IOException {
        randomAccessFile.seek(recordNumber * recordLength);
        String temp = "";
    }

}
