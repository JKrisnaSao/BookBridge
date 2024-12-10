package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private BookRepository bookRepository; // To handle books related to the provider

    @Autowired
    private OrderRepository orderRepository;

    // Create a new provider
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    // Method for updating an existing provider
    public Provider updateProvider(Long providerId, Provider providerDetails) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found with id " + providerId));

        provider.setBusinessName(providerDetails.getBusinessName());
        provider.setName(providerDetails.getName());
        provider.setEmail(providerDetails.getEmail());
        provider.setAccountStatus(providerDetails.getAccountStatus());
        provider.setRole(providerDetails.getRole());

        return providerRepository.save(provider);
    }

    // Get all providers
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    // Get provider by ID
    public Optional<Provider> getProviderById(Long providerId) {
        return providerRepository.findById(providerId);
    }

    // Get books listed by a specific provider
    public List<Book> getBooksByProvider(Long providerId) {
        return bookRepository.findByProviderId(providerId); // Assuming this method exists in BookRepository
    }

    // Create a book posting
    public Book createBookForProvider(Long providerId, Book book) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with id " + providerId));
        book.setProvider(provider);  // Link book to provider
        return bookRepository.save(book);
    }

    // Delete provider
    public void deleteProvider(Long providerId) {
        providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found with ID: " + providerId));
        providerRepository.deleteById(providerId);
    }

    private static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    // Get all orders for a specific provider
    public List<Order> getAllOrdersForProvider(Long providerId) {
        return orderRepository.findAllOrdersByProviderId(providerId);
    }
}
