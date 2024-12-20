package cloneamazon.Amazon.Clone.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import cloneamazon.Amazon.Clone.Entity.Category;
import cloneamazon.Amazon.Clone.Entity.Product;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    
    public List<Product> findByCategory(Category category);

    public Long countProductsByCategory(Long categoryId);

    public List<Product> findAllByCategory(Category category);

}
