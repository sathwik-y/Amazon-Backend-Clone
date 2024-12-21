package cloneamazon.Amazon.Clone.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cloneamazon.Amazon.Clone.Entity.Category;
import cloneamazon.Amazon.Clone.Entity.Product;
import cloneamazon.Amazon.Clone.Service.CategoryService;

@RestController
@RequestMapping("/category")
public class CateogryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Category addNewCategory(@RequestParam String categoryName) {
        return categoryService.addNewCategory(categoryName);
    }

    @PostMapping("/{categoryId}/products")
    public List<Product> addProductsToCategory(@PathVariable("categoryId") Long categoryId, @RequestParam List<Long> products) {
        return categoryService.addProductsToCategory(categoryId, products);
    }

    
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long categoryId, 
            @RequestParam String newName) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, newName));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getProductsByCategory(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategories(@RequestParam String keyword) {
        return ResponseEntity.ok(categoryService.searchCategories(keyword));
    }

    @GetMapping("/{categoryId}/product-count")
    public ResponseEntity<Long> getCategoryProductCount(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryProductCount(categoryId));
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromCategory(
            @PathVariable Long categoryId,
            @PathVariable Long productId) {
        categoryService.removeProductFromCategory(categoryId, productId);
        return ResponseEntity.ok().build();
    }
}   
