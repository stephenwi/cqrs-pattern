package cm.ss.cqrs.cqrs.command.api.events;


import cm.ss.cqrs.cqrs.command.api.data.Product;
import cm.ss.cqrs.cqrs.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHamdler {

    private ProductRepository repository;

    public ProductEventsHamdler(ProductRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        Product product = new Product();
        BeanUtils.copyProperties(event,product);
        repository.save(product);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw  exception;
    }
}
