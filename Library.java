import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {
    private Map<Integer, Member> members = new HashMap<>();
    private Map<Integer, Book> bookInventory = new HashMap<>();
    private final Map<Integer, Set<Integer>> borrowMap;

    public Library() {
        this.borrowMap = new HashMap<>();
    }

    public void addMember(Member member) {
        members.put(member.getId(), member);
    }
    
    public void addToInventory(Book book) {
        bookInventory.put(book.getId(), book);
    }

    public Member findMemberById(int id) {
        return members.get(id);
    }

    public Book findBookById(int id) {
        return bookInventory.get(id);
    }

    public Map<Integer, Member> getMembers() {
        return members;
    }
    
    public Map<Integer, Book> getBookInventory() {
        return bookInventory;
    }

    public void addBorrow(int memberId, int bookId) {
        borrowMap.computeIfAbsent(memberId, k-> new HashSet<>()).add(bookId);
        bookInventory.get(bookId).checkedOut();
    }

    public void removeBorrow(int memberId, int bookId) {
        Set<Integer> borrowedBooks = borrowMap.get(memberId);
        
        if (borrowedBooks != null) {
            borrowedBooks.remove(bookId);
            bookInventory.get(bookId).returned();
            
            if (borrowedBooks.isEmpty()) {
                borrowMap.remove(memberId);
            }
        }
    }

    public Set<Integer> getBorrowedBooks(int memberId) {
        return borrowMap.getOrDefault(memberId, Collections.emptySet());
    }
}
