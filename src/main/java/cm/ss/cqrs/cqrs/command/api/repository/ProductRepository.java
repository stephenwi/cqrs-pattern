package cm.ss.cqrs.cqrs.command.api.repository;

import cm.ss.cqrs.cqrs.command.api.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
