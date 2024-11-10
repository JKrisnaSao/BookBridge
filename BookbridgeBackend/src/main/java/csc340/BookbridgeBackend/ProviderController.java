package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    // create a new provider profile
    @PostMapping("/new")
    public Provider createProvider(@RequestBody Provider provider) {
        return providerService.createProvider(provider);
    }

    // update an existing provider profile
    @PutMapping("/update/{providerId}")
    public Provider updateProvider(@PathVariable int providerId, @RequestBody Provider provider) {
        return providerService.updateProvider((long) providerId, provider);
    }

    // view all books listed by the provider
    @GetMapping("/{providerId}/books")
    public List<Book> viewProviderBooks(@PathVariable Long providerId) {
        return providerService.getBooksByProvider(providerId);
    }

    // Provider makes a new listing
    @PostMapping("/{providerId}/books/new")
    public Book createBookListing(@PathVariable Long providerId, @RequestBody Book book) {
        return providerService.createBookForProvider(providerId, book);
    }

    @GetMapping("/{providerId}/orders/all")
    public List<Order> getAllOrdersForProvider(@PathVariable Long providerId) {
        return providerService.getAllOrdersForProvider(providerId);
    }

}

