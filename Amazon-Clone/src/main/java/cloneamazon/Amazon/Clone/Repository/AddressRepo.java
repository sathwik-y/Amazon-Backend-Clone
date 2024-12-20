package cloneamazon.Amazon.Clone.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cloneamazon.Amazon.Clone.Entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {

    List<Address> findByUserId(Long userId);

    List<Address> findByEmail(String email);

    
}
