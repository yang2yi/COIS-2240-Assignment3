import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set; 

public class LibraryManagement {
    private Library library = new Library();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new LibraryManagement().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. List All Members");
            System.out.println("8. List All Books");
            System.out.println("9. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    int id = getMemberIdFromInput();   
                    String name = getMemberNameFromInput();

                    library.addMember(new Member(id, name));
                    
                    System.out.println("Member added successfully.");
                    System.out.println();
                    
                    break;
                case 2:
                    id = getBookIdFromInput();

                	System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    
                    library.addToInventory(new Book(id, title));

                    System.out.println("Book added to library successfully.");
                    System.out.println();
                    
                    break;
                case 3:
                	System.out.println("\n--- Available Members ---");
                    for (Member member : library.getMembers().values()) {
                        System.out.println(member.toString());
                    }
                
                    int memberId = getMemberIdFromInput();
                    
                    System.out.println("\n--- Available Books ---");
                    for (Book book : library.getBookInventory().values()) {
                        if (book.isAvailable())
                            System.out.println(book.toString());
                    }
                    
                    int bookId = getBookIdFromInput();

                    Member member = library.findMemberById(memberId);
                    Book book = library.findBookById(bookId);

                    if (member != null && book != null) {
                    	library.addBorrow(memberId, bookId);;
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }

                    System.out.println();
                    break;
                case 4:
                    memberId = getMemberIdFromInput();
                    bookId = getBookIdFromInput();

                    member = library.findMemberById(memberId);
                    book = library.findBookById(bookId);

                    if (member != null && book != null) {
                    	library.removeBorrow(memberId, bookId);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }

                    System.out.println();
                    break;
                case 5:
                    memberId = getMemberIdFromInput();
                    Member specificMember = library.findMemberById(memberId);
                    
                    if (specificMember != null) {
                        System.out.println("Books borrowed by " + specificMember.getName() + ":");
                        Set<Integer> borrowedBookIds = library.getBorrowedBooks(memberId);
                        for (int bid : borrowedBookIds) {
                            System.out.println(" - " + library.getBookInventory().get(bid).toString());
                        }
                    } else {
                        System.out.println("Invalid member ID.");
                    }

                    System.out.println();
                    break;
                case 6:
                	//Transaction.displayTransactionHistory();
                    break;
                case 7:
                    System.out.println("\n--- All Members ---");
                    Map<Integer, Member> members = library.getMembers();
                    if(members == null || members.values().size() == 0) {
                        System.out.println("Empty");
                    } else {
                        for (Member m : members.values()) {
                            System.out.println(m.toString());
                        }
                    }
                    
                    System.out.println();
                    break;
                case 8:
                    System.out.println("\n--- All Library Books ---");
                    
                    Map<Integer, Book> books = library.getBookInventory();
                    if(books == null || books.values().size() == 0) {
                        System.out.println("Empty");
                    } else {
                        for (Book b : books.values()) {
                            System.out.println(b.toString());
                        }
                    }
             
                    System.out.println();
                    break;
                case 9:
                    System.out.println("Exiting. Good Bye..");
                    running = false;

                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    System.out.println();
            }
        }

        scanner.close();
    }


    private int getMemberIdFromInput() {
        System.out.print("Enter member ID: ");
        return getUserInputInt();
    }

    private int getBookIdFromInput() {
        System.out.print("Enter book ID: ");
        return getUserInputInt();
    }

    private int getUserInputInt() {
        int id = this.scanner.nextInt();
        scanner.nextLine();

        return id;
    }

    private String getMemberNameFromInput() {
        System.out.print("Enter member name: ");
        
        return scanner.nextLine();
    }
}
