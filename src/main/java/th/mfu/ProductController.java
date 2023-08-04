package th.mfu;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // select all product
    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // select product by id
    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        // check if id exists in db
        if (!optProduct.isEmpty()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        Product pd = optProduct.get();
        return ResponseEntity.ok(pd);
    }

    // select employee by firstname
    @GetMapping("/products/name/{name}")
    public ResponseEntity getProductByName(@PathVariable String name) {
        // get product from db
        List<Product> products = productRepository.findByNameStartingWith(name);
        // check if product is empty
        if (products.isEmpty()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(products);
    }

    // create new product
    @PostMapping("/products")
    public ResponseEntity<String> createEmployee(@RequestBody Product product) {
        // TODO: check if id exists

        // add employee to repository
        productRepository.save(product);

        // return created success message
        return ResponseEntity.ok("Product created");
    }

    // update employee
    @PutMapping("/products/")
    public ResponseEntity<String> updateEmployee(@RequestBody Product product) {
        // check if id not exists
        if (!productRepository.existsById(product.getId())) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // update employee
        productRepository.save(product);

        // return success message
        return ResponseEntity.ok("Product updated");
    }

    // delete employee
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        // check if id not exists
        if (!productRepository.existsById(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        // delete employee
        productRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Product deleted");
    }
}
