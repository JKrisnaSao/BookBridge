package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // create a new customer profile
    @PostMapping("/new")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // update existing customer profile
    @PutMapping("/update/{customerId}")
    public Customer updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    // view all books
    @GetMapping("/books")
    public List<Book> viewAvailableBooks() {
        return customerService.getAllBooks();
    }

    // place an order
    @PostMapping("/subscribe")
    public Order subscribeToBook(@RequestBody Order order) {
        return customerService.createOrder(order);
    }

    // write a review for a book endpoint
    @PostMapping("/review")
    public Review writeReview(@RequestBody Review review) {
        return customerService.addReview(review);
    }
}
