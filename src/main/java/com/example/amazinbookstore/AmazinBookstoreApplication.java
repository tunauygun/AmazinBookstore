package com.example.amazinbookstore;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.entities.Cart;
import com.example.amazinbookstore.entities.CartItem;
import com.example.amazinbookstore.entities.Purchase;
import com.example.amazinbookstore.repositories.BookRepository;
import com.example.amazinbookstore.repositories.PurchaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AmazinBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazinBookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, PurchaseRepository purchaseRepository) {
		return (args) -> {
			// Create some initial books
			Book book1 = new Book(
					Arrays.asList("Richard Hooker", "John Keble", "Michael Russell"), "Religion","978-3-16-148410-0",300,9.99,"CreateSpace",10,"Of the Laws of Ecclesiastical Polity: Set (Hooker's Works Series)","http://books.google.com/books/content?id=6SZMbwAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",1905
			);
			Book book2 = new Book(
					Arrays.asList("David M. Lawrence"), "Science","978-3-16-148420-1",450,29.99,"Rutgers University Press",5,"Upheaval from the Abyss: Ocean Floor Mapping and the Earth Science Revolution","http://books.google.com/books/content?id=Bx2B7nsdbb0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2002

			);
			Book book3 = new Book(
					Arrays.asList("Allen Woodall", "Sean Brickell"), "Antiques & Collectibles","978-3-16-148430-2",120,15,"Schiffer Pub Limited",8,"The Illustrated Encyclopedia of Metal Lunch Boxes (A Schiffer Book for Collectors)","http://books.google.com/books/content?id=GOj2AAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",1999

			);
			Book book4 = new Book(
					Arrays.asList("Sheldon M. Novick"), "Biography & Autobiography","978-3-16-148440-3",280,12.5,"Laurel",7,"Honorable Justice; The Life of Oliver Wendell Holmes","http://books.google.com/books/content?id=JhPYAAAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",1990

			);
			Book book5 = new Book(
					Arrays.asList("Marlen Haushofer"), "Fiction","978-3-16-148450-4",320,14.99,"Cleis Press",12,"The Wall","http://books.google.com/books/content?id=q7QF1JXNPG4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2011

			);
			Book book6 = new Book(
					Arrays.asList("William Benedetto"),"Transportation","978-3-16-148460-5",250,18,"Citadel Press",10,"Sailing into the Abyss: A True Story of Extreme Heroism on the High Seas","http://books.google.com/books/content?id=bMd4k1R4YtcC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2000

			);
			Book book7 = new Book(
					Arrays.asList("Wayne Rice"),"Religion","978-3-16-148470-6",400,24.5,"Zondervan/Youth Specialties",15,"More Hot Illustrations For Youth Talks","http://books.google.com/books/content?id=E7ekFH6c9BIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2010

			);
			Book book8 = new Book(
					Arrays.asList("Laura Hillenbrand"),"Biography & Autobiography","978-3-16-148480-7",220,16.75,"Random House Trade Paperbacks",6,"A Mission to Millions; The Story of Ernie Allen and the Every Home Crusade","http://books.google.com/books/content?id=1PeLDQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2014

			);
			Book book9 = new Book(
					Arrays.asList("Mark Wertman"),"Biography & Autobiography","978-3-16-148490-8",330,20,"iUniverse",8,"True Confessions of a Real Mr. Mom","http://books.google.com/books/content?id=vr-WF7ncWzoC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2000

			);
			Book book10 = new Book(
					Arrays.asList("J. Benton Jones Jr."),"Science","978-3-16-148500-9",290,11.99,"CRC Press",9,"Tomato Plant Culture In the Field, Greenhouse, and Home Garden","http://books.google.com/books/content?id=eEy9ftsCqtoC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2007

			);
			Book book11 = new Book(
					Arrays.asList("Jameson Currier"),"Fiction","978-3-16-148510-0",350,22.5,"Penguin Group USA",5,"Dancing on the Moon: Short Stories About AIDS","http://books.google.com/books/content?id=AAaxAAAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",1994

			);
			Book book12 = new Book(
					Arrays.asList("Dean W. Arnold"),"History","978-3-16-148520-1",200,13.95,"Dean W. Arnold",8,"America's Trail of Tears: A Story of Love and Betrayal","http://books.google.com/books/content?id=Un8IAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",2000

			);
			Book book13 = new Book(
					Arrays.asList("Alex Rovira", "Fernando Trías De Bes"),"Business & Economics","978-3-16-148530-2",400,19.99,"Jossey-Bass",7,"Good Luck: Creating the Conditions for Success in Life and Business","http://books.google.com/books/content?id=8zYGAAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",2004

			);
			Book book14 = new Book(
					Arrays.asList("Jennifer Fulton"),"Computers","978-3-16-148540-3",180,10.5,"Sams Publishing",12,"How to Use Microsoft Publisher 2000","http://books.google.com/books/content?id=kwu1ee0fHAAC&printsec=frontcover&img=1&zoom=1&source=gbs_api",1999

			);
			Book book15 = new Book(
					Arrays.asList("Simon Brett"),"Fiction","978-3-16-148550-4",310,17.75,"Severn House Publishers Ltd",9,"What Bloody Man Is That","http://books.google.com/books/content?id=YRmpBQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2012

			);
			Book book16 = new Book(
					Arrays.asList("Andy Symonds"),"Fiction","978-3-16-148560-5",270,14.3,"Mascot Books",11,"Thy Father's Son: A Novel","http://books.google.com/books/content?id=vF7esgEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",2015

			);
			Book book17 = new Book(
					Arrays.asList("Helen Strudwick"),"Egypt","978-3-16-148570-6",390,25.6,"Amber Books",4,"To the Land of the Living (Signed First Edition, Leather Bound, Easton Press)","http://books.google.com/books/content?id=kNMDkAEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",2000

			);
			Book book18 = new Book(
					Arrays.asList("Laura Robb"),"Education","978-3-16-148580-7",360,18.45,"Teaching Resources",14,"Teaching reading in social studies, science, and math","http://books.google.com/books/content?id=JqWh2Jc_G1kC&printsec=frontcover&img=1&zoom=1&source=gbs_api",2003

			);
			Book book19 = new Book(
					Arrays.asList("Joe Lamport"),"Fiction","978-3-16-148590-8",340,21.3,"Booklocker.Com Incorporated",10,"Dinkelmann's Rules: A Dot-Comedy of Errors","http://books.google.com/books/content?id=ELo6LxMSsGUC&printsec=frontcover&img=1&zoom=1&source=gbs_api",2000

			);
			Book book20 = new Book(
					Arrays.asList("Marianna Torgovnick"),"History","978-3-16-148600-9",260,15.75,"University of Chicago Press",13,"The War Complex: World War II in Our Time","http://books.google.com/books/content?id=L1mfUVX7y-IC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",2000

			);

			// Save the books in the repository
			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);
			bookRepository.save(book4);
			bookRepository.save(book5);
			bookRepository.save(book6);
			bookRepository.save(book7);
			bookRepository.save(book8);
			bookRepository.save(book9);
			bookRepository.save(book10);
			bookRepository.save(book11);
			bookRepository.save(book12);
			bookRepository.save(book13);
			bookRepository.save(book14);
			bookRepository.save(book15);
			bookRepository.save(book16);
			bookRepository.save(book17);
			bookRepository.save(book18);
			bookRepository.save(book19);
			bookRepository.save(book20);

			List<Book> books = bookRepository.findAll();

			//add 10 purchases

			CartItem cartItem1, cartItem2, cartItem3, cartItem4, cartItem5;
			Cart cart;
			Purchase purchase;

			cartItem1 = new CartItem(books.get(5), 2);
			cartItem2 = new CartItem(books.get(7), 2);
			cartItem3 = new CartItem(books.get(2), 1);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(13), 1);
			cartItem2 = new CartItem(books.get(17), 1);
			cartItem3 = new CartItem(books.get(12), 1);
			cartItem4 = new CartItem(books.get(5), 1);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			cart.addCartItem(cartItem4);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(12), 1);
			cartItem2 = new CartItem(books.get(19), 4);
			cartItem3 = new CartItem(books.get(4), 4);
			cartItem4 = new CartItem(books.get(1), 1);
			cartItem5 = new CartItem(books.get(3), 3);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			cart.addCartItem(cartItem4);
			cart.addCartItem(cartItem5);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(2), 4);
			cartItem2 = new CartItem(books.get(17), 4);
			cartItem3 = new CartItem(books.get(2), 4);
			cartItem4 = new CartItem(books.get(5), 5);
			cartItem5 = new CartItem(books.get(7), 4);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			cart.addCartItem(cartItem4);
			cart.addCartItem(cartItem5);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(3), 4);
			cartItem2 = new CartItem(books.get(7), 3);
			cartItem3 = new CartItem(books.get(8), 4);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(8), 4);
			cartItem2 = new CartItem(books.get(4), 5);
			cartItem3 = new CartItem(books.get(9), 4);
			cartItem4 = new CartItem(books.get(1), 1);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			cart.addCartItem(cartItem4);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(2), 3);
			cartItem2 = new CartItem(books.get(9), 1);
			cartItem3 = new CartItem(books.get(14), 1);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(14), 2);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(17), 3);
			cartItem2 = new CartItem(books.get(0), 3);
			cartItem3 = new CartItem(books.get(16), 4);
			cartItem4 = new CartItem(books.get(7), 2);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			cart.addCartItem(cartItem4);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			cartItem1 = new CartItem(books.get(7), 5);
			cartItem2 = new CartItem(books.get(11), 2);
			cartItem3 = new CartItem(books.get(4), 4);
			cart = new Cart();
			cart.addCartItem(cartItem1);
			cart.addCartItem(cartItem2);
			cart.addCartItem(cartItem3);
			purchase = new Purchase(cart,"qw","er", "tyuio");
			purchaseRepository.save(purchase);

			//Show if purchases were saved properly
//			List<Purchase> purchases = purchaseRepository.findAll();
//			for (Purchase p : purchases) {
//				System.out.println("\n");
//				System.out.println("purchase id = " + p.getId());
//				System.out.println("purchased books size = " + p.getBooks().size());
//				System.out.print("Book id = ");
//				for (Book b : p.getBooks()) {
//					System.out.print(b.getId().toString() + ", ");
//				}
//				System.out.println();
//			}

////			Generate purchases
//			Random rand = new Random();
//			for (int i = 1; i < 6; i++) {
//				int j = 0;
//				int maxj = rand.nextInt(8)+1;
//				for (j = 1; j <= maxj; j++) {
//					System.out.println("cartItem" + j + " = new CartItem(books.get(" + rand.nextInt(20) + "), " + (rand.nextInt(5) + 1) + ");");
//				}
//				System.out.println("cart = new Cart();");
//				for (j = 1; j <= maxj; j++) {
//					System.out.println("cart.addCartItem(cartItem" + j + ");");
//				}
//				System.out.println("cartRepository.save(cart);");
//				System.out.println("carts = cartRepository.findAll();");
//				System.out.println("purchase = new Purchase(carts.get(0),\"qw\",\"er\", \"tyuio\");");
//				System.out.println("purchaseRepository.save(purchase);");
//				System.out.println("cartRepository.deleteAll();");
//				System.out.println();
//			}
		};
	}

}
