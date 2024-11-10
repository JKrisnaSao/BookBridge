package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // create a new customer profile
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // update existing customer profile
    public Customer updateCustomer(int customerId, Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById((long) customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setAccountStatus(customerDetails.getAccountStatus());

        return customerRepository.save(existingCustomer);
    }

    // get all available books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // create an order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // write a review for a book
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}

