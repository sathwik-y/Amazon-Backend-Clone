package cloneamazon.Amazon.Clone.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.Category;
import cloneamazon.Amazon.Clone.Entity.Product;
import cloneamazon.Amazon.Clone.Repository.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductService productService;

    public Category addNewCategory(String categoryName) {
        if(categoryRepo.findByCategoryName(categoryName)){
            throw new RuntimeException("Category already exist");
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        return categoryRepo.save(category);
    }

    public List<Product> addProductsToCategory(Long categoryId, List<Long> products) {
        Category category = categoryRepo.findById(categoryId)
                            .orElseThrow(()-> new RuntimeException("Category Not Found"));
    
        List <Product> produc = products.stream()   
                                    .map(id-> productService.getProductById(id))
                                    .peek(product->product.setCategory(category))
                                    .collect(Collectors.toList());
        return productService.saveAll(produc);
    }

    public Category updateCategory(Long categoryId, String newName){
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
        category.setCategoryName(newName); 
        return categoryRepo.save(category);
    }

    public List<Category> searchCategories(String keyword) {
        return categoryRepo.findByCategoryNameContainingIgnoreCase(keyword);
    }

    public Long getCategoryProductCount(Long categoryId) {
        return productService.countProductsByCategory(categoryId);
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepo.delete(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void removeProductFromCategory(Long categoryId,Long productId){
        Product product = productService.getProductById(productId);
        product.setCategory(null);
        productService.addProduct(product);
    }
    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        return productService.getProductsByCategory(category);
    }
}
