package cloneamazon.Amazon.Clone.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cloneamazon.Amazon.Clone.Entity.Category;
import cloneamazon.Amazon.Clone.Entity.Product;
import cloneamazon.Amazon.Clone.Repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        return product.orElseThrow(()->new RuntimeException("Product Not Found"));
    }

    public List<Product> getProductByCategory(Category category) {
        return productRepo.findByCategory(category);
    }

    public String deleteProductById(Long productId) {
        if(!productRepo.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepo.deleteById(productId);
        return "Product Deleted";
    }

    public Product changeProductDetails(Product product) {

        Product prod = productRepo.getReferenceById(product.getProductId());
        prod.setCategory(product.getCategory());
        prod.setDescription(product.getDescription());
        prod.setPrice(product.getPrice());
        prod.setProductName(product.getProductName());
        prod.setStock(product.getStock());
        return productRepo.save(prod);
        }

    public List<Product> saveAll(List<Product> produc) {
        return productRepo.saveAll(produc);
    }

    public Long countProductsByCategory(Long categoryId) {
        return productRepo.countByCategory_CategoryId(categoryId);
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepo.findAllByCategory(category);
    }
}
