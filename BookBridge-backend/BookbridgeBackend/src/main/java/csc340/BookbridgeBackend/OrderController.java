package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders"; // Maps to "orders.html"
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order-details"; // Maps to "order-details.html"
    }

//    @GetMapping("/new")
//    public String showCreateOrderForm(Model model) {
//        model.addAttribute("orderRequest", new OrderRequest());
//        return "order-form"; // Maps to "order-form.html"
//    }
//
//    @PostMapping("/new")
//    public String createOrder(@ModelAttribute OrderRequest orderRequest) {
//        orderService.createOrder(orderRequest.getCustomerId(), orderRequest.getBookId());
//        return "redirect:/orders/all";
//    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        // Call the delete method in OrderService
        orderService.deleteOrder(id);
        return "redirect:/orders/all";
    }


    @GetMapping("/new")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.getAllCustomers()); // List of customers
        model.addAttribute("books", bookService.getAllBooks()); // List of books
        return "order-form"; // Maps to "order-form.html"
    }

    @PostMapping("/new")
    public String createOrder(@ModelAttribute Order order) {
        // Save the new order
        orderService.createOrder(order);
        return "redirect:/orders/all"; // Redirect to orders list after creation
    }

    @GetMapping("/update/{id}")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("books", bookService.getAllBooks());
        return "order-form"; // Maps to "order-form.html"
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        // Update the existing order
        order.setOrderId(id); // Set the order ID to update the correct order
        orderService.updateOrder(order);
        return "redirect:/orders/all"; // Redirect to orders list after update
    }


}

