package cm.ss.cqrs.cqrs.query.api.controller;

import cm.ss.cqrs.cqrs.command.api.model.ProductRestModel;
import cm.ss.cqrs.cqrs.query.api.queries.GetProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductQueryController {
    private QueryGateway gateway;
    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        GetProductQuery getProductQuery = new GetProductQuery();
        List<ProductRestModel> productRestModelList = gateway.query(getProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        return productRestModelList;
    }
}
