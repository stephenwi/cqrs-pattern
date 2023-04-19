package cm.ss.cqrs.cqrs.query.api.projection;

import cm.ss.cqrs.cqrs.command.api.data.Product;
import cm.ss.cqrs.cqrs.command.api.model.ProductRestModel;
import cm.ss.cqrs.cqrs.command.api.repository.ProductRepository;
import cm.ss.cqrs.cqrs.query.api.queries.GetProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository repository;

    public ProductProjection(ProductRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<ProductRestModel> handle (GetProductQuery getProductQuery) {
        List<Product> products = repository.findAll();
        return products.stream().map(product -> ProductRestModel
                .builder()
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .name(product.getName())
                .build())
                .collect(Collectors.toList());

       // return productRestModelList;
    }
}
