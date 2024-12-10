package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Create a new customer profile
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update existing customer profile
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setAccountStatus(customerDetails.getAccountStatus());

        return customerRepository.save(existingCustomer);
    }

    // Get a customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Get all available books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Create an order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Write a review for a book
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
