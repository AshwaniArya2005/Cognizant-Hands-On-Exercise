import java.util.Arrays;
import java.util.Comparator;

class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public String toString() {
        return String.format("Book { ID: %3d, Title: %-30s, Author: %s }", bookId, title, author);
    }
}

class BookSearch {
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    public static Book linearSearchByAuthor(Book[] books, String author) {
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) return b;
        }
        return null;
    }

    public static Book binarySearchByTitle(Book[] sortedBooks, String title) {
        int low = 0, high = sortedBooks.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = sortedBooks[mid].getTitle().compareToIgnoreCase(title);
            if (cmp == 0) return sortedBooks[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}

public class LibraryManagement {
    private static void printResult(String label, Book result) {
        System.out.println(label + (result != null ? result : "Not found."));
    }

    public static void main(String[] args) {
        System.out.println("=== Library Management System ===");

        Book[] books = {
            new Book(1, "Clean Code",              "Robert C. Martin"),
            new Book(2, "The Pragmatic Programmer", "Andrew Hunt"),
            new Book(3, "Design Patterns",          "Gang of Four"),
            new Book(4, "Effective Java",           "Joshua Bloch"),
            new Book(5, "Refactoring",              "Martin Fowler"),
        };

        Book[] sortedBooks = Arrays.copyOf(books, books.length);
        Arrays.sort(sortedBooks, Comparator.comparing(b -> b.getTitle().toLowerCase()));

        System.out.println("\nLinear Search (unsorted):");
        printResult("  By title  'Effective Java'  -> ", BookSearch.linearSearchByTitle(books, "Effective Java"));
        printResult("  By author 'Martin Fowler'   -> ", BookSearch.linearSearchByAuthor(books, "Martin Fowler"));
        printResult("  By title  'Unknown Book'    -> ", BookSearch.linearSearchByTitle(books, "Unknown Book"));

        System.out.println("\nBinary Search (sorted by title):");
        printResult("  'Clean Code'               -> ", BookSearch.binarySearchByTitle(sortedBooks, "Clean Code"));
        printResult("  'Design Patterns'          -> ", BookSearch.binarySearchByTitle(sortedBooks, "Design Patterns"));
        printResult("  'Unknown Book'             -> ", BookSearch.binarySearchByTitle(sortedBooks, "Unknown Book"));

        System.out.println("\nComplexity: Linear=O(n), Binary=O(log n) on sorted data.");
        System.out.println("Use linear for unsorted/small sets; binary for large sorted catalogs.");
    }
}
