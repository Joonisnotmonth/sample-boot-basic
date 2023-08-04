package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findAll();

    public List<Product> findByName(String name);

    public List<Product> findByNameStartingWith(String prefix);
}
