package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/all")
    public String getAllProviders(Model model) {
        model.addAttribute("providers", providerService.getAllProviders());
        return "providers"; // Maps to "providers.html"
    }

    @GetMapping("/{id}")
    public String getProviderById(@PathVariable Long id, Model model) {
        model.addAttribute("provider", providerService.getProviderById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + id)));
        return "provider-details"; // Maps to "provider-details.html"
    }

    @GetMapping("/new")
    public String showCreateProviderForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider-form"; // Maps to "provider-form.html"
    }

    @PostMapping("/new")
    public String createProvider(@ModelAttribute Provider provider) {
        providerService.createProvider(provider);
        return "redirect:/providers/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateProviderForm(@PathVariable Long id, Model model) {
        model.addAttribute("provider", providerService.getProviderById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + id)));
        return "provider-form";
    }

    @PostMapping("/update/{id}")
    public String updateProvider(@PathVariable Long id, @ModelAttribute Provider provider) {
        providerService.updateProvider(id, provider);
        return "redirect:/providers/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id); // Call the service method
        return "redirect:/providers/all";
    }

    // View books listed by a provider
    @GetMapping("/{id}/books")
    public String viewProviderBooks(@PathVariable Long id, Model model) {
        model.addAttribute("books", providerService.getBooksByProvider(id));
        return "provider-books"; // Maps to "provider-books.html"
    }

    // Create a new book listing for a provider
    @GetMapping("/{id}/books/new")
    public String showCreateBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("providerId", id);
        return "book-form"; // Maps to "book-form.html"
    }

    @PostMapping("/{id}/books/new")
    public String createBookForProvider(@PathVariable Long id, @ModelAttribute Book book) {
        providerService.createBookForProvider(id, book);
        return "redirect:/providers/" + id + "/books";
    }

    // View all orders for a provider
    @GetMapping("/{id}/orders")
    public String viewProviderOrders(@PathVariable Long id, Model model) {
        model.addAttribute("orders", providerService.getAllOrdersForProvider(id));
        return "provider-orders"; // Maps to "provider-orders.html"
    }
}
