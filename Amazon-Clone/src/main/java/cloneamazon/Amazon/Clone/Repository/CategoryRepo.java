package cloneamazon.Amazon.Clone.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cloneamazon.Amazon.Clone.Entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    boolean findByCategoryName(String categoryName);

    List<Category> findByCategoryNameContainingIgnoreCase(String keyword);
    
}
