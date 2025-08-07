import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Book class
    static class Book {
        private int id;
        private String title;
        private String author;
        private boolean isIssued;

        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public int getId() { return id; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isIssued() { return isIssued; }

        public void issueBook() {
            this.isIssued = true;
        }

        public void returnBook() {
            this.isIssued = false;
        }

        public String toString() {
            return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No");
        }
    }

    // Library class
    static class Library {
        private ArrayList<Book> books = new ArrayList<>();

        public void addBook(Book book) {
            books.add(book);
        }

        public void viewBooks() {
            if (books.isEmpty()) {
                System.out.println("No books in the library.");
                return;
            }
            for (Book book : books) {
                System.out.println(book);
            }
        }

        public Book findBookById(int id) {
            for (Book book : books) {
                if (book.getId() == id)
                    return book;
            }
            return null;
        }

        public boolean issueBook(int id) {
            Book book = findBookById(id);
            if (book != null && !book.isIssued()) {
                book.issueBook();
                return true;
            }
            return false;
        }

        public boolean returnBook(int id) {
            Book book = findBookById(id);
            if (book != null && book.isIssued()) {
                book.returnBook();
                return true;
            }
            return false;
        }
    }

    // Main program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Preload some books
        library.addBook(new Book(101, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(102, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(103, "1984", "George Orwell"));

        int choice;
        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. View All Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    if (library.issueBook(issueId))
                        System.out.println("Book issued successfully.");
                    else
                        System.out.println("Book not available or already issued.");
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    if (library.returnBook(returnId))
                        System.out.println("Book returned successfully.");
                    else
                        System.out.println("Invalid return or book was not issued.");
                    break;
                case 4:
                    System.out.println("Thank you for using the Library System.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
