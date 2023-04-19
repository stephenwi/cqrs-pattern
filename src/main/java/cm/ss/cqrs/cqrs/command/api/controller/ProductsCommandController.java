package cm.ss.cqrs.cqrs.command.api.controller;


import cm.ss.cqrs.cqrs.command.api.commands.CreateProductCommand;
import cm.ss.cqrs.cqrs.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

    private CommandGateway commandGateway;

    public ProductsCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String insertProduct(@RequestBody ProductRestModel productRestModel) {

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRestModel.getName())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();

        return commandGateway.sendAndWait(createProductCommand);
    }
}
