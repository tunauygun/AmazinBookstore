package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.entities.Purchase;
import com.example.amazinbookstore.repositories.BookRepository;
import com.example.amazinbookstore.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/amazinBookstore")
public class BookViewController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    // Manage all books as admin
    @GetMapping("/admin/books")
    public String manageAllBooksAsAdmin(@RequestParam(value = "sort", required = false) String sort, Model model) {
        List<Book> books;
        if ("alphabetical".equals(sort)) {
            books = bookRepository.findAll(Sort.by("title").ascending());
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        return "Admin_Books";
    }



    @GetMapping("/customer/books")
    public String viewAllBooksAsCustomer(@RequestParam(value = "sort", required = false) String sort, Model model) {
        List<Book> books;
        if ("alphabetical".equals(sort)) {
            books = bookRepository.findAll(Sort.by("title").ascending());
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        return "Customer_Books";
    }

    // View all recommended books as customer
    @GetMapping("/customer/recommended_books")
    public String viewAllRecBooksAsCustomer(Model model) {
        List<Book> recBooks = new ArrayList<>();

        List<Purchase> purchases = purchaseRepository.findAll();
        System.out.println("Number of Purchases # = " + purchases.size());
        List<List<Long>> booksIds = new ArrayList<>();
        int n = 0;

        //Moved all books in purchased to booksIds
        for (Purchase purchase : purchases) {
            System.out.print("Purchase id = " + purchase.getId() + ", ");
            System.out.print("Book ids = ");
            booksIds.add(new ArrayList<>());
            booksIds.get(n).addAll(purchase.getBookIds());
            for (Book b : purchase.getBooks()) {
                System.out.print(b.getId().toString() + ", ");
            }
            System.out.println();
            n++;
        }

        Map<String, Double> map = new HashMap<>();

        //Calculate jaccard similatrity for all possible combinations
        int x = 0; //purchase id 1
        for (List<Long> i : booksIds) {
            int y = 0; //purchase id 2
            for (List<Long> j : booksIds) {
                //if haven't compared these two purchaseId books yet
                if (i != j && !(map.containsKey((x) + "," + (y)) || map.containsKey((y) + "," + (x)))) {
                    //Calculate jaccard similatrity
                    double jaccardSimilarity = jaccardSimilarity(i.toArray(new Long[0]), j.toArray(new Long[0]));
                    System.out.println(x + " " + y + " " + jaccardSimilarity);
                    //Store purchase ids hashmap
                    if (x < y) {
                        map.put((x) + "," + (y), jaccardSimilarity);
                    } else {
                        map.put((y) + "," + (x), jaccardSimilarity);
                    }
                }
                y++;
            }
            x++;
        }

        List<Long> recIds = new ArrayList<>(); //Id's of books to recommend
        List<String> topKeys = new ArrayList<>(); //Id's of keys present to makeup recIds list
        double bestJaccard = -1;
        String key = "";
        n = 0;
        do {
            //for all combinations of purchases
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                //if it is highest jaccard value so far and haven't stored this key yet
                if (entry.getValue() > bestJaccard && !topKeys.contains(entry.getKey())) {
                    bestJaccard = entry.getValue();
                    key = entry.getKey();
                }
            }
            //for all books in key get book id and add to
            for (Long bookId : intersectionBooks(booksIds, key)) {
                if (!recIds.contains(bookId)) {
                    recIds.add(bookId);
                    bookRepository.findById(bookId).ifPresent(recBooks::add);
                }
            }
            System.out.println("N: " + n + " Key: " + key + ", Best jaccard: " + bestJaccard);
            topKeys.add(key);
            n++;
            bestJaccard = -1;
            key = "";
        } while (recIds.size() <  8 || n >= map.size() - 1); // until there are 8 recommendations or finished iterating through hashmap

        System.out.println("Final recommended Book List: " + recIds);

        model.addAttribute("books", recBooks);
        return "Recommended_Books";
    }

    //Calculate jaccard similarity based on two arrays of type Long
    static private  double jaccardSimilarity(Long[] a, Long[] b) {
        Set<Long> s1 = new LinkedHashSet<>(Arrays.asList(a));
        Set<Long> s2 = new LinkedHashSet<>(Arrays.asList(b));

        Set<Long> intersection = new LinkedHashSet<>(s1);
        intersection.retainAll(s2);

        Set<Long> union = new LinkedHashSet<>(s1);
        union.addAll(s2);

        double jaccardSimilarity = (double)intersection.size()/ (double)union.size();
//        System.out.println(intersection + " " + union);
        return jaccardSimilarity;
    }

    //Output any books that are in both lists
    //First digit of key is the first purchase id books and second is the second purchased id books
    static private List<Long> intersectionBooks(List<List<Long>> booksIds, String key) {
        String[] keyParts = key.split(",");
        Long[] a = booksIds.get(Integer.parseInt(keyParts[0])).toArray(new Long[0]);
        Long[] b = booksIds.get(Integer.parseInt(keyParts[1])).toArray(new Long[0]);

        Set<Long> s1 = new LinkedHashSet<>(Arrays.asList(a));
        Set<Long> s2 = new LinkedHashSet<>(Arrays.asList(b));

        Set<Long> intersection = new LinkedHashSet<>(s1);
        intersection.retainAll(s2);
        return List.of(intersection.toArray(new Long[0]));
    }
}
