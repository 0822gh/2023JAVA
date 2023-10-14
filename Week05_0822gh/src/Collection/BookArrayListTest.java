package Collection;

import java.util.Scanner;

public class BookArrayListTest {
	
	public static void main(String[] args) {
		BookArrayList bookArrayList = new BookArrayList();
		
		Book book1 = new Book(0001, "파우스트");
		Book book2 = new Book(0002, "황무지");
		Book book3 = new Book(0003, "변신");
		Book book4 = new Book(0004, "픽션들");
		Book book5 = new Book(0005, "톨스토이");
		
		bookArrayList.addBook(book1);
		bookArrayList.addBook(book2);
		bookArrayList.addBook(book3);
		bookArrayList.addBook(book4);
		bookArrayList.addBook(book5);
		
		bookArrayList.showAllBook(); 
		
		String title;

		Scanner s = new Scanner(System.in);
		System.out.print("도서를 추가할 위치 입력 : ");
		int insertIndex = s.nextInt();
		s.nextLine();
		
		System.out.print("책 ID 입력 : ");
		int BookId = s.nextInt();
		s.nextLine();
		
		System.out.print("책 제목 입력 : ");
		title = s.nextLine();

		Book newBook = new Book(BookId, title);
		bookArrayList.insertBook(insertIndex, newBook);
		bookArrayList.showAllBook();
	}
}