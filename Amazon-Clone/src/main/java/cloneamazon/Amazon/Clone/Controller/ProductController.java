package cloneamazon.Amazon.Clone.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import cloneamazon.Amazon.Clone.Entity.Category;
import cloneamazon.Amazon.Clone.Entity.Product;
import cloneamazon.Amazon.Clone.Service.ProductService;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

      @PostMapping("/add")
    public Product registerUser(@RequestBody Product product){
        return productService.addProduct(product);
        
    }   

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/{category}")
    public List<Product> getProductsByemail(@PathVariable("category") Category category){
        return productService.getProductByCategory(category);
    }

    @PutMapping("/{id}")
    public Product changeProductDetails(@PathVariable("id") Long ProductId , @RequestBody Product Product ){
        return productService.changeProductDetails(Product);
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable("id") Long ProductId){
        return productService.deleteProductById(ProductId);
    }
}

